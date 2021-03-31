package de.servicezombie.zulip;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import de.servicezombie.rundeck.Notification;
import de.servicezombie.rundeck.NotificationService;

@Named
public class ZulipRoutingServiceImpl implements NotificationService {

	private Config config = ConfigProvider.getConfig();
	
	@Inject
	private RestClientStrategy strategy = new ResteasyClientStrategy();

	@Override
	public void onReceive(Notification notification) {

		final NotificationToZulipMessageTransformer transformer = new NotificationToZulipMessageTransformer();
		transformer.setStream(loadStream(notification));		
		transformer.setTopic(loadTopic(notification));
		transformer.setToken(config.getValue("zulip.token", String.class));
		transformer.setUser(config.getValue("zulip.user", String.class));
		transformer.setEndpoint(config.getValue("zulip.endpoint", String.class));
		
		final ZulipMessage message = transformer.transform(notification);

		strategy.send(message);

	}


	private String loadStream(Notification notification) {
		final String result;
		final Optional<String> value = config.getOptionalValue("zulip.stream." + notification.getStatus(), String.class);
		if (value.isEmpty()) {
			result = config.getValue("zulip.stream.default", String.class);
		} else {
			result = value.get();
		}

		return result;
	}

	private String loadTopic(Notification notification) {
		final String result;
		final Optional<String> value = config.getOptionalValue("zulip.topic." + notification.getStatus(), String.class);
		if (value.isEmpty()) {
			result = config.getValue("zulip.topic.default", String.class);
		} else {
			result = value.get();
		}

		return result;
	}

}
