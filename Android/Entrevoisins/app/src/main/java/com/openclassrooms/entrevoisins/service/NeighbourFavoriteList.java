package com.openclassrooms.entrevoisins.service;

import android.util.Log;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

public class NeighbourFavoriteList implements NeighbourApiService {

    List<Neighbour> neighboursFavorites;

    public NeighbourFavoriteList() {
        neighboursFavorites = new ArrayList<Neighbour>();
    }

    public List<Neighbour> getNeighbours() {
        return neighboursFavorites;
    }

    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighboursFavorites.add(neighbour);
    }

    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighboursFavorites.remove(neighbour);
    }
}
