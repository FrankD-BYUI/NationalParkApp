package team2.cs246.nationalparkapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Allows a call to SearchParksActivity to filter
 * by full park name or State.
 * Uses a recycler adapter to update with latest results from API searches
 */
public class SearchParksActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Activity activity = this;
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_parks);

        Intent intent = getIntent();
        this.query = intent.getStringExtra(DashboardActivity.EXTRA_QUERY);

        if (this.query != null){
        new Thread(new SearchParksPresenter(this, this.query)).start();
        }
        else
        {
            new Thread(new SearchParksPresenter(this, "")).start();
        }
        recyclerView = findViewById(R.id.parkRecyclerView);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // Only do search after a submit so we don't have to make extra
            // API calls and get interim results
            @Override
            public boolean onQueryTextSubmit(String query) {
                new Thread(new SearchParksPresenter(activity, query)).start();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

}