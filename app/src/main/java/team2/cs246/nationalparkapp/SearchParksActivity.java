package team2.cs246.nationalparkapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import team2.cs246.nationalparkapp.model.Park;
import team2.cs246.nationalparkapp.model.ParkRepository;

public class SearchParksActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    List<Park> parksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_parks);

        new Thread(new ParksSearcher(this, "")).start();
/*        parksList = new ArrayList<>();


        parksList.add("Yosemite National Park");
        parksList.add("Grand Canyon National Park");
        parksList.add("Yellowstone National Park");
        parksList.add("Devil's Rock National Park");
        parksList.add("Mount Rainier National Park");
        parksList.add("Mt. Rushmore National Park");
        parksList.add("Arches National Park");
        parksList.add("Zions National Park");
        parksList.add("Bryce Canyon National Park");
        parksList.add("Manhattan Project National Historical Park");
        parksList.add("Canyonlands National Park");
        parksList.add("Sequoia National Park");
        parksList.add("Joshua Tree National Park");
        parksList.add("Kings Canyon National Park");
        parksList.add("Death Valley National Park");
        parksList.add("Channel Island National Park");
        parksList.add("Pinnacles National Park");
        parksList.add("Petrified Forest National Park");
        parksList.add("Saguaro National Park");
        parksList.add("Monument Valley National Park");*/


        /*recyclerView = findViewById(R.id.parkRecyclerView);
        recyclerAdapter = new RecyclerAdapter(parksList);

        recyclerView.setAdapter(recyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerAdapter.getFilter().filter(newText);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}