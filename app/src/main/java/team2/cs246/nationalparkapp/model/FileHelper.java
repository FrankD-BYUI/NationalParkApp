package team2.cs246.nationalparkapp.model;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.lang.ref.WeakReference;

public class FileHelper {
    private static final String TAG = "FileHelper";

    public static void saveData(WeakReference<Activity> activityRef, String key, String data) {
        Log.d(TAG, "Attempting to save data: " + key);

        //create an instance of the SharedPreferences, create an editor, add the data, apply the edit
        final Activity activity = activityRef.get();
        if (activity != null) {
            SharedPreferences sharedPref =
                    PreferenceManager.getDefaultSharedPreferences(activity.getBaseContext());
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(key, data);
            editor.apply();
        }
    }

    public static String loadData(WeakReference<Activity> activityRef, String key) {
        String data = null;

        Log.d(TAG, "Attempting to load data: " + key);
        final Activity activity = activityRef.get();
        if (activity != null) {
            SharedPreferences sharedPref =
                    PreferenceManager.getDefaultSharedPreferences(activity.getBaseContext());
            data = sharedPref.getString(key, null);
        }
        return data;
    }
}
