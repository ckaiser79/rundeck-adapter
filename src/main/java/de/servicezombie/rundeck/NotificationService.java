package de.servicezombie.rundeck;

import de.servicezombie.rundeck.rest.Notification;

public interface NotificationService {

	void onReceive(Notification notification);
	
}
