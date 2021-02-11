package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user deletes a Neighbour favoris list
 */

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
