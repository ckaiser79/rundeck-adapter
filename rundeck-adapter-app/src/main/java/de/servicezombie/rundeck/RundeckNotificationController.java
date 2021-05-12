package de.servicezombie.rundeck;

import java.io.StringWriter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * receive rundeck notifications
 */
@Path("/notification")
@ApplicationScoped
@OpenAPIDefinition(info = @Info(title = "Rundeck notification endpoint", version = "1.0"))
public class RundeckNotificationController {

	private static final Logger LOG = LoggerFactory.getLogger(RundeckNotificationController.class);
	private static final Logger XMLLOG = LoggerFactory
			.getLogger(RundeckNotificationController.class.getPackage().getName() + ".XML");

	@Inject
	private NotificationService notificationService;

	/**
	 * .
	 * @param notification rundeck message
	 * @return 204
	 */
	@APIResponses(value = {
			@APIResponse(responseCode = "204", description = "Event received")
	})
	@POST
	@Timed(name = "timed-request")
	@Consumes({ "application/xml", "text/xml"})	
	@Path("/receive")
	public Response receive(Notification notification) {
		if (LOG.isTraceEnabled())
			LOG.trace("receive: >> {}", notification);

		logXmlDataIfEnabled(notification);

		notificationService.onReceive(notification);
		return Response.noContent().build();
	}

	private void logXmlDataIfEnabled(final Notification notification) {

		if (XMLLOG.isInfoEnabled()) {

			if (notification != null) {

				final StringWriter writer = new StringWriter();
				JAXBContext jaxbContext;
				try {
					jaxbContext = JAXBContext.newInstance(Notification.class);
					jaxbContext.createMarshaller().marshal(notification, writer);
				} catch (JAXBException e) {
					XMLLOG.error("receive: dump notification as xml fails ", notification);
				}
				XMLLOG.info("receive: dump notification as xml\n{}\n.", writer.toString());
			}
			else {
				XMLLOG.info("receive: dump notification as xml null-data");
			}
		}
	}
}
