package njerry.todolist;

/**
 * Created by njerry on 8/2/15.
 */
import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Job")
public class Job extends ParseObject{
    public Job(){

    }

    public boolean isCompleted(){
        return getBoolean("completed");
    }

    public void setCompleted(boolean complete){
        put("completed", complete);
    }

    public String getDescription(){
        return getString("description");
    }

    public void setDescription(String description){
        put("description", description);
    }
}

