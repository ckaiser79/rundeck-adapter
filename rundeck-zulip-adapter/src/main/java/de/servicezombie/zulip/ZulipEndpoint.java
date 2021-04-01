package de.servicezombie.zulip;

/**
 * Zulip authentication and connection informations.
 */
public class ZulipEndpoint {

	private final String user;
	private final String token;
	private final String endpoint;

	public ZulipEndpoint(final String user, final String token, final String endpoint) {
		this.user = user;
		this.token = token;
		this.endpoint = endpoint;
	}

	@Override
	public String toString() {
		return "ZulipEndpoint [user=" + user + ", token=" + (token != null) + ", endpoint=" + endpoint + "]";
	}

	public String getUser() {
		return user;
	}

	public String getToken() {
		return token;
	}

	public String getEndpoint() {
		return endpoint;
	}

}
