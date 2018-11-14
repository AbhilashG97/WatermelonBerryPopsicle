package abhilash.example.com.alertmelon.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.enums.PNStatusCategory;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

public class FireAlertService extends Service {

    private PubNub mPubNub;
    private final static String channelName= "watermelonChannel";
    private JsonElement recievedMessageObject;

    @Override
    public void onCreate() {
        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setSubscribeKey("sub-c-0bbe0cb0-e2b6-11e8-a575-5ee09a206989");
        pnConfiguration.setPublishKey("pub-c-957647d4-c417-4a35-969f-95d00a04a33f");
        pnConfiguration.setSecure(false);

        Log.i("SERVICE", "Service created");

        mPubNub = new PubNub(pnConfiguration);
        addListener();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        Log.i("SERVICE STARTED", "Service started");
        return START_STICKY;
    }

    /**
     * Listener for PubNum
     * Listens for fire alert
     */
    public void addListener() {
        mPubNub.addListener(new SubscribeCallback() {
            @Override
            public void status(PubNub pubnub, PNStatus status) {
                if (status.getCategory() == PNStatusCategory.PNUnexpectedDisconnectCategory) {
                    /**
                     * TODO: Handle event when radio/connectivity lost
                     */
                } else if (status.getCategory() == PNStatusCategory.PNConnectedCategory) {
                    // Connected to channel
                    Log.i("CONNECTED TO CHANNEL", "Yay!Connected to channel");
                } else if (status.getCategory() == PNStatusCategory.PNReconnectedCategory) {
                    /**
                     * TODO: Handle event when radio/connectivity is regained
                     */
                } else if (status.getCategory() == PNStatusCategory.PNDecryptionErrorCategory) {
                    /**
                     * TODO: Handle decryption event
                     */
                }
            }

            @Override
            public void message(PubNub pubnub, PNMessageResult message) {
                if (message.getChannel() != null) {
                    Log.i("CHANNEL MESSAGE", message.getChannel());
                } else {
                    Log.i("SUBSCRIPTION MESSAGE", message.getSubscription());
                }

                recievedMessageObject = message.getMessage();
                Log.i("RECEIVED MESSAGE", recievedMessageObject.toString());

                Log.i("MESSAGE CONTENT", message.getMessage()
                        .getAsJsonObject()
                        .get("watermelon").getAsString());
            }

            @Override
            public void presence(PubNub pubnub, PNPresenceEventResult presence) {

            }
        });
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}