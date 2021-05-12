package de.servicezombie.rundeck;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * intercept requests if token is missing and configured
 */
@WebFilter(urlPatterns = "/notification/*")
public class ApiTokenFilter implements Filter {
	
	@Inject
	@ConfigProperty(name = "rundeck.apitoken", defaultValue = "")
	private String apitoken;
	
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {

    	final String token = servletRequest.getParameter("token");
    	final boolean isValidToken = "".equals(apitoken) || apitoken.equals(token);
    	
    	if(isValidToken) {
    		filterChain.doFilter(servletRequest, servletResponse);	
    	}
    	else {
    		HttpServletResponse response = (HttpServletResponse) servletResponse;
    		response.sendError(403, "provide correct '?token=[secret-value]' as query parameter");
    	}
    	
    }

}
