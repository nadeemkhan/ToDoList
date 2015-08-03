package njerry.todolist;

import android.annotation.TargetApi;
import android.graphics.Paint;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {
    private EditText mTaskInput;
    private ListView mListView;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTaskInput = (EditText) findViewById(R.id.task_input);
        mListView = (ListView) findViewById(R.id.task_list);

        mAdapter = new MyAdapter(this, new ArrayList<Job>());
        mListView.setAdapter(mAdapter);

        updateData();

        mListView.setOnItemClickListener(this);

    }

        public void createTask(View v) {
            if (mTaskInput.getText().length() > 0) {
                Job j = new Job();
                j.setDescription(mTaskInput.getText().toString());
                j.setCompleted(false);
                j.saveEventually();
                mTaskInput.setText("");
                mAdapter.insert(j, 0);
            }
        }



    public void updateData(){
        ParseQuery<Job> query = ParseQuery.getQuery(Job.class);
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_THEN_NETWORK);
        query.findInBackground(new FindCallback<Job>() {

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void done(List<Job> list, com.parse.ParseException e) {
                if(list != null){
                    mAdapter.clear();
                    mAdapter.addAll(list);
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Job job = mAdapter.getItem(position);
        TextView taskDescription = (TextView) view.findViewById(R.id.job_description);

        job.setCompleted(!job.isCompleted());

        if(job.isCompleted()){
            taskDescription.setPaintFlags(taskDescription.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            taskDescription.setPaintFlags(taskDescription.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        job.saveEventually();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
