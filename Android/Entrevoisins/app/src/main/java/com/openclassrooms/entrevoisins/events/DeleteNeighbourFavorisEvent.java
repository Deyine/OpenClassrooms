package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class DeleteNeighbourFavorisEvent {

    /**
     * Neighbour to delete
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */
    public DeleteNeighbourFavorisEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
