package com.openclassrooms.entrevoisins.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;
import android.support.test.uiautomator.By;
import android.support.v4.app.NotificationManagerCompat;


import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.InfoNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private static int ITEMS_EMPTY = 0;
    private Neighbour fakeInfoNeighbour;
    private String facebookNeighbourg;
    private int NotificationId = 123;
    private UiDevice uiDevice;
    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private Context mContext;


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
        mApiService = DI.getNeighbourApiService();
        mNeighbours = mApiService.getNeighbours();
        fakeInfoNeighbour = new Neighbour(1, "Caroline", "https://i.pravatar.cc/150?u=a042581f4e29026704d", "Saint-Pierre-du-Mont ; 5km",
                "+33 6 86 57 90 14",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..",false);
        facebookNeighbourg = "www.Facebook.com/" + fakeInfoNeighbour.getName();
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

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

        // Given : We sitch to favorites List
        onView(ViewMatchers.withText("FAVORIES")).perform(ViewActions.click());

        // Then : the number of element is 0
        onView(ViewMatchers.withId(R.id.list_fav_neighbours)).check(withItemCount(ITEMS_EMPTY));;
    }

    @Test
    public void myNeighbourDetail_TestFabSnackBar() {
        // Given : We launch InfoNeighbourActivity with fake neighbour.
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

    @Test
    public void myNeighbourDetail_CheckInfo(){
        // Given : We Click on Caroline to open Detail of neighbour
        onView(withId(R.id.list_neighbours))
        .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        // Then : We Check if data is correct
        onView(withId(R.id.textViewNameNeighbourg)).check(matches(withText(fakeInfoNeighbour.getName())));
        onView(withId(R.id.txtAdresseNeighbour)).check(matches(withText(fakeInfoNeighbour.getAddress())));
        onView(withId(R.id.txtPhoneNeighbour)).check(matches(withText(fakeInfoNeighbour.getPhoneNumber())));
        onView(withId(R.id.txtFaceBook)).check(matches(withText(facebookNeighbourg)));
        onView(withId(R.id.txtAboutMe)).check(matches(withText(fakeInfoNeighbour.getAboutMe())));
    }

    @Test
    public void addNeighbourFavories(){
        // Given : We click on Caroline to open detail
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        // When perform click on fab to add favorite neighbour
        onView(withId(R.id.floatingButtonFavorie)).perform(ViewActions.click());
        // When Return to list
        uiDevice.pressBack();

        // When click to tab view favories
        onView(ViewMatchers.withText("FAVORIES")).perform(ViewActions.click());

        // Then : the number of element is 1
        onView(ViewMatchers.withId(R.id.list_fav_neighbours)).check(withItemCount(ITEMS_EMPTY+1));
        List<Neighbour> lstFav = mApiService.getFavoriteNeighbours();
        Neighbour fav = lstFav.get(0);
        Neighbour name = mNeighbours.get(2);

        // Check if the neighbourg is correct and if another neighbourg is not in list
        assertNotEquals(fav.getName(),name.getName());
        assertEquals(fav.getName(),fakeInfoNeighbour.getName());

        onView(ViewMatchers.withId(R.id.list_fav_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));


        // When perform click on fab to remove favorite neighbour for another test
        onView(withId(R.id.floatingButtonFavorie)).perform(ViewActions.click());

    }

    @Test
    public void myNeighbourDetail_TestFabAddNotification(){
        String expectedAppName = "Entrevoisins";
        String expectedTitle = "Ajout Favorie";
        String expectedText = "Caroline fait maintenant partie de vos favoris";


        // Given : We launch InfoNeighbourActivity with fake neighbour.
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

        // We open notification
        uiDevice.openNotification();
        uiDevice.wait(Until.hasObject(By.textStartsWith(expectedAppName)), 5);
        UiObject2 title = uiDevice.findObject(By.text(expectedTitle));
        UiObject2 texte = uiDevice.findObject(By.text(expectedText));

        // Then : We check texte in notification
        assertEquals(expectedTitle,title.getText());
        assertEquals(expectedText,texte.getText());

        // We remove neighbour for other test after
        onView(withId(R.id.floatingButtonFavorie)).perform(ViewActions.click());
    }

    @Test
    public void myNeighbourDetail_TestFabSuppNotification(){
        String expectedAppName = "Entrevoisins";
        UiDevice uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        String expectedTitle = "Suppression Favorie";
        String expectedText = "Caroline ne fait plus partie de vos favoris";

        // Given : We launch InfoNeighbourActivity with fake neighbour.
        Intent intent = new Intent();
        intent.putExtra("neighbour_Id",fakeInfoNeighbour.getId());
        intent.putExtra("neighbour_Name",fakeInfoNeighbour.getName());
        intent.putExtra("neighbour_AboutMe",fakeInfoNeighbour.getAboutMe());
        intent.putExtra("neighbour_AvatarUrl",fakeInfoNeighbour.getAvatarUrl());
        intent.putExtra("neighbour_PhoneNumber",fakeInfoNeighbour.getPhoneNumber());
        intent.putExtra("neighbour_Adresse",fakeInfoNeighbour.getAddress());
        intent.putExtra("neighbour_isFavorite",true);
        InfoNeighbourActivityTestRule.launchActivity(intent);

        // When perform click on fab to add favorite neighbour
        onView(withId(R.id.floatingButtonFavorie)).perform(ViewActions.click());

        // We open notification
        uiDevice.openNotification();
        uiDevice.wait(Until.hasObject(By.textStartsWith(expectedAppName)), 5);
        UiObject2 title = uiDevice.findObject(By.text(expectedTitle));
        UiObject2 texte = uiDevice.findObject(By.text(expectedText));

        // Then : We check texte in notification
        assertEquals(expectedTitle,title.getText());
        assertEquals(expectedText,texte.getText());

    }
}