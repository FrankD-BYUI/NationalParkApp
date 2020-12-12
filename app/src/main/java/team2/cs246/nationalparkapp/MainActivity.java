package team2.cs246.nationalparkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import team2.cs246.nationalparkapp.model.APITester;
import team2.cs246.nationalparkapp.model.Park;
import team2.cs246.nationalparkapp.model.ParkRepository;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testAPI(View view) {
        new Thread(new APITester(this, false, false)).start();
    }

    public void clearStoredData(View view) {
        Log.d(TAG, "Clearing stored data");
        new Thread(new APITester(this, true, false)).start();
    }

    public void openSearchAdapter(View view) {
        Intent intent = new Intent(this, SearchParksActivity.class);
        startActivity(intent);
    }

    public void openDashboard(View view) {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }

    public void populateFavsVisit(View view) {
        new Thread(new APITester(this, false, true)).start();
    }

}