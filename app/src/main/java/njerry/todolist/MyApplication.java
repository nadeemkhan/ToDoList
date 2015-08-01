package njerry.todolist;

import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by njerry on 8/2/15.
 */
public class MyApplication extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "3MS3XVtUTlShrIvhomPRUIXqyH0G2DnZWlJ1Ryru",
                "hFLyNZudqvG75OpjXwEotmCW2gErcHuGFv4CKoKX");

        ParseObject.registerSubclass(Job.class);



    }
}
