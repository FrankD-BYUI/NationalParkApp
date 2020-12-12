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


        String latText = latLongText.substring(latLongText.indexOf(":")+1,latLongText.indexOf(","));
        String longText = latLongText.substring(latLongText.lastIndexOf(":")+1);

        double latDouble = Double.parseDouble(latText);
        double longDouble = Double.parseDouble(longText);

        parkTitle = findViewById(R.id.parkTitle);
        parkName = findViewById(R.id.parkName);
        description = findViewById(R.id.description);
        mapLookup = findViewById(R.id.mapLookup);

        parkTitle.setText(parkTitleText);
        parkName.setText(parkNameText);
        description.setText(descriptionText);

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