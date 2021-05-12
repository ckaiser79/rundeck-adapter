package de.servicezombie.rundeck;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.Form;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * log rest client request data 
 */
public class LoggingRestClientFilter implements ClientRequestFilter {
    private static final Logger LOG = LoggerFactory.getLogger(LoggingRestClientFilter.class);

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
    	
    	LOG.info("call {} {}", requestContext.getMethod(), requestContext.getUri());
    	
    	final Object entity = requestContext.getEntity();
    	if(entity instanceof Form) {
    		LOG.debug(((Form)entity).asMap().toString());	
    	}
    	else {
    		LOG.debug(requestContext.getEntity().toString());
    	}
        
    }
}
