package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyNeighbourGenerator {

    public static List<Neighbour> DUMMY_NEIGHBOURS = Arrays.asList(
            new Neighbour(1, "Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d"),
            new Neighbour(2, "Jack", "http://i.pravatar.cc/150?u=a042581f4e29026704e"),
            new Neighbour(3, "Chloé", "http://i.pravatar.cc/150?u=a042581f4e29026704f"),
            new Neighbour(1, "Vincent", "http://i.pravatar.cc/150?u=a042581f4e29026704a"),
            new Neighbour(1, "Elodie", "http://i.pravatar.cc/150?u=a042581f4e29026704b"),
            new Neighbour(1, "Sylvain", "http://i.pravatar.cc/150?u=a042581f4e29026704c"),
            new Neighbour(1, "Laetitia", "http://i.pravatar.cc/150?u=a042581f4e29026703d"),
            new Neighbour(1, "Dan", "http://i.pravatar.cc/150?u=a042581f4e29026703b"),
            new Neighbour(1, "Joseph", "http://i.pravatar.cc/150?u=a042581f4e29026704d"),
            new Neighbour(1, "Emma", "http://i.pravatar.cc/150?u=a042581f4e29026706d"),
            new Neighbour(1, "Patrick", "http://i.pravatar.cc/150?u=a042581f4e29026702d"),
            new Neighbour(1, "Ludovic", "http://i.pravatar.cc/150?u=a042581f3e39026702d")
    );

    static List<Neighbour> generateNeighbours() {
        return new ArrayList<>(DUMMY_NEIGHBOURS);
    }
}
