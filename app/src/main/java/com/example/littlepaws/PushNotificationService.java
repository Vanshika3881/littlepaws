package com.example.littlepaws;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class PushNotificationService extends FirebaseMessagingService {

    @SuppressLint({"NewApi", "MissingPermission"})
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        String title =message.getNotification().getTitle();
        String text=message.getNotification().getBody();
        final String CHANNEL_ID="HEADS_UP_NOTIFICATION";
        NotificationChannel channel=new NotificationChannel(
                CHANNEL_ID,
                "Heads Up Notification",
                NotificationManager.IMPORTANCE_HIGH
        );
        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        Notification.Builder notification=new Notification.Builder(this,CHANNEL_ID)
                .setContentTitle(title).setContentText(text).setSmallIcon(R.drawable.ic_launcher_background).setAutoCancel(true);
        NotificationManagerCompat.from(this).notify(1,notification.build());
        super.onMessageReceived(message);
    }
}