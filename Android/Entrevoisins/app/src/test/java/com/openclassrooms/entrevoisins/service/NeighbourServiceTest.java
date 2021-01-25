package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;
    private List<Neighbour> favoriteNeighbours;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void addFavoriteNeighbourWithSuccess() {
        service.getFavoriteNeighbours().clear();
        favoriteNeighbours = service.getFavoriteNeighbours();
        Neighbour favoriteNeighbourToAdd = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(0);
        service.addFavoriteNeighbour(favoriteNeighbourToAdd);
        assertTrue(service.getFavoriteNeighbours().contains(favoriteNeighbourToAdd));
    }

    @Test
    public void isFavoriteNeighboursWithSuccess() {
        service.getFavoriteNeighbours().clear();
        List<Neighbour> favoriteNeighbours = service.getFavoriteNeighbours();
        Neighbour neighbourToCompare = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(0);
        neighbourToCompare.setFavorite(true);
        favoriteNeighbours.add(neighbourToCompare);
        assertTrue(service.getFavoriteNeighbours().get(0).isFavorite());
    }

    @Test
    public void deleteFavoriteNeighboursWithSuccess() {
        Neighbour neighbourToDelete = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(0);
        neighbourToDelete.setFavorite(true);
        service.getFavoriteNeighbours().clear();
        favoriteNeighbours = service.getFavoriteNeighbours();
        favoriteNeighbours.add(neighbourToDelete);
        service.deleteFavoriteNeighbour(neighbourToDelete);
        assertFalse(service.getFavoriteNeighbours().contains(neighbourToDelete));
    }
}
