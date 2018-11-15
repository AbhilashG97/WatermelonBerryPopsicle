package abhilash.example.com.alertmelon.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import abhilash.example.com.alertmelon.R;
import butterknife.ButterKnife;

public class LogsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);

        ButterKnife.bind(this);
    }
}
