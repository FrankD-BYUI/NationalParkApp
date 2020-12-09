package team2.cs246.nationalparkapp.model;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.lang.ref.WeakReference;

public class APITester implements Runnable {
    private final String TAG = "APITester";
    private WeakReference<Activity> activityRef;
    private boolean clearData;

    public APITester(Activity activity, boolean clearData) {
        this.activityRef = new WeakReference<Activity>(activity);
        this.clearData = clearData;
    }

    @Override
    public void run() {
        if (!clearData) {
            Park park = ParkRepository.loadParkByParkCode(this.activityRef, "acad");
            Log.d(TAG, park.toString());

            /*List<Park> parkList = ParkRepository.searchParksByName(this.activityRef, "Grand Canyon");
            parkList.forEach((myPark -> {
                Log.d(TAG, myPark.toString());
            }));*/

            /*List<Park> parkList2 = ParkRepository.searchParksByState(this.activityRef, "DC");
            parkList2.forEach((myPark -> {
                Log.d(TAG, myPark.toString());
            })); */
        } else {
            //clear out data
            clearSharedPreferences();
        }
    }

    private void clearSharedPreferences() {
        Log.d(TAG, "Clearing stored data");
        final Activity activity = this.activityRef.get();
        if (activity != null) {
            SharedPreferences sharedPref =
                    PreferenceManager.getDefaultSharedPreferences(activity.getBaseContext());
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.clear();
            editor.apply();
        }
    }


}
