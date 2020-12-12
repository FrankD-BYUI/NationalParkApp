package team2.cs246.nationalparkapp.model;

import android.util.Log;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

/**
 * This class is for interacting directly with the NPS API.
 * All methods are static to avoid the need for instantiating it.
 */
public class API {
    private static String API_KEY = "KkwsP6yUTb9qxUieGKy0doP8lcju2oim8qVDvblf";
    private static String BASE_URL = "https://developer.nps.gov/api/v1/";
    private static String TAG = "API";
    private static String LIMIT = "50";

    /**
     * Performs an API request for a single park based on it's offical park code.
     * @param parkCode  The official 4 letter park code assigned by NPS
     * @return          the first park that matches the given park code
     */
    public static Park getParkByParkCode(String parkCode) {
        String url;
        String response;
        ParkWrapper parkWrapper;
        Gson gson = new Gson();
        Park park = null;

        // build the URL and send the HTTP request
        url = BASE_URL + "parks?parkCode=" + parkCode + "&api_key=" + API_KEY;
        response = HTTPHelper.readHTTP(url);

        // if data was returned, serialize it and return it.
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
     * a space are treated as "or". Further filtering is likely required.
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

        // encode the query term to be included in the URL
        try {
            urlQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, e.getMessage());
            return null;
        }

        // build the URL
        url = BASE_URL + "parks?";

        // this lets the request work if an empty string was passed in as the
        // query term... I'm not sure how useful it is, but it works.
        if (query != ""){
            url += "q=" + urlQuery;
        }
        url += "&limit=" + LIMIT + "&api_key=" + API_KEY;

        response = HTTPHelper.readHTTP(url);

        // if data was received, serialize it and return it.
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
     * @param name The string used to query the API
     * @return      A list of parks that contain the query in the full name
     */
    public static List<Park> getParksByName(String name) {
        List<Park> parkList = getParksByQuery(name);
        List<Park> filteredList = new ArrayList<Park>();

        // builds a filtered list only containing parks that have the query
        // term in the park's full name.
        if (parkList != null) {
            parkList.forEach((park) -> {
                if (containsIgnoreCase(park.getFullName(), name)) {
                    filteredList.add(park);
                }
            });
        }

        return filteredList;
    }

    /**
     * Searches for parks in a given state.
     * @param state either a 2 letter state code or state name (case insensitive)
     * @return      an ArrayList of Parks in the specified state
     */
    public static List<Park> getParksByState(String state) {
        String url;
        String urlState;
        String response;
        ParkWrapper parkWrapper;
        Gson gson = new Gson();
        List<Park> parkList;
        List<Park> filteredList = new ArrayList<Park>();
        //use the StateHelper to make sure you have a valid state code
        String stateCode = StateHelper.getStateCode(state);

        // if StateHelper returned null, pass it back along
        if (stateCode == null) {
            Log.d(TAG, "null state code");
            return null;
        }

        // URL encode the state code... just in case
        try {
            urlState = URLEncoder.encode(stateCode, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, e.getMessage());
            return null;
        }

        // build the URL and send the request.
        url = BASE_URL + "parks?stateCode=" + urlState + "&api_key=" + API_KEY;
        response = HTTPHelper.readHTTP(url);


        if (response != null) {
            parkWrapper = gson.fromJson(response, ParkWrapper.class);
            parkList = parkWrapper.getParks();

            // filter park list to only include parks that have the state code in their states property
            parkList.forEach((park) -> {
                if (containsIgnoreCase(park.getStates(), stateCode)) {
                    filteredList.add(park);
                }
            });
            parkList = filteredList;
        } else {
            Log.d(TAG, "No API data received");
            return null;
        }

        return parkList;
    }
}
