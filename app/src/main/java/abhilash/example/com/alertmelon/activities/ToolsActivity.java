package abhilash.example.com.alertmelon.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.pubnub.api.PubNub;

import abhilash.example.com.alertmelon.R;
import abhilash.example.com.alertmelon.singleton.PubNubSingleton;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ToolsActivity extends AppCompatActivity {

    @BindView(R.id.switch_buzzer)
    Switch buzzerSwitch;

    @BindView(R.id.switch_alarm_LED)
    Switch ledAlarmSwitch;

    private PubNub mPubNub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);
        ButterKnife.bind(this);

        mPubNub = PubNubSingleton.getInstance();
        buzzerSwitch.setClickable(false);
        addSwitchListener();
    }

    public void addSwitchListener() {
        ledAlarmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isButtonChecked) {
                if(isButtonChecked) {
                    // Switch is turned on
                } else {
                    // Switch is turned off
                }
            }
        });
    }
}
