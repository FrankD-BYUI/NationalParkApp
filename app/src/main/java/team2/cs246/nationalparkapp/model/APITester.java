package team2.cs246.nationalparkapp.model;

import android.app.Activity;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

public class APITester implements Runnable {
    private final String TAG = "APITester";
    private WeakReference<Activity> activityRef;

    public APITester(Activity activity) {
        this.activityRef = new WeakReference<Activity>(activity);
    }

    @Override
    public void run() {
        /*Park park = API.getParkByParkCode("acad");
        Log.d(TAG, park.toString());*/

        List<Park> parkList = API.getParksByName("Grand Canyon");
        parkList.forEach((park -> {
            Log.d(TAG, park.toString());
        }));

        /*List<Park> parkList = API.getParksByState("dc");
        parkList.forEach((park -> {
            Log.d(TAG, park.toString());
        }));*/
    }
}
