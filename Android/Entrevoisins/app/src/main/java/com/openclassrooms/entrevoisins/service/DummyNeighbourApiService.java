package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    // Create List of favorie
    private List<Neighbour> neighboursFavorites = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }


    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        return neighboursFavorites;
    }

    /**
     * Add a favorite neighbour
     * {@param neighbour}
     */

    @Override
    public void addFavoriteNeighbour(Neighbour neighbour) {
        neighbour.setFavorite(true);
        neighboursFavorites.add(neighbour);

    }

    /**
     * Delete a favorite neighbour
     * {@param neighbour}
     */

    @Override
    public void deleteFavoriteNeighbour(Neighbour neighbour) {
        neighbour.setFavorite(false);
        neighboursFavorites.remove(neighbour);
    }

}
