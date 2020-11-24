package team2.cs246.nationalparkapp.model;

import android.util.Log;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class API {
    private static String API_KEY = "KkwsP6yUTb9qxUieGKy0doP8lcju2oim8qVDvblf";
    private static String BASE_URL = "https://developer.nps.gov/api/v1/";
    private static String TAG = "API";

    /**
     *
     * @param parkCode
     * @return          the first park that matches the given park code
     */
    public static Park getParkByParkCode(String parkCode) {
        String url;
        String response;
        ParkWrapper parkWrapper;
        Gson gson = new Gson();
        Park park = null;

        url = BASE_URL + "parks?parkCode=" + parkCode + "&api_key=" + API_KEY;
        response = HTTPHelper.readHTTP(url);
        //Log.d(TAG, response);

        if (response != null) {
            parkWrapper = gson.fromJson(response, ParkWrapper.class);
            park = parkWrapper.getParks().get(0);
        } else {
            Log.d(TAG, "No API data received");
        }

        return park;
    }

    /**
     * Searches for the query term anywhere in the Park's data. Words separated by
     * a space are searched individually, put quotes around the words to search for
     * them together (Exampel: grand canyon returns 48 results, "grand canyon" returns 3)
     *
     * @param query The string used to query the API
     * @return      A list of parks returned by the API
     */
    public static List<Park> getParksByQuery(String query) {
        String url;
        String urlQuery;
        String response;
        ParkWrapper parkWrapper;
        Gson gson = new Gson();
        List<Park> parkList = null;

        try {
            urlQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, e.getMessage());
            return null;
        }

        url = BASE_URL + "parks?q=" + urlQuery + "&api_key=" + API_KEY;
        response = HTTPHelper.readHTTP(url);
        //Log.d(TAG, response);

        if (response != null) {
            parkWrapper = gson.fromJson(response, ParkWrapper.class);
            parkList = parkWrapper.getParks();
        } else {
            Log.d(TAG, "No API data received");
        }

        return parkList;
    }

    /**
     * Simply calls getParksByQuery then filters the list to only parks that
     * have the search query in the full park name.
     * @param query The string used to query the API
     * @return      A list of parks that contain the query in the full name
     */
    public static List<Park> getParksByName(String query) {
        List<Park> parkList = getParksByQuery(query);
        List<Park> filteredList = new ArrayList<Park>();

        if (parkList != null) {
            parkList.forEach((park) -> {
                if (park.getFullName().contains(query)) {
                    filteredList.add(park);
                }
            });
        }

        return filteredList;
    }

    /**
     * Searches for parks in a given state.
     * @param state either a 2 letter state code or state name (case insensitive)
     * @return an ArrayList of Parks in the specified state
     */
    public static List<Park> getParksByState(String state) {
        String url;
        String urlState;
        String response;
        ParkWrapper parkWrapper;
        Gson gson = new Gson();
        List<Park> parkList;
        String stateCode = StateHelper.getStateCode(state);

        if (stateCode == null) {
            Log.d(TAG, "null state code");
            return null;
        }

        try {
            urlState = URLEncoder.encode(stateCode, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, e.getMessage());
            return null;
        }

        url = BASE_URL + "parks?q=" + urlState + "&api_key=" + API_KEY;
        response = HTTPHelper.readHTTP(url);
        //Log.d(TAG, response);

        if (response != null) {
            parkWrapper = gson.fromJson(response, ParkWrapper.class);
            parkList = parkWrapper.getParks();
        } else {
            Log.d(TAG, "No API data received");
            return null;
        }

        return parkList;
    }
}
