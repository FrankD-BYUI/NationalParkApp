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
        Park park = ParkRepository.loadParkByParkCode("acad");
        Log.d(TAG, park.toString());

        List<Park> parkList = ParkRepository.loadParksByName("Grand Canyon");
        parkList.forEach((myPark -> {
            Log.d(TAG, myPark.toString());
        }));

        List<Park> parkList2 = API.getParksByState("dc");
        parkList2.forEach((myPark -> {
            Log.d(TAG, myPark.toString());
        }));
    }
}
