package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    @Override
    public void deleteNeighbour(Neighbour neighbour) {

    }
}
