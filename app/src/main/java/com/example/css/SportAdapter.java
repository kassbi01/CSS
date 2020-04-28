package com.example.css;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.SportHolder> {

    class SportHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewTime;
        private TextView textViewLocation;


        private SportHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.name);
            textViewTime = itemView.findViewById(R.id.time);
            textViewLocation = itemView.findViewById(R.id.location);
        }
    }

    private final LayoutInflater mInflater;
    private List<Sport> sports;
    SportAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public SportHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new SportHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SportHolder holder, int position) {
        if (sports != null) {
            Sport currentSport = sports.get(position);
            holder.textViewName.setText(currentSport.getEventName());
            holder.textViewTime.setText(currentSport.getDate());
            holder.textViewLocation.setText(String.valueOf(currentSport.getEventLocation()));
        } else {
            holder.textViewName.setText("No sport");
            holder.textViewTime.setText("No sport");
            holder.textViewLocation.setText("No sport");

        }
    }


    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        if (sports != null)
            return sports.size();
        else
            return 0;
    }

    public void setSport(List<Sport> sports) {
        this.sports = sports;
        notifyDataSetChanged();
    }
}
