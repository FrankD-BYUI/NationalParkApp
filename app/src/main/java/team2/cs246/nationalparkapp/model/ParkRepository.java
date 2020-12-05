package team2.cs246.nationalparkapp.model;

import android.app.Activity;
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
        String savedData = FileHelper.loadData(activity, name);

        // if data was found, de-serialize it.
        // if nothing was found, get it from the NPS API and save it.
        if (savedData != null) {
            Type parkListType = new TypeToken<ArrayList<Park>>(){}.getType();
            parkList = gson.fromJson(savedData, parkListType);
        } else {
            parkList = API.getParksByName(name);
            FileHelper.saveData(activity, name, gson.toJson(parkList));
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

    /*private static List<Park> searchParks(WeakReference<Activity> activity, String query) {
        Gson gson = new Gson();
        List<Park> parkList;
        // Attempt to load saved data for this park
        String savedData = FileHelper.loadData(activity, query);

        // if data was found, de-serialize it.
        // if nothing was found, get it from the NPS API and save it.
        if (savedData != null) {
            Type parkListType = new TypeToken<ArrayList<Park>>(){}.getType();
            parkList = gson.fromJson(savedData, parkListType);
        } else {
            parkList = API.getParksByName(query);
            FileHelper.saveData(activity, query, gson.toJson(parkList));
        }
        return parkList;
    }*/

    //TODO: Implement favorites
    public static List<Park> loadFavorites() {
        return null;
    }

    //TODO: Implement favorites
    public static boolean saveFavorites(List<Park> parkList) {
        return false;
    }
}
