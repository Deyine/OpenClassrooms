
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.core.IsNull.notNullValue;

import java.util.List;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;
    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        mApiService = DI.getNeighbourApiService();
        mNeighbours = mApiService.getNeighbours();
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        //onView(withId(R.id.list_neighbours))
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT-1));
    }
    @Test
    public void neighbourIsClicked_shouldOpenDetailPage_withCorrectInfo() {
        //launches second page
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        Neighbour neighbour = mNeighbours.get(0);
        onView(withId(R.id.constraintLayout_UserInfo)).check(matches(isDisplayed()));

        onView(withId(R.id.detail_avatar_lable_name)).check(matches(withText(neighbour.getName())));
        onView(withId(R.id.detail_info_text_name)).check(matches(withText(neighbour.getName())));
    }
    @Test
    public void testFavoriteList_OnlyDisplaysFavoriteNeighbours() throws InterruptedException {
        //checks that the favorite list is empty at first
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).perform(swipeLeft());
        Thread.sleep(500);
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(0));
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).perform(swipeRight());
        Thread.sleep(500);
        //adds a favorite neighbour by clicking on the fab button
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        Neighbour neighbour = mNeighbours.get(0);
        onView(withId(R.id.detail_button_favorite)).perform(click());
        onView(withId(R.id.detail_back_arrow)).perform(click());//go back home page
        //Checks the the favorite list has one member and that it is the correct neighbour
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).perform(swipeLeft());
        Thread.sleep(500);
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(1));
        onView(allOf(withText(neighbour.getName()),isDisplayed())).check(matches(isDisplayed()));
    }
}