package com.example.i20088.iconsultapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionsStatePagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentlist=new ArrayList<>();
    private final List<String> mFragmentTitleList=new ArrayList<>();
    public SectionsStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public void addFragment(Fragment fragment, String title)

    {
        mFragmentlist.add(fragment);
        mFragmentTitleList.add(title);
    }
    @Override
    public Fragment getItem(int position) {
        return mFragmentlist.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentlist.size();
    }
}
