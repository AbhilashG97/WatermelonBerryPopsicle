package abhilash.example.com.alertmelon.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

import abhilash.example.com.alertmelon.MainActivity;
import abhilash.example.com.alertmelon.R;
import abhilash.example.com.alertmelon.activities.NotificationActivity;
import abhilash.example.com.alertmelon.singleton.PubNubSingleton;

public class FireAlertService extends Service {

    public static boolean isServiceRunning = false;
    public static final int NOTIFICATION_ID=543;
    private PubNub mPubNub;

    @Override
    public void onCreate() {
        mPubNub = PubNubSingleton.getInstance();
        createAlertNotification();
    }

    /**
     * Start service
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        Log.i("SERVICE STARTED", "Service started");
        createServiceNotification();
        return START_STICKY;
    }

    /**
     * Listener for PubNub singleton instance
     */
    public void addPubNubListener() {
        mPubNub.addListener(new SubscribeCallback() {
            @Override
            public void status(PubNub pubnub, PNStatus status) {
                // Subscribe to channel
            }

            @Override
            public void message(PubNub pubnub, PNMessageResult message) {

            }

            @Override
            public void presence(PubNub pubnub, PNPresenceEventResult presence) {

            }
        });
    }

    /**
     * Displays the service that is running in the foreground
     */
    public void createServiceNotification() {
        if (isServiceRunning) return;
        isServiceRunning = true;

        android.support.v4.app.NotificationCompat.Builder mBuilder;

        NotificationManager notificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String chanel_id = "3000";
            CharSequence name = "Channel Name";
            String description = "Chanel Description";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel mChannel = new NotificationChannel(chanel_id, name, importance);
            mChannel.setDescription(description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.BLUE);
            notificationManager.createNotificationChannel(mChannel);
            mBuilder = new android.support.v4.app.NotificationCompat.Builder(this, chanel_id);
        } else {
            mBuilder = new android.support.v4.app.NotificationCompat.Builder(this);
        }

        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setAction("MAIN");
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(contentIntent);
        Notification notification = mBuilder.build();
        notification.flags = notification.flags | Notification.FLAG_NO_CLEAR;
        mBuilder.setAutoCancel(true)
                .setContentIntent(contentIntent)
                .setOngoing(true);
        startForeground(NOTIFICATION_ID, notification);
    }

    /**
     * Notifies the user when the temperature reaches abnormally high
     */
    public void createAlertNotification() {
        android.support.v4.app.NotificationCompat.Builder mBuilder;

        NotificationManager notificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String chanel_id = "3000";
            CharSequence name = "Fire Alert Channel";
            String description = "Sends a notification when the temperature becomes exceedingly high";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel mChannel = new NotificationChannel(chanel_id, name, importance);
            mChannel.setDescription(description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.BLUE);
            notificationManager.createNotificationChannel(mChannel);
            mBuilder = new android.support.v4.app.NotificationCompat.Builder(this, chanel_id);
        } else {
            mBuilder = new android.support.v4.app.NotificationCompat.Builder(this);
        }

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        PendingIntent dismissIntent = NotificationActivity.getDismissIntent(1001,
                this);

        mBuilder.setContentIntent(contentIntent);
        mBuilder.setSmallIcon(R.drawable.ic_notification_warning)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentTitle("Temperature Alert")
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setTicker(getResources().getString(R.string.app_name))
                .setContentText("Temperature has reached exceedingly high!")
                .setOngoing(true)
                .addAction(R.drawable.ic_notification_warning, "DISMISS", dismissIntent);
        notificationManager.notify(1001, mBuilder.build());
    }


    @Override
    public void onDestroy() {
        isServiceRunning = false;
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
