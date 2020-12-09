package team2.cs246.nationalparkapp;

import android.app.Activity;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.List;

import team2.cs246.nationalparkapp.model.Park;
import team2.cs246.nationalparkapp.model.ParkRepository;

public class ParksSearcher implements Runnable{
    private static final String TAG = "PullTemp";
    private WeakReference<Activity> activityRef;
    private String parkName;

    public ParksSearcher(Activity activity, String parkName) {
        this.activityRef = new WeakReference<Activity>(activity);
        this.parkName = parkName;
    }

    @Override
    public void run() {
        List<Park> parksList;

        parksList = ParkRepository.searchParksByName(this.activityRef, parkName);

        if (parksList != null) {
            final Activity activity = activityRef.get();
            if (activity != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        RecyclerView recyclerView;
                        RecyclerAdapter recyclerAdapter;

                        recyclerView = activity.findViewById(R.id.parkRecyclerView);
                        recyclerAdapter = new RecyclerAdapter(parksList);

                        recyclerView.setAdapter(recyclerAdapter);

                        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL);
                        recyclerView.addItemDecoration(dividerItemDecoration);
                    }
                });
            }
        }
    }
}
