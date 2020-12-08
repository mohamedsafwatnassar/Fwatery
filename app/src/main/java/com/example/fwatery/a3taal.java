package com.example.fwatery;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.fwatery.Models.A3tal;
import com.example.fwatery.Models.Fatora;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class a3taal extends Fragment implements View.OnClickListener {

    FloatingActionButton fab;
    RecyclerView recyclerViewA3tal;
    ViewPager viewPager;

    A3taalAdapter a3taalAdapter;
    RecyclerView.LayoutManager layoutManager;

    public a3taal() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_a3taal, container, false);

        initView();
        initRecyclerView();
        return view;
    }

    private void initView() {
        recyclerViewA3tal = view.findViewById(R.id.recyclerView_a3tal);
        viewPager = view.findViewById(R.id.viewPager);
        fab = view.findViewById(R.id.add_3otl);
        fab.setOnClickListener(this);
    }

    Dialog dialog;
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_3otl){
            dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.add_a3tal);

            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
            dialog.setCancelable(false);
            dialog.show();
            Button add3otl = dialog.findViewById(R.id.Add_3otll);
            Button cancel = dialog.findViewById(R.id.cancel);

            add3otl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }
    }

    List<A3tal> a3talList;
    private void initRecyclerView() {
        a3talList = new ArrayList<>();
        a3talList.add(new A3tal("mohamed safwat","01117796570","55"));
        a3talList.add(new A3tal("Ali","01150500021","street-5"));
        a3talList.add(new A3tal("Seif","01117796570","street-44"));
        a3talList.add(new A3tal("Omar","01117796570","street-33"));
        a3talList.add(new A3tal("Nassar","01117796570","street-543"));
        a3talList.add(new A3tal("Hamada","01117796570","street-18"));
        a3talList.add(new A3tal(" sssss","01117796570","street-1"));

        a3taalAdapter = new A3taalAdapter(getActivity(),a3talList);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewA3tal.setHasFixedSize(true);
        recyclerViewA3tal.setAdapter(a3taalAdapter);
        recyclerViewA3tal.setLayoutManager(layoutManager);

    }
}