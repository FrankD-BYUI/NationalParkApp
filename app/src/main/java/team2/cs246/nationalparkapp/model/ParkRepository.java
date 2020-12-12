package team2.cs246.nationalparkapp.model;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * The repository is the one stop interaction with the model.
 * All requests for data are sent to the repository which then
 * retrieves the data from either local storage or the web API.
 *
 * functions that start with 'search' return a list of objects
 * meeting that criteria. Functions starting with 'load' return
 * a single park.
 */
public class ParkRepository {
    private static String TAG = "ParkRepository";

    public static Park loadParkByParkCode(WeakReference<Activity> activity, String parkCode) {
        Gson gson = new Gson();
        Park park;
        // Attempt to load saved data for this park
        String savedData = FileHelper.loadData(activity, parkCode);

        // if data was found, de-serialize it.
        // if nothing was found, get it from the NPS API and save it.
        if (savedData != null) {
            park = gson.fromJson(savedData, Park.class);
        } else {
            park = API.getParkByParkCode(parkCode);
            FileHelper.saveData(activity, parkCode, gson.toJson(park));
        }
        return park;
    }

    public static List<Park> searchParksByName(WeakReference<Activity> activity, String name) {
        Gson gson = new Gson();
        List<Park> parkList;

        // Attempt to load saved data for this park
        String savedData = FileHelper.loadData(activity, name.replaceAll(" ", "_").toLowerCase());

        // if data was found, de-serialize it.
        // if nothing was found, get it from the NPS API and save it.
        if (savedData != null) {
            Type parkListType = new TypeToken<ArrayList<Park>>(){}.getType();
            parkList = gson.fromJson(savedData, parkListType);
            Log.d(TAG, name.toLowerCase() + " was loaded. Length: " + String.valueOf(parkList.size()));
        } else {
            Log.d(TAG, name.toLowerCase() + " was not loaded.");
            parkList = API.getParksByName(name);
            FileHelper.saveData(activity, name.replaceAll(" ", "_").toLowerCase(), gson.toJson(parkList));
        }
        return parkList;
    }

    public static List<Park> searchParksByState(WeakReference<Activity> activity, String state) {
        Gson gson = new Gson();
        List<Park> parkList;
        // turn state into properly formatted, 2 letter state code
        String stateCode = StateHelper.getStateCode(state);
        // Attempt to load saved data for this park
        String savedData = FileHelper.loadData(activity, stateCode);

        // if data was found, de-serialize it.
        // if nothing was found, get it from the NPS API and save it.
        if (savedData != null) {
            Type parkListType = new TypeToken<ArrayList<Park>>(){}.getType();
            parkList = gson.fromJson(savedData, parkListType);
        } else {
            parkList = API.getParksByState(stateCode);
            FileHelper.saveData(activity, stateCode, gson.toJson(parkList));
        }
        return parkList;
    }

    public static List<Park> loadFavorites(WeakReference<Activity> activity) {
        Gson gson = new Gson();
        List<Park> favorites;

        // Attempt to load favorite parks
        String savedData = FileHelper.loadData(activity, "favorites");

        // if favorites was found, de-serialize it.
        // if nothing was found, create a new ArrayList<Park> and return it.
        if (savedData != null) {
            Type parkListType = new TypeToken<ArrayList<Park>>(){}.getType();
            favorites = gson.fromJson(savedData, parkListType);
            Log.d(TAG, "favorites was loaded. Length: " + String.valueOf(favorites.size()));
        } else {
            Log.d(TAG, "favorites was not found. Empty list returned.");
            favorites = new ArrayList<Park>();
        }
        return favorites;
    }

    public static void saveFavorites(WeakReference<Activity> activity, List<Park> favorites) {
        Gson gson = new Gson();
        Log.d(TAG, "Attempting to save favorites.");
        FileHelper.saveData(activity, "favorites", gson.toJson(favorites));
    }

    public static List<Park> loadVisited(WeakReference<Activity> activity) {
        Gson gson = new Gson();
        List<Park> visited;

        // Attempt to load visited parks
        String savedData = FileHelper.loadData(activity, "visited");

        // if visited was found, de-serialize it.
        // if nothing was found, create a new ArrayList<Park> and return it.
        if (savedData != null) {
            Type parkListType = new TypeToken<ArrayList<Park>>(){}.getType();
            visited = gson.fromJson(savedData, parkListType);
            Log.d(TAG, "visited was loaded. Length: " + String.valueOf(visited.size()));
        } else {
            Log.d(TAG, "visited was not found. Empty list returned.");
            visited = new ArrayList<Park>();
        }
        return visited;
    }

    public static void saveVisited(WeakReference<Activity> activity, List<Park> favorites) {
        Gson gson = new Gson();
        Log.d(TAG, "Attempting to save visited.");
        FileHelper.saveData(activity, "visited", gson.toJson(favorites));
    }
}
