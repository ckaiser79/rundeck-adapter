package de.servicezombie.zulip;

import javax.ws.rs.client.InvocationCallback;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.Tag;

final class MetricsInvocationCallback implements InvocationCallback<Object> {


	private final Tag tag;
	private final MetricRegistry metricRegistry;

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
		ResteasyClientStrategy.LOG.trace("receive ok message for {}", tag.getTagValue());
	}

	@Override
	public void failed(Throwable throwable) {
		final Counter counter = metricRegistry.counter("zulip-messages-failure-counter", tag);
		counter.inc();
		ResteasyClientStrategy.LOG.trace("receive fail message for {}", tag.getTagValue());
	}
}