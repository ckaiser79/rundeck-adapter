package de.servicezombie.zulip;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.thymeleaf.context.Context;

import de.servicezombie.rundeck.Notification;
import de.servicezombie.thymeleaf.ThymeleadContextFactory;

/**
 * Make varaibles available in Thymeleaf templates.
 */
public class ZulipMessageThymeleafContextFactory implements ThymeleadContextFactory {

	private final ZulipRequest zulipMessage;
	private final Notification notification;
	private final Locale locale;
	
	/**
	 * Default Locale is US.
	 * 
	 * @param zulipMessage .
	 * @param notification .
	 */
	public ZulipMessageThymeleafContextFactory(final ZulipRequest zulipMessage, final Notification notification) {
		this(zulipMessage, notification, Locale.US);
	}
	
	/**
	 * 
	 * @param zulipMessage .
	 * @param notification .
	 * @param locale .
	 */
	public ZulipMessageThymeleafContextFactory(final ZulipRequest zulipMessage, final Notification notification, final Locale locale) {
		this.locale = locale;
		this.zulipMessage = zulipMessage;
		this.notification = notification;
	}
	
	
	@Override
	public Context createContext() {
		
		final Context context = new Context(locale);

		final Map<String, Object> variables = new HashMap<>();
		variables.put("message", zulipMessage);
		variables.put("notification", notification);
		context.setVariables(variables);

		return context;
	}

}
