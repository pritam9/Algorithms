package app.one.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


import org.jose4j.json.internal.json_simple.JSONObject;

import com.auth0.jwt.internal.org.apache.commons.io.IOUtils;
import com.google.gson.Gson;

import app.one.model.Notification;



public class PushNotification {
	private static String API_KEY ="AIzaSyDXc-CTw7DZF0WrJdqlpABkJNFSQ4zBnLs";
	public static void send(String to,String msg){
		try {
					Notification notification = new Notification();
					notification.setTitle("Server");
					notification.setBody(msg);
					notification.setIcon("No_id");
		            // Prepare JSON containing the GCM message content. What to send and where to send.
		            JSONObject jGcmData = new JSONObject();
		            JSONObject jData = new JSONObject();
		            jData.put("title", "From My Server");
		            jData.put("text", msg);

		            jGcmData.put("to", to);

		            // What to send in GCM message.
		            jGcmData.put("notification", jData);

		            // Create connection to send GCM Message request.
		            URL url = new URL("https://fcm.googleapis.com/fcm/send");
		            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		            conn.setRequestProperty("Authorization", "key=" + API_KEY);
		            conn.setRequestProperty("Content-Type", "application/json");
		            conn.setRequestMethod("POST");
		            conn.setDoOutput(true);

		            // Send GCM message content.
		            OutputStream outputStream = conn.getOutputStream();
		            outputStream.write(jGcmData.toString().getBytes());

		           // System.out.println("JSON OBject is - "+new Gson().toJson(Notification.testMessage));
		            
		            // Read GCM response.
		            InputStream inputStream = conn.getInputStream();
		            String resp = IOUtils.toString(inputStream);
		            System.out.println(resp);
		            System.out.println("Check your device/emulator for notification or logcat for " +
		                    "confirmation of the receipt of the GCM message.");
		        } catch (IOException e) {
		            System.out.println("Unable to send GCM message.");
		            System.out.println("Please ensure that API_KEY has been replaced by the server " +
		                    "API key, and that the device's registration token is correct (if specified).");
		            e.printStackTrace();
		        }
		}

}
