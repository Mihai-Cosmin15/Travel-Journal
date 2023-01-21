package com.mihainour.finalproject;

public interface SelectTrip {
    void onItemClicked(Trip trip);
    void onItemLongClicked(Trip trip);
    void onFavoriteButtonClicked(Trip trip, boolean isFavorite);
}
