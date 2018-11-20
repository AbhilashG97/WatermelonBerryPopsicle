package abhilash.example.com.alertmelon.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import abhilash.example.com.alertmelon.R;
import abhilash.example.com.alertmelon.singleton.adapter.LogAdapter;
import abhilash.example.com.alertmelon.logrecyclerview.LogListAdapter;
import abhilash.example.com.alertmelon.utility.TinyDB;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LogsActivity extends AppCompatActivity {

    @BindView(R.id.rv_logs)
    RecyclerView logRecyclerView;

    private LogListAdapter logListAdapter;
    private LogAdapter logAdapter;
    private TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        ButterKnife.bind(this);
        tinyDB = new TinyDB(this);

        initializeRecyclerView();
    }

    public void initializeRecyclerView() {
        logAdapter = LogAdapter.getInstance();

        //TODO: Deserialize logs from shared preferences and store it in temperatureList

//        ArrayList<String> tempList = new ArrayList<>();
//
//        tempList.add("Log 1");
//        tempList.add("Log 2");
//        tempList.add("Log 3");
//        tempList.add("Log 4");
//        tempList.add("Log 5");

        if(logAdapter.getLogList().size() != 0) {
            tinyDB.putListString("Log list", logAdapter.getLogList());
            logListAdapter = new LogListAdapter(logAdapter.getLogList());
            //logListAdapter = new LogListAdapter(tempList);
            logListAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "No Logs!", Toast.LENGTH_LONG).show();
        }
        //logListAdapter = new LogListAdapter(tempList);
        logRecyclerView.setItemAnimator(new DefaultItemAnimator());
        logRecyclerView.setAdapter(logListAdapter);
    }

}
