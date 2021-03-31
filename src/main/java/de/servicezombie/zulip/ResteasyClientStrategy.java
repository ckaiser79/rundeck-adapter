package de.servicezombie.zulip;

import java.nio.charset.Charset;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Metric;
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
	public void send(ZulipMessage message) {
		final Client client = ClientBuilder.newClient();

		final String auth = message.getUser() + ":" + message.getToken();
		byte[] encodedAuth = Base64.encodeBytesToBytes(auth.getBytes(Charset.forName("ISO-8859-1")));
		final String authHeader = "Basic " + new String(encodedAuth);

		final String uri = message.getEndpoint();
		final WebTarget target = client.target(uri);
		target.register(new LoggingRestClientFilter());
		final Entity<?> entity = Entity.entity(message.toJson(), MediaType.APPLICATION_JSON);
		
		target.request()
				.header(HttpHeaders.AUTHORIZATION, authHeader)
				.buildPost(entity)
				.submit(new InvocationCallbackImplementation());
		
	}

}
