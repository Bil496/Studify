package demo;

import demo.model.Notification;
import demo.model.Payload;
import demo.model.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class NotificationSender {

    static AndroidPushNotificationsService androidPushNotificationsService = new AndroidPushNotificationsService();

    public static void sendNotification(User user, Notification notification, Payload payload) {
        Set<User> users = new HashSet<>();
        users.add(user);
        sendNotification(users, notification, payload);
    }

    @RequestMapping(value = "/send", method = RequestMethod.GET, produces = "application/json")
    public static void sendNotification(Set<User> users, Notification notification, Payload payload, User... exclude) {

        JSONObject requestBody = new JSONObject();

        JSONArray usersTokenList = new JSONArray();
        Set<User> usersExcluded = (HashSet) ((HashSet) users).clone();
        usersExcluded.removeAll(Arrays.asList(exclude));
        for (User user : usersExcluded) {
            if (user.getToken() != null)
                usersTokenList.put(user.getToken());
        }
        if (usersTokenList.length() == 0)
            return;

        try {
            requestBody.put("registration_ids", usersTokenList);

            JSONObject bodysNotification = new JSONObject();
            bodysNotification.put("title", notification.getTitle());
            bodysNotification.put("body", notification.getMessage());
            //requestBody.put("notification", bodysNotification);

            JSONObject data = new JSONObject();
            data.put("notification", bodysNotification.toString());
            data.put("type", payload.getType().toString());
            if (payload.getData() != null)
                data.put("payload", payload.getData().toString());
            requestBody.put("data", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HttpEntity<String> request = new HttpEntity<>(requestBody.toString());

        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            String firebaseResponse = pushNotification.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
