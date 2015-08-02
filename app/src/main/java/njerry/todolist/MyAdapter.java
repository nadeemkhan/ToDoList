package njerry.todolist;



import java.util.List;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<Job> {
    private Context mContext;
    private List<Job> mJobs;

    public MyAdapter(Context context, List<Job> objects) {
        super(context, R.layout.job_row_item, objects);
        this.mContext = context;
        this.mJobs = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            convertView = mLayoutInflater.inflate(R.layout.job_row_item, null);
        }

        Job job = mJobs.get(position);

        TextView descriptionView = (TextView) convertView.findViewById(R.id.job_description);

        descriptionView.setText(job.getDescription());

        if(job.isCompleted()){
            descriptionView.setPaintFlags(descriptionView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else{
            descriptionView.setPaintFlags(descriptionView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        return convertView;
    }

}
