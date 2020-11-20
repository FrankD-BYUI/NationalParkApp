package team2.cs246.nationalparkapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecyclerAdapter extends  RecyclerView.Adapter<RecyclerAdapter.ParkViewHolder> implements Filterable {

    private static final String TAG = "RecyclerAdapter";

    List<String> parksList;
    List<String> fullParksList;

    public RecyclerAdapter(List<String> parksList) {
        this.parksList = parksList;
        this.fullParksList = new ArrayList<>(parksList);
    }

    @NonNull
    @Override
    public ParkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Log.i(TAG, "onCreateViewHolder: " + count++);
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        ParkViewHolder parkViewHolder = new ParkViewHolder(view);

        return parkViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ParkViewHolder holder, int position) {
        holder.rowCountTextView.setText(String.valueOf(position));
        holder.parkNameTextView.setText(parksList.get(position));
    }

    @Override
    public int getItemCount() {
        return parksList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {

        // runs on a background thread
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<String> filteredParksList = new ArrayList<>();

            if (constraint.toString().isEmpty())
            {
                filteredParksList.addAll(fullParksList);
            } else {
                for (String park: fullParksList) {
                    if (park.toLowerCase().contains(constraint.toString().toLowerCase())) {
                        filteredParksList.add(park);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredParksList;

            return filterResults;
        }

        // runs on a UI thread
        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            parksList.clear();
            parksList.addAll((Collection<? extends String>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    class ParkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView parkNameTextView, rowCountTextView;

        public ParkViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            parkNameTextView = itemView.findViewById(R.id.parkNameTextView);
            rowCountTextView = itemView.findViewById(R.id.rowCountTextView);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    parksList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                }
            });
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), parksList.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();
        }
    }
}
