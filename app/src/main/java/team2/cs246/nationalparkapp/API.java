package team2.cs246.nationalparkapp;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

public class API {
    private static String API_KEY = "KkwsP6yUTb9qxUieGKy0doP8lcju2oim8qVDvblf";
    private static String BASE_URL = "https://developer.nps.gov/api/v1/";
    private static String TAG = "API";

    public static Park getParkByParkCode(String parkCode) {
        String url = BASE_URL + "parks?parkCode=" + parkCode + "&api_key=" + API_KEY;
        String response = HTTPHelper.readHTTP(url);
        Log.d(TAG, response);

        Gson gson = new Gson();
        Park park = null;

        //TODO: get this working
        /*if (response != null) {
            //park = gson.fromJson(response, Park.class);
            Map<String, String> map = gson.fromJson(response, new TypeToken<Map<String, String>>() {}.getType());
            //List<Park> list = gson.fromJson(map.get("data"), new TypeToken<List<Park>>() {}.getType());
            //park = list.get(0);

        } else {
            Log.d(TAG, "No API data received");
        }*/

        return park;
    }
}
