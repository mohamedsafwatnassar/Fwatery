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

    //Context context;
    //List<Fragment> fragmentList = new ArrayList<>();
    //List<String> stringList = new ArrayList<>();

    int numOftabs;

    /*public void addFragment(Fragment fragment, String title){
        fragmentList.add(fragment);
        stringList.add(title);
    }*/

    int numOfTabs;
    public AdapterViewPager(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOftabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        //return fragmentList.get(position);
        switch (position){
            case 0:
                return new RepairDone();
            case 1:
                return new RepairNotDone();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        //return fragmentList.size();
        return numOftabs;
    }

    /*@Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        //return stringList.get(position);
    }*/

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
