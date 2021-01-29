package com.openclassrooms.entrevoisins.neighbour_list;

import android.content.Intent;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.InfoNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private Neighbour fakeInfoNeighbour;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);
    public ActivityTestRule<InfoNeighbourActivity> InfoNeighbourActivityTestRule =
            new ActivityTestRule(InfoNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        fakeInfoNeighbour = new Neighbour(1,
                "Caroline",
                "https://i.pravatar.cc/150?u=a042581f4e29026704d",
                "Saint-Pierre-du-Mont ; 5km",
                "+33 6 86 57 90 14",
                "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..",
                false);
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT-1));
    }

    @Test
    public void myNeighboursFavorieListIsEmpty() {
        onView(ViewMatchers.withId(R.id.tabItem2)).check(matches(ViewMatchers.withText("FAVORIES")));

    }

    @Test
    public void myNeighbourDetail_TestFabSnack() {
        // Given : We launch DetailNeighbourActivity with fake neighbour.
        Intent intent = new Intent();
        intent.putExtra("neighbour_Id",fakeInfoNeighbour.getId());
        intent.putExtra("neighbour_Name",fakeInfoNeighbour.getName());
        intent.putExtra("neighbour_AboutMe",fakeInfoNeighbour.getAboutMe());
        intent.putExtra("neighbour_AvatarUrl",fakeInfoNeighbour.getAvatarUrl());
        intent.putExtra("neighbour_PhoneNumber",fakeInfoNeighbour.getPhoneNumber());
        intent.putExtra("neighbour_Adresse",fakeInfoNeighbour.getAddress());
        intent.putExtra("neighbour_isFavorite",fakeInfoNeighbour.isFavorite());
        InfoNeighbourActivityTestRule.launchActivity(intent);
        // When perform click on fab to add favorite neighbour
        onView(withId(R.id.floatingButtonFavorie)).perform(ViewActions.click());
        // Then : We should have confirmation message on the snack
        onView(withId(android.support.design.R.id.snackbar_text))
                .check(matches(ViewMatchers.withText("Vous venez d'ajouter Caroline à vos voisins favoris !")));

    }




}