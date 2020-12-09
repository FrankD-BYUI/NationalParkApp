package team2.cs246.nationalparkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import team2.cs246.nationalparkapp.model.APITester;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testAPI(View view) {
        new Thread(new APITester(this, false)).start();
    }

    public void clearStoredData(View view) {
        Log.d("MainActivity", "Clearing stored data");
        new Thread(new APITester(this, true)).start();
    }

    public void openSearchAdapter(View view) {
        Intent intent = new Intent(this, SearchParksActivity.class);
        startActivity(intent);
    }

}