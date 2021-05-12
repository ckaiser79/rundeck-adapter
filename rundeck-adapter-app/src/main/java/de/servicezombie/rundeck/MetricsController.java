package de.servicezombie.rundeck;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.smallrye.metrics.exporters.OpenMetricsExporter;

/**
 * expose metrics of this application
 */
@Path("/metrics")
public class MetricsController {

	private final OpenMetricsExporter exporter = new OpenMetricsExporter();

	/**
	 * e.g. for Prometheus
	 */
	@GET
	@Path("/")
	public Response metrics() {
		final StringBuilder result = exporter.exportAllScopes();		
		return Response.ok(result.toString(), MediaType.TEXT_PLAIN).build();
	}

}
