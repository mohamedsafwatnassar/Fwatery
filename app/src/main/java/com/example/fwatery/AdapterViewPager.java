package com.example.fwatery;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdapterViewPager extends FragmentPagerAdapter {

    /*Context context;
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> stringList = new ArrayList<>();

    public void addFragment(Fragment fragment, String title){
        fragmentList.add(fragment);
        stringList.add(title);
    }*/

    int numOfTabs;
    public AdapterViewPager(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new a3taal();
            case 1:
                return new a3taal();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    /*@Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return stringList.get(position);
    }*/
}
