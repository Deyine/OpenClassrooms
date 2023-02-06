package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {
    private Context myContext;
    public ListNeighbourPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        myContext = context;
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        System.out.println("fragement Position: " + position);
        return NeighbourFragment.newInstance(position);
    }


    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return 2;
    }
}