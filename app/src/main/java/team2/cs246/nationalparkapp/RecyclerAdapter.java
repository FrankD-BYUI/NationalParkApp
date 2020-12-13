package team2.cs246.nationalparkapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import team2.cs246.nationalparkapp.model.Park;

public class RecyclerAdapter extends  RecyclerView.Adapter<RecyclerAdapter.ParkViewHolder> /*implements Filterable*/ {

    private static final String TAG = "RecyclerAdapter";

    List<Park> parksList;
    List<Park> fullParksList;
    private final Context mContext;
    private final LayoutInflater mLayoutInflator;

    public RecyclerAdapter(List<Park> parksList, Context mContext) {
        this.parksList = parksList;
        this.fullParksList = new ArrayList<>(parksList);
        this.mContext = mContext;
        mLayoutInflator = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ParkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Log.i(TAG, "onCreateViewHolder: " + count++);
        View view = mLayoutInflator.inflate(R.layout.row_item, parent, false);
        ParkViewHolder parkViewHolder = new ParkViewHolder(view);

        return parkViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ParkViewHolder holder, int position) {
        holder.parkLocationTextView.setText(parksList.get(position).getCityState());
        holder.parkNameTextView.setText(parksList.get(position).getName());
        holder.mCurrentPosition = position;
    }

    @Override
    public int getItemCount() {
        return parksList.size();
    }

    class ParkViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView parkNameTextView, parkLocationTextView;
        public int mCurrentPosition;

        public ParkViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            parkNameTextView = itemView.findViewById(R.id.parkNameTextView);
            parkLocationTextView = itemView.findViewById(R.id.parkLocationTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(mContext,ParkDetail.class);
                    Park selectedPark = parksList.get(mCurrentPosition);
                    intent.putExtra("FNAME", selectedPark.getFullName());
                    intent.putExtra("NAME", selectedPark.getName());
                    intent.putExtra("DESC", selectedPark.getDescription());
                    intent.putExtra("LATLONG", selectedPark.getLatLong());
                    intent.putExtra("PARKDESIGNATION", selectedPark.getDesignation());
                    intent.putExtra("PARKCODE", selectedPark.getParkCode());
                    //intent.putExtra("HEADER", selectedPark.getImages());
                    mContext.startActivity(intent);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    parksList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}