package in.vakrangee.vkms.utilities;

// Author: Shreayan Chaudhary
import java.io.*;
import org.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class NotifService {

    private final String FCM_NOTIFY_SERVER_URL = "https://fcm.googleapis.com/fcm/send";
    // App Auth Key
    private final String APP_AUTH_KEY = "AIzaSyDjb5WCAK9TnFoGjr0viFn83gLWUf4-YVU";
    final String PRIORITY_NORMAL = "normal";
    final String PRIORITY_HIGH = "high";

//    /**
//     * Send Test Notification
//     *
//     * @param fcmId
//     * @return
//     * @throws IOException
//     */
    public boolean testNotification(String fcmId) throws IOException {
        System.out.println("method called");
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(FCM_NOTIFY_SERVER_URL);
        post.setHeader("Content-type", "application/json");
        post.setHeader("Authorization", "key=" + APP_AUTH_KEY);
        // Notification Message
        JSONObject message = new JSONObject();
        message.put("to", fcmId);
        message.put("priority", PRIORITY_HIGH);
        JSONObject notification = new JSONObject();
        notification.put("title", "VAKRANGEE");
        notification.put("body", "Hello! Welcome to Vakrangee Kendra.");
        message.put("notification", notification);

        post.setEntity(new StringEntity(message.toString(), "UTF-8"));
        HttpResponse response = client.execute(post);
        System.out.println("Response: " + response.getStatusLine().getStatusCode());
        System.out.println("Message: " + message);
        return true;
    }
}