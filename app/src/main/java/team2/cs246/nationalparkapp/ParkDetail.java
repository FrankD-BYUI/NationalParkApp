package team2.cs246.nationalparkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ParkDetail extends AppCompatActivity {

    TextView parkTitle;
    TextView parkName;
    TextView description;
    TextView parkDesignation;
    Button mapLookup;
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


        String latText = latLongText.length() > 0 ? latLongText.substring(latLongText.indexOf(":")+1,latLongText.indexOf(",")) : "0.0";
        String longText = latLongText.length() > 0 ? latLongText.substring(latLongText.lastIndexOf(":")+1) : "0.0";

        double latDouble = Double.parseDouble(latText);
        double longDouble = Double.parseDouble(longText);

        parkTitle = findViewById(R.id.parkTitle);
        parkName = findViewById(R.id.parkName);
        description = findViewById(R.id.description);
        parkDesignation = findViewById(R.id.parkDesignation);
        mapLookup = findViewById(R.id.mapLookup);

        parkTitle.setText(parkTitleText);
        parkName.setText(parkNameText);
        description.setText(descriptionText);
        parkDesignation.setText(parkDesignationText);

        mapLookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMap(latDouble, longDouble);
            }
        });
    }

    public void goToMap(double latDouble, double longDouble){
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("LAT", latDouble);
        intent.putExtra("LONG", longDouble);
        startActivity(intent);
    }
}