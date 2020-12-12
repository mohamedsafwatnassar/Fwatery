package com.example.fwatery;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.fwatery.Base.BaseFragment;
import com.example.fwatery.Models.A3tal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import static com.example.fwatery.MainActivity.bottomNavigation;

public class a3taal extends BaseFragment {

    TabLayout tabLayout;

    A3tal_VM vm;

    ViewPager viewPager;
    AdapterViewPager adapterViewPager;

    View view;
    TabItem repairDoneTab;
    TabItem repairNotDoneTab;

    public a3taal() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bottomNavigation.show(3,false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vm = new ViewModelProvider(getActivity()).get(A3tal_VM.class);
        view = inflater.inflate(R.layout.fragment_a3taal, container, false);
        getActivity().setTitle("أعطال                                  ");

        initView();

        return view;
    }


    private void initView() {
        tabLayout = view.findViewById(R.id.tabLayout);
        repairDoneTab = view.findViewById(R.id.repairDoneTab);
        repairNotDoneTab = view.findViewById(R.id.repairNotDoneTab);
        viewPager = view.findViewById(R.id.viewPager);

        adapterViewPager = new AdapterViewPager(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapterViewPager);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0 ){
                    adapterViewPager.notifyDataSetChanged();
                }else if (tab.getPosition() == 1){
                    adapterViewPager.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }

}