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
 * Main app activity which allows a user to do
 * a search query on the park name or state.
 */
public class DashboardActivity extends AppCompatActivity {
    RecyclerView favoritesRv;
    RecyclerView visitedRv;
    Activity activity = this;
    // Designates what to send as the intent name
    public static final String EXTRA_QUERY = "team2.cs246.nationalparkapp.QUERY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        favoritesRv = findViewById(R.id.RV_FavoriteParks);
        visitedRv = findViewById(R.id.RV_VisitedParks);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        favoritesRv.addItemDecoration(dividerItemDecoration);
        visitedRv.addItemDecoration(dividerItemDecoration);

        new Thread(new DashboardPresenter(this)).start();
    }

    /*@Override
    protected void onResume() {
        super.onResume();
        new Thread(new DashboardPresenter(this)).start();
    }*/



    /**
     * Allows a call to SearchParksActivity to filter
     * by full park name or State.
     * @param menu The menu on Dashboard that allows for search
     * @return boolean of status of creating options menu and calls
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // We want to only query when a user submits instead of letter by letter
            // This was done for performance reasons and to not call the API too heavily
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(activity, SearchParksActivity.class);
                intent.putExtra(EXTRA_QUERY, query);
                startActivity(intent);
                //new Thread(new SearchParksPresenter(activity, query)).start();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}