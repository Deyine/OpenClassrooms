package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class AddNeighbourgFavorisEvent {
    /**
     * Neighbour to add in favoris list
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */
    public AddNeighbourgFavorisEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
