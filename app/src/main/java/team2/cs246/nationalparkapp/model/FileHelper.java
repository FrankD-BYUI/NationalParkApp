package team2.cs246.nationalparkapp.model;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * This class handles the interaction with the shared storage
 */
public class FileHelper {
    private static final String TAG = "FileHelper";

    /**
     * Saves key value pairs using the SharedPreferences API
     * @param activityRef   A WeakReference to the activity attempting to save data
     * @param key           The key that will be used to identify the data
     * @param data          The String data to be stored
     */
    public static void saveData(WeakReference<Activity> activityRef, String key, String data) {
        Log.d(TAG, "Attempting to save data: " + key);

        // check to see if originating activity still exists
        final Activity activity = activityRef.get();
        if (activity != null) {
            // create an instance of the SharedPreferences
            SharedPreferences sharedPref =
                    PreferenceManager.getDefaultSharedPreferences(activity.getBaseContext());
            // create a preferences editor
            SharedPreferences.Editor editor = sharedPref.edit();
            // add the data to the editor
            editor.putString(key, data);
            // submit the edit
            editor.apply();
        }
    }

    /**
     * Retrieves saved data using the SharedPreferences API
     * @param activityRef   A WeakReference to the activity attempting to load data
     * @param key           The key that was used to identify the saved data
     * @return              The data that was saved
     */
    public static String loadData(WeakReference<Activity> activityRef, String key) {
        String data = null;

        Log.d(TAG, "Attempting to load data: " + key);
        // check to see if originating activity still exists
        final Activity activity = activityRef.get();
        if (activity != null) {
            // create an instance of the SharedPreferences
            SharedPreferences sharedPref =
                    PreferenceManager.getDefaultSharedPreferences(activity.getBaseContext());
            // retrieve the data, null is returned if key wasn't found.
            data = sharedPref.getString(key, null);
        }
        return data;
    }
}
