package de.servicezombie.rundeck;

/**
 * implemented by modules, used to delegate notification messages 
  */
public interface NotificationService {

	/** . */
	void onReceive(Notification notification);
	
}
