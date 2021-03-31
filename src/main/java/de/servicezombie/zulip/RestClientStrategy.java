package de.servicezombie.zulip;

public interface RestClientStrategy {

	void send(ZulipMessage message);
	
}
