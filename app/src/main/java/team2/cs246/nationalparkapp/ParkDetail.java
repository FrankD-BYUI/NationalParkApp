package team2.cs246.nationalparkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.lang.ref.WeakReference;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import team2.cs246.nationalparkapp.model.Park;
import team2.cs246.nationalparkapp.model.ParkRepository;

public class ParkDetail extends AppCompatActivity {
    private static final String TAG = "team2.cs246.nationalparkapp.ParkDetail";
    Activity activity = this;
    TextView parkTitle;
    TextView parkName;
    TextView description;
    TextView parkDesignation;
    Button mapLookup;
    Button visited;
    Park currentPark;
    WeakReference<Activity> parkDetailActivityRef;

    //ImageView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_detail);

        String parkTitleText = getIntent().getStringExtra("NAME");
        String parkNameText = getIntent().getStringExtra("FNAME");
        String descriptionText = getIntent().getStringExtra("DESC");
        String latLongText = getIntent().getStringExtra("LATLONG");
        String parkDesignationText = getIntent().getStringExtra("PARKDESIGNATION");
        String parkCodeText = getIntent().getStringExtra("PARKCODE");

        // Takes the API lat/long string and separates the coordinates to separate strings
        String latText = latLongText.length() > 0 ? latLongText.substring(latLongText.indexOf(":")+1,latLongText.indexOf(",")) : "0.0";
        String longText = latLongText.length() > 0 ? latLongText.substring(latLongText.lastIndexOf(":")+1) : "0.0";

        // Converts the lat/long strings to double variables
        double latDouble = Double.parseDouble(latText);
        double longDouble = Double.parseDouble(longText);

        parkTitle = findViewById(R.id.parkTitle);
        parkName = findViewById(R.id.parkName);
        description = findViewById(R.id.description);
        parkDesignation = findViewById(R.id.parkDesignation);
        mapLookup = findViewById(R.id.mapLookup);
        visited = findViewById(R.id.visited);

        parkTitle.setText(parkTitleText);
        parkName.setText(parkNameText);
        description.setText(descriptionText);
        parkDesignation.setText(parkDesignationText);

        // Calls the goToMap class and passes the variables
        mapLookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMap(latDouble, longDouble, parkTitleText);
            }
        });

        visited.setOnClickListener(new View.OnClickListener()   {
            @Override
            public void onClick(View view) {
                if (!parkCodeText.isEmpty())
                visitedPark(parkCodeText, view);
            }
        });
    }

    // Adds intents and intent extras to pass to the maps activity for displaying the maps correctly
    public void goToMap(double latDouble, double longDouble, String parkTitle){
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("LAT", latDouble);
        intent.putExtra("LONG", longDouble);
        intent.putExtra("NAME", parkTitle);
        startActivity(intent);
    }

    public void visitedPark(String parkCode, View view){
        parkDetailActivityRef = new WeakReference<Activity>(activity);

        new Thread(new Runnable() {
            @Override
            public void run() {
                currentPark = ParkRepository.loadParkByParkCode(parkDetailActivityRef, parkCode);
                List<Park> visited = ParkRepository.loadVisited(parkDetailActivityRef);
                visited.add(currentPark);
                ParkRepository.saveVisited(parkDetailActivityRef, visited);
            }
        }).start();
    }


}