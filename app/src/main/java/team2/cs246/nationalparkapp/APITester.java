package team2.cs246.nationalparkapp;

import android.app.Activity;
import android.util.Log;

import java.lang.ref.WeakReference;

public class APITester implements Runnable {
    private final String TAG = "APITester";
    private WeakReference<Activity> activityRef;

    public APITester(Activity activity) {
        this.activityRef = new WeakReference<Activity>(activity);
    }

    @Override
    public void run() {
        Park park = API.getParkByParkCode("acad");
        //Log.d(TAG, park.toString());
    }
}
