package de.servicezombie.rundeck;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingRestClientFilter implements ClientRequestFilter {
    private static final Logger LOG = LoggerFactory.getLogger(LoggingRestClientFilter.class);

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        LOG.info(requestContext.getEntity().toString());
    }
}
