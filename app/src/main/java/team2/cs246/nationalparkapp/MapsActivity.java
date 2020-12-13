package team2.cs246.nationalparkapp;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    // Private variables for maps
    private GoogleMap mMap;
    private double latDouble;
    private double longDouble;
    private String locName;

    // Get the intent passed from ParkDetails. Store them in variables.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        latDouble = getIntent().getDoubleExtra("LAT", 0);
        longDouble = getIntent().getDoubleExtra("LONG", 0);
        locName = getIntent().getStringExtra("NAME");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Pass lac/long location and park name for marker
        LatLng location = new LatLng(latDouble, longDouble);
        mMap.addMarker(new MarkerOptions().position(location).title(locName));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
    }
}