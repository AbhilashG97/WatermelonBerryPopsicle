package abhilash.example.com.alertmelon.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import abhilash.example.com.alertmelon.R;
import abhilash.example.com.alertmelon.logrecyclerview.LogItem;
import abhilash.example.com.alertmelon.logrecyclerview.LogListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LogsActivity extends AppCompatActivity {

    @BindView(R.id.rv_logs)
    RecyclerView logRecyclerView;

    private LogListAdapter logListAdapter;
    private ArrayList<LogItem> logItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        ButterKnife.bind(this);

        logItemList = new ArrayList<>();

        /**
         * TODO: Deserialize logs from shared preferences and store it in logItemList
         */

        logListAdapter = new LogListAdapter(logItemList);
        logRecyclerView.setItemAnimator(new DefaultItemAnimator());
        logRecyclerView.setAdapter(logListAdapter);
    }

}
