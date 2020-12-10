package team2.cs246.nationalparkapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import team2.cs246.nationalparkapp.model.Park;
import team2.cs246.nationalparkapp.model.StateHelper;

public class SearchParksActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    //RecyclerAdapter recyclerAdapter;
    //List<Park> parksList;
    Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_parks);

        new Thread(new SearchParksPresenter(this, "")).start();
        recyclerView = findViewById(R.id.parkRecyclerView);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                new Thread(new SearchParksPresenter(activity, query)).start();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //recyclerAdapter.getFilter().filter(newText);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}