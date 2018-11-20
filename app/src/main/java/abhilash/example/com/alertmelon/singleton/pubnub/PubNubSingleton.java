package abhilash.example.com.alertmelon.singleton.pubnub;

import android.util.Log;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;

public class PubNubSingleton {

    private static PubNub mPubNub;
    public final static String ALARM_CHANNEL = "alarm";
    public final static String TEMPERATURE_CHANNEL = "temperature";
    public final static String USER_SETTINGS_CHANNEL = "user_settings";


    private PubNubSingleton() {

    }

    public static PubNub getInstance() {
        if(mPubNub == null) {
            PNConfiguration pnConfiguration = new PNConfiguration();
            pnConfiguration.setSubscribeKey("sub-c-0bbe0cb0-e2b6-11e8-a575-5ee09a206989");
            pnConfiguration.setPublishKey("pub-c-957647d4-c417-4a35-969f-95d00a04a33f");
            pnConfiguration.setSecure(true);

            Log.i("PUBNUB INSTANCE", "Instance created");

            mPubNub = new PubNub(pnConfiguration);
            return mPubNub;
        }

        return mPubNub;
    }

}
