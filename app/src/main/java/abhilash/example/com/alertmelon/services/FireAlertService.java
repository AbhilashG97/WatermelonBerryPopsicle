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
import android.util.Log;
import android.widget.Toast;

import com.pubnub.api.PubNub;

import abhilash.example.com.alertmelon.MainActivity;
import abhilash.example.com.alertmelon.R;
import abhilash.example.com.alertmelon.singleton.PubNubSingleton;

public class FireAlertService extends Service {

    public static boolean isServiceRunning = false;
    public static final int NOTIFICATION_ID=543;
    private PubNub mPubNub;

    @Override
    public void onCreate() {
        mPubNub = PubNubSingleton.getInstance();
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
        createNotification();
        return START_STICKY;
    }


    public void createNotification() {
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
        mBuilder.setSmallIcon(R.drawable.ic_notification_warning)
                .setContentTitle("Temperature Alert")
                .setTicker(getResources().getString(R.string.app_name))
                .setContentText("Temperature has reached exceedingly high!")
                .setAutoCancel(true)
                .setContentIntent(contentIntent)
                .setOngoing(true);
        startForeground(NOTIFICATION_ID, notification);
    }

    public void addPubNubListener() {
        PubNubSingleton.addListener(mPubNub);
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
