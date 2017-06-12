package com.example.jamie.warmindjsonfunctions;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class SlideAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> pages;

    public SlideAdapter(FragmentManager fm) {
        super(fm);
        pages = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return pages.get(position);
    }

    @Override
    public int getCount() {
        return pages.size();
    }
}
