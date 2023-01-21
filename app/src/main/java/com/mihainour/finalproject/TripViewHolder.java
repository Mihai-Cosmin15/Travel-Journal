package com.mihainour.finalproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class TripViewHolder extends RecyclerView.ViewHolder {

    private final ImageView imageViewPhoto;
    private final TextView textViewName, textViewDestination, textViewPrice;
    private final CardView cardView;
    private final ToggleButton favoriteButton;

    public TripViewHolder(@NonNull View itemView) {
        super(itemView);
        imageViewPhoto = itemView.findViewById(R.id.imageViewTripPhoto);
        textViewName = itemView.findViewById(R.id.textViewTripName);
        textViewDestination = itemView.findViewById(R.id.textViewTripDestination);
        textViewPrice = itemView.findViewById(R.id.textViewTripPrice);
        favoriteButton = itemView.findViewById(R.id.favoriteButton);
        cardView = itemView.findViewById(R.id.trip_item_cardview);
    }

    public ImageView getImageViewPhoto() {
        return imageViewPhoto;
    }

    public TextView getTextViewName() {
        return textViewName;
    }

    public TextView getTextViewDestination() {
        return textViewDestination;
    }

    public TextView getTextViewPrice() {
        return textViewPrice;
    }

    public ToggleButton getFavoriteButton() {
        return favoriteButton;
    }

    public CardView getCardView() {
        return cardView;
    }
}
