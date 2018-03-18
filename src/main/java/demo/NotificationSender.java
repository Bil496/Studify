package demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import demo.model.Notification;
import demo.model.Payload;
import demo.model.User;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

public class NotificationSender {

    @Autowired
    static
    AndroidPushNotificationsService androidPushNotificationsService;
    
    public static void sendNotification(User user, Notification notification, Payload payload) {
	Set<User> users = new HashSet<>();
	users.add(user);
	sendNotification(users, notification, payload);
    }

    @RequestMapping(value = "/send", method = RequestMethod.GET, produces = "application/json")
    public static void sendNotification(Set<User> users, Notification notification, Payload payload) {

        JSONObject requestBody = new JSONObject();

        JSONArray usersTokenList = new JSONArray();
        for (User user: users) {
            usersTokenList.put(user.getToken());
        }
        requestBody.put("registration_ids", usersTokenList);

        JSONObject bodysNotification = new JSONObject();
        bodysNotification.put("title", notification.getTitle());
        bodysNotification.put("body", notification.getMessage());
        requestBody.put("notification", bodysNotification);

        JSONObject data = new JSONObject();
        data.put("type", payload.getType());
        data.put("payload", payload.getData());
        requestBody.put("data", data);

        HttpEntity<String> request = new HttpEntity<>(requestBody.toString());

        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            String firebaseResponse = pushNotification.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    
}
