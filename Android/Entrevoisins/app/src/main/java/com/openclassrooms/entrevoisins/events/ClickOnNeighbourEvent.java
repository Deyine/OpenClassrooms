package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public interface ClickOnNeighbourEvent {
    void clickOnNeighbour(Neighbour neighbourClicked);
}