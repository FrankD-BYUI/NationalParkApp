package team2.cs246.nationalparkapp.model;

import android.util.Log;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * This class performs the HTTP request
 */
public class HTTPHelper {
    private static String TAG = "HTTPHelper";

    /**
     * Takes in a URL and returns the JSON data that the API returned
     * @param url   String representation of the request URL
     * @return      The JSON data returned by the server
     */
    public static String readHTTP(String url) {
        URLConnection connection;
        InputStream response = null;
        String responseBody;

        // attempt to make a connection and capture the response
        try {
            connection = new URL(url).openConnection();
            response = connection.getInputStream();
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
            return null;
        }

        if (response != null) {
            // attempt to parse the JSON data out of the HTTP response
            try (Scanner scanner = new Scanner(response)) {
                responseBody = scanner.useDelimiter("\\A").next();
                return responseBody;
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
                return null;
            }
        }
        return null;
    }
}
