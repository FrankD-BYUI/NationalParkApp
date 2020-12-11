package team2.cs246.nationalparkapp;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import team2.cs246.nationalparkapp.model.Park;
import team2.cs246.nationalparkapp.model.ParkRepository;
import team2.cs246.nationalparkapp.model.StateHelper;

public class SearchParksPresenter implements Runnable, RecyclerAdapter.ItemClickListener{
    private static final String TAG = "PullTemp";
    private WeakReference<Activity> activityRef;
    private String parkName;
    List<Park> parksList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    public SearchParksPresenter(Activity activity, String parkName) {
        this.activityRef = new WeakReference<Activity>(activity);
        this.parkName = parkName;
        recyclerAdapter = new RecyclerAdapter(parksList);
        recyclerAdapter.setListener(this);
    }

    @Override
    public void run() {

        if (StateHelper.isState(parkName)) {
            parksList = ParkRepository.searchParksByState(this.activityRef, parkName);
        } else {
            parksList = ParkRepository.searchParksByName(this.activityRef, parkName);
        }

        if (parksList != null) {
            final Activity activity = activityRef.get();

            if (activity != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView = activity.findViewById(R.id.parkRecyclerView);


                        recyclerView.setAdapter(recyclerAdapter);
                        recyclerAdapter.notifyDataSetChanged();
                    }
                });
            }
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(activityRef.get(), ParkDetail.class);
        activityRef.get().startActivity(intent);
    }
}