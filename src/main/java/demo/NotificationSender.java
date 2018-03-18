package demo;

import java.util.HashSet;
import java.util.Set;

import demo.model.Notification;
import demo.model.Payload;
import demo.model.User;

public class NotificationSender {
    
    public static void sendNotification(User user, Notification notification, Payload payload) {
	Set<User> users = new HashSet<>();
	users.add(user);
	sendNotification(users, notification, payload);
    }
    
    public static void sendNotification(Set<User> users, Notification notification, Payload payload) {
	// TODO implement using firebase to send notification and its payload to given users.
    }
    
}
