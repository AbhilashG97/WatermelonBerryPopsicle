package abhilash.example.com.alertmelon.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

public class FireAlertService extends Service {

    private PubNub mPubNub;

    @Override
    public void onCreate() {
        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setSubscribeKey("sub-c-0bbe0cb0-e2b6-11e8-a575-5ee09a206989");
        pnConfiguration.setPublishKey("pub-c-957647d4-c417-4a35-969f-95d00a04a33f");
        pnConfiguration.setSecure(false);

        mPubNub = new PubNub(pnConfiguration);
        addListener();
    }

    /**
     * Listener for PubNum
     * Listens for fire alert
     */
    public void addListener() {
        mPubNub.addListener(new SubscribeCallback() {
            @Override
            public void status(PubNub pubnub, PNStatus status) {

            }

            @Override
            public void message(PubNub pubnub, PNMessageResult message) {

            }

            @Override
            public void presence(PubNub pubnub, PNPresenceEventResult presence) {

            }
        });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
