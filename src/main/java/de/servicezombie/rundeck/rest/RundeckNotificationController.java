package de.servicezombie.rundeck.rest;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.servicezombie.rundeck.NotificationService;

/**
 *
 */
@Path("/notification")
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
	@Timed(name = "timed-request")
	@Path("/receive")
	public Response receive(Notification notification) {
		if (LOG.isTraceEnabled())
			LOG.trace("receive: >> {}", notification);
		
		notificationService.onReceive(notification);
		return Response.noContent().build();
	}
}
