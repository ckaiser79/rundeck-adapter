package de.servicezombie.zulip;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Metric;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.util.Base64;

import de.servicezombie.rundeck.LoggingRestClientFilter;

@Named
public class ResteasyClientStrategy implements RestClientStrategy {

	private final class InvocationCallbackImplementation implements InvocationCallback<Object> {
		@Override
		public void completed(Object response) {
			messagesSendCounter.inc();
		}

		@Override
		public void failed(Throwable throwable) {
			messagesFailCounter.inc();
		}
	}

	@Inject
	@Metric(name = "messages-send-counter")
	private Counter messagesSendCounter;

	@Inject
	@Metric(name = "messages-fail-counter")
	private Counter messagesFailCounter;

	@Override
	public void send(ZulipRequest message) {
		final ResteasyClient client = new ResteasyClientBuilder()
				.connectionPoolSize(5)
				.connectionTTL(500, TimeUnit.MILLISECONDS)
				.disableTrustManager()
				.connectTimeout(2, TimeUnit.SECONDS)
				.readTimeout(5, TimeUnit.SECONDS)
				.build();
		

		final ZulipEndpoint endpoint = message.getEndpoint();

		final String auth = endpoint.getUser() + ":" + endpoint.getToken();
		byte[] encodedAuth = Base64.encodeBytesToBytes(auth.getBytes(Charset.forName("ISO-8859-1")));
		final String authHeader = "Basic " + new String(encodedAuth);

		final String uri = endpoint.getEndpoint();
		final WebTarget target = client.target(uri);
		target.register(new LoggingRestClientFilter());

		final Form form = new Form();
		form.param("to", toArrayString(message.getTo()));
		form.param("topic", message.getTopic());
		form.param("type", message.getType().toString().toLowerCase());
		form.param("content", message.getContent());
		
		target.request()
				.header(HttpHeaders.AUTHORIZATION, authHeader)
				.buildPost(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED))
				.submit(new InvocationCallbackImplementation());

	}

	private String toArrayString(String[] to) {
		if(to == null)
			return "[]";
		
		final Iterable<String> iterable = Arrays.asList(to);
		return "[" + org.thymeleaf.util.StringUtils.join(iterable, ',') + "]";
		
	}

}
