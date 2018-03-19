package demo;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;

public class AndroidPushNotificationsService {
    private static final String FIREBASE_SERVER_KEY = "AAAAhz4Ewxo:APA91bGQlN138cNpx_GzCFlQmKYTI5W03-4zoslN2Lb4THeyg6NEzW6qD70L62np9K-U_ClwqUcZ1FxUXUlkrHVxNVBuep6IP9WSyNwD2HCiOQjo2fcJ_EATzJrL6jevVNClX3nynr7R";
    private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

    @Async
    public CompletableFuture<String> send(HttpEntity<String> entity) {

        RestTemplate restTemplate = new RestTemplate();

        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
        restTemplate.setInterceptors(interceptors);

        String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);

        return CompletableFuture.completedFuture(firebaseResponse);
    }
}
