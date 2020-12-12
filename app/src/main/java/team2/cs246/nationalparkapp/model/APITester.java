package team2.cs246.nationalparkapp.model;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class APITester implements Runnable {
    private final String TAG = "APITester";
    private WeakReference<Activity> activityRef;
    private boolean clearData;
    private boolean populateFavsVis;

    public APITester(Activity activity, boolean clearData, boolean populateFavsVis) {
        this.activityRef = new WeakReference<Activity>(activity);
        this.clearData = clearData;
        this.populateFavsVis = populateFavsVis;
    }

    @Override
    public void run() {
        if (!clearData && !populateFavsVis) {
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
        } else if(clearData) {
            //clear out data
            clearSharedPreferences();
        } else if(populateFavsVis) {
            Log.d(TAG, "Populating Favorites...");
            List<Park> favorites = new ArrayList<Park>();
            favorites.add(ParkRepository.loadParkByParkCode(activityRef, "grca"));
            favorites.add(ParkRepository.loadParkByParkCode(activityRef, "acad"));
            favorites.add(ParkRepository.loadParkByParkCode(activityRef, "agfo"));
            favorites.add(ParkRepository.loadParkByParkCode(activityRef, "alka"));
            favorites.add(ParkRepository.loadParkByParkCode(activityRef, "alca"));
            ParkRepository.saveFavorites(activityRef, favorites);

            Log.d(TAG, "Populating Visited parks...");
            List<Park> visited = new ArrayList<Park>();
            visited.add(ParkRepository.loadParkByParkCode(activityRef, "alpo"));
            visited.add(ParkRepository.loadParkByParkCode(activityRef, "anac"));
            visited.add(ParkRepository.loadParkByParkCode(activityRef, "apis"));
            visited.add(ParkRepository.loadParkByParkCode(activityRef, "appa"));
            visited.add(ParkRepository.loadParkByParkCode(activityRef, "badl"));
            ParkRepository.saveVisited(activityRef, visited);
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
