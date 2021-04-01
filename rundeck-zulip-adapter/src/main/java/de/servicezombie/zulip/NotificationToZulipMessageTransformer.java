package de.servicezombie.zulip;

import java.io.IOException;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.templatemode.TemplateMode;

import de.servicezombie.rundeck.Notification;
import de.servicezombie.thymeleaf.TemplateWriter;
import de.servicezombie.thymeleaf.ThymeleadContextFactory;

/**
 * read from a template use template to create a zulip message;
 * 
 * @author chris
 *
 */
public class NotificationToZulipMessageTransformer {

	private static final Logger LOG = LoggerFactory.getLogger(NotificationToZulipMessageTransformer.class);

	private String stream;
	private String topic;
	private final ZulipEndpoint endpoint;

	public NotificationToZulipMessageTransformer(final ZulipEndpoint endpoint) {
		this.endpoint = endpoint;
	}

	public ZulipRequest transform(Notification notification) {

		ZulipRequest result = new ZulipRequest(endpoint);

		final ThymeleadContextFactory contextFactory = new ZulipMessageThymeleafContextFactory(result, notification);

		final String message = readTemplate(notification, contextFactory);

		result.setContent(message);
		result.setType(ZulipType.STREAM);
		result.setTo(stream.split("[,: ]"));
		result.setTopic(topic);

		return result;
	}

	private String readTemplate(final Notification notification, final ThymeleadContextFactory contextFactory)
			{

		if (LOG.isTraceEnabled())
			LOG.trace("readTemplate: >> {}, {}", notification, contextFactory);

		final String templateName = "rundeck." + notification.getTrigger();
		String result;

		try (final StringWriter out = new StringWriter()) {
			final TemplateWriter templateWriter = new TemplateWriter(templateName, TemplateMode.TEXT, "UTF8");

			templateWriter.write(contextFactory, out);
			result = out.toString();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			result = null;
		}

		if (LOG.isTraceEnabled())
			LOG.trace("readTemplate: << \n{}", result);

		return result;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

}
