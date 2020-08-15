package com.hiddin.pushnotification;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidConfig.Priority;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

public class FcmClient {

	private static Logger logger = LoggerFactory.getLogger(FcmClient.class);

	public void sendJoke(Map<String, String> data) throws InterruptedException, ExecutionException {

		AndroidConfig androidConfig = AndroidConfig.builder().setTtl(Duration.ofMinutes(2).toMillis())
				.setCollapseKey("chuck").setPriority(Priority.HIGH)
				.setNotification(AndroidNotification.builder().setTag("chuck").build()).build();

		ApnsConfig apnsConfig = ApnsConfig.builder()
				.setAps(Aps.builder().setCategory("chuck").setThreadId("chuck").build()).build();

		Message message = Message.builder().putAllData(data).setTopic("chuck").setApnsConfig(apnsConfig)
				.setAndroidConfig(androidConfig).setNotification(Notification.builder().setTitle("Chuck Norris Joke")
						.setBody("A new Chuck Norris joke has arrived").build())
				.build();

		String response = FirebaseMessaging.getInstance().sendAsync(message).get();
		System.out.println("Sent message: " + response);
	}

	public static void sendPersonalMessage(String clientToken, Map<String, String> data)
			throws InterruptedException, ExecutionException {
		AndroidConfig androidConfig = AndroidConfig.builder().setTtl(Duration.ofMinutes(2).toMillis())
				.setCollapseKey("personal").setPriority(Priority.HIGH)
				.setNotification(AndroidNotification.builder().setTag("personal").build()).build();

		ApnsConfig apnsConfig = ApnsConfig.builder()
				.setAps(Aps.builder().setCategory("personal").setThreadId("personal").build()).build();

		Message message = Message.builder().putAllData(data).setToken(clientToken).setApnsConfig(apnsConfig)
				.setAndroidConfig(androidConfig)
				.setNotification(
						Notification.builder().setTitle("Hiddin Covid-19").setBody("A Personal Message").build())
				.build();

		String response = FirebaseMessaging.getInstance().sendAsync(message).get();
		System.out.println("Sent message: " + response);
	}

	public static void sendPersonalMessage(String clientToken, String data)
			throws InterruptedException, ExecutionException {
//		String imageUrl = "https://hiddin.net/img/favicon.png";
		AndroidConfig androidConfig = AndroidConfig.builder().setTtl(Duration.ofMinutes(2).toMillis())
				.setCollapseKey("hiddin").setPriority(Priority.HIGH)
				.setNotification(AndroidNotification.builder().setTag("hiddin").build()).build();

		ApnsConfig apnsConfig = ApnsConfig.builder()
				.setAps(Aps.builder().setCategory("hiddin").setThreadId("hiddin").build()).build();

		Message message = Message.builder().setToken(clientToken).setApnsConfig(apnsConfig)
				.setAndroidConfig(androidConfig)
				.setNotification(
						Notification.builder()/* .setImage(imageUrl) */.setTitle("Hiddin Covid-19").setBody(data).build())
				.build();

		String response = FirebaseMessaging.getInstance().sendAsync(message).get();
		System.out.println("Sent message: " + response);
	}

	public static String sendNotification(String message, String deviceToken, long unreadCount) {
		Map<String, Long> deviceTokens = new HashMap<>();
		deviceTokens.put(deviceToken, unreadCount);
//		return sendNotifications(message, deviceTokens);
		try {
			sendPersonalMessage(deviceToken, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public static String sendNotifications(String message, Map<String, Long> deviceTokenMap) {
		try {
			int id = 0;
			Set<String> tokenSet = deviceTokenMap.keySet();
			for (String token : tokenSet) {

				System.out.println("Sending personal message to: " + token);
				Map<String, String> data = new HashMap<>();
				data.put("id", String.valueOf(++id));
				data.put("text", message);

				try {
					sendPersonalMessage(token, data);
				} catch (InterruptedException | ExecutionException e) {
					System.out.println("send personal message" + e.getMessage());
				}

			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return "success";
	}

}