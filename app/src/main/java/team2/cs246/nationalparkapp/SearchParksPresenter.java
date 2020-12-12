package team2.cs246.nationalparkapp;

import android.app.Activity;

import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.List;

import team2.cs246.nationalparkapp.model.Park;
import team2.cs246.nationalparkapp.model.ParkRepository;
import team2.cs246.nationalparkapp.model.StateHelper;

public class SearchParksPresenter implements Runnable {
    private static final String TAG = "SearchParksPresenter";
    private WeakReference<Activity> activityRef;
    private String parkName;
    RecyclerAdapter recyclerAdapter;

    public SearchParksPresenter(Activity activity, String parkName) {
        this.activityRef = new WeakReference<Activity>(activity);
        this.parkName = parkName;
    }

    @Override
    public void run() {
        List<Park> parksList;

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
                        RecyclerView recyclerView = activity.findViewById(R.id.parkRecyclerView);
                        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(parksList, activity);

                        recyclerView.setAdapter(recyclerAdapter);
                        recyclerAdapter.notifyDataSetChanged();
                    }
                });
            }
        }
    }

}