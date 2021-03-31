package de.servicezombie.rundeck;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
@Path("/rundeck/notification")
@Singleton
@ApplicationScoped
@OpenAPIDefinition(info = @Info(title = "Rundeck notification endpoint", version = "1.0"))
public class RundeckNotificationController {

	private static final Logger LOG = LoggerFactory.getLogger(RundeckNotificationController.class);

	@Inject
	private NotificationService notificationService;
	
	@APIResponses(value = {
			@APIResponse(responseCode = "204", description = "Event received")
	})
	@POST
	@Path("/receive")
	public Response receive(Notification notification) {
		if (LOG.isTraceEnabled())
			LOG.trace("receive: >> {}", notification);
		return Response.noContent().build();
	}
}
