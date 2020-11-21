package team2.cs246.nationalparkapp.model;

import android.util.Log;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class HTTPHelper {
    private static String TAG = "HTTPHelper";

    public static String readHTTP(String url) {
        URLConnection connection;
        InputStream response = null;
        String responseBody;

        try {
            connection = new URL(url).openConnection();
            response = connection.getInputStream();

        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
            return null;
        }

        if (response != null) {
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
