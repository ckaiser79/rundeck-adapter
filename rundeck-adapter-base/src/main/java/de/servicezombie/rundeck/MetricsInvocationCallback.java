package de.servicezombie.rundeck;

import javax.ws.rs.client.InvocationCallback;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * store metrics about rest calls 
 */
public final class MetricsInvocationCallback implements InvocationCallback<Object> {

	private static final Logger LOG = LoggerFactory.getLogger(MetricsInvocationCallback.class);
	
	private final Tag tag;
	private final MetricRegistry metricRegistry;

	/** 
	 * Well a constructor.
	 * 
	 * @param metricRegistry to receive counter objects
	 * @param jobNameTagValue usually the group/jobname of a rundeck job is epxected here
	 */
	public MetricsInvocationCallback(final MetricRegistry metricRegistry, final String jobNameTagValue) {
		this.metricRegistry = metricRegistry;
		if (jobNameTagValue == null)
			tag = null;
		else
			tag = new Tag("jobname", jobNameTagValue);
	}

	@Override
	public void completed(Object response) {
		final Counter counter = metricRegistry.counter("zulip-messages-completed-counter", tag);
		counter.inc();
		LOG.trace("receive ok message for {}", tag.getTagValue());
	}

	@Override
	public void failed(Throwable throwable) {
		final Counter counter = metricRegistry.counter("zulip-messages-failure-counter", tag);
		counter.inc();
		LOG.trace("receive fail message for {}", tag.getTagValue());
	}
}