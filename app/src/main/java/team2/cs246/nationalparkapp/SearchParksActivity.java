package team2.cs246.nationalparkapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.List;

import team2.cs246.nationalparkapp.model.Park;
import team2.cs246.nationalparkapp.model.ParkRepository;
import team2.cs246.nationalparkapp.model.StateHelper;

public class SearchParksActivity extends AppCompatActivity implements RecyclerAdapter.ItemClickListener {
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    List<Park> parksListOG;
    List<Park> parksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_parks);

        WeakReference<Activity> weak = new WeakReference<>(this);

        parksListOG = ParkRepository.searchParksByName(weak, "");
        parksList = ParkRepository.searchParksByName(weak, "");

        recyclerView = findViewById(R.id.parkRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter(parksList);
        recyclerAdapter.setListener(this);
        recyclerView.setAdapter(recyclerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                search(newText);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    public void search(String text){
        parksList.clear();
        text = text.toLowerCase();
        for(Park park : parksListOG){
            if(park.getFullName().toLowerCase().contains(text) || park.getCityState().toLowerCase().contains(text))
                parksList.add(park);
        }
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {
        Park selectedPark = parksList.get(position);

        Intent intent = new Intent(this, ParkDetail.class);
        intent.putExtra("FNAME", selectedPark.getFullName());
        intent.putExtra("NAME", selectedPark.getName());
        intent.putExtra("DESC", selectedPark.getDescription());
        intent.putExtra("LATLONG", selectedPark.getLatLong());

        startActivity(intent);
    }
}