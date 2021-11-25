package com.oc.mareu.ui.mareu;

import com.oc.mareu.model.Meeting;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListMeetingPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Meeting> mMeetings;

    public ListMeetingPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return 1;
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return MeetingFragment.newInstance();
    }

}
