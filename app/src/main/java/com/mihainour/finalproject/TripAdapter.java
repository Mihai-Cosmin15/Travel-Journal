package com.mihainour.finalproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Movie;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mihainour.finalproject.ui.contact.ContactFragment;
import com.mihainour.finalproject.ui.home.HomeFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripViewHolder> {

    private static final String TAG_ACTIVITY = "TripAdapter";


    Context context;
    private List<Trip> tripList;
    private SelectTrip selectTrip;
    private boolean isFavorite;

    public TripAdapter(Context context, List<Trip> trips, SelectTrip selectTrip) {
        this.context = context;
        tripList = trips;
        this.selectTrip = selectTrip;
    }

    // create the items and add them to the parent (RecyclerView)
    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_item, parent, false);
        return new TripViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
        Trip currentTrip = tripList.get(position);
        if(currentTrip.getImageUrl().length() > 0) {
            Picasso.get().load(currentTrip.getImageUrl())
                    .placeholder(R.drawable.trip)
                    .error(R.drawable.error)
                    .into(holder.getImageViewPhoto());
        }
        holder.getTextViewName().setText(currentTrip.getName());
        holder.getTextViewDestination().setText(context.getString(R.string.destination_text, currentTrip.getDestination()));
        holder.getTextViewPrice().setText(context.getString(R.string.price_text, String.valueOf(currentTrip.getPrice())));

        SharedPreferences prefs = context.getSharedPreferences(HomeFragment.APP_KEY, Context.MODE_PRIVATE);

        isFavorite = prefs.getBoolean(""+currentTrip.getId(), false);
        holder.getFavoriteButton().setChecked(isFavorite);
        holder.getFavoriteButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isFavorite = prefs.getBoolean(""+currentTrip.getId(), false);
                isFavorite = !isFavorite;
                holder.getFavoriteButton().setChecked(isFavorite);
                selectTrip.onFavoriteButtonClicked(tripList.get(holder.getAdapterPosition()), isFavorite);
            }
        });

        holder.getCardView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                selectTrip.onItemLongClicked(tripList.get(holder.getAdapterPosition()));
                return true;
            }
        });

        holder.getCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectTrip.onItemClicked(tripList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return tripList == null ? 0 : tripList.size();
    }

}
