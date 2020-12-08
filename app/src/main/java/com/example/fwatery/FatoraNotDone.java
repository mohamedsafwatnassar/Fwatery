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

import com.example.fwatery.Models.Fatora;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FatoraNotDone extends Fragment {

    RecyclerView recyclerViewFatoraNotDone;
    Dialog dialog;
    View view ;
    FloatingActionButton fab;

    FatoraAdapter fatoraAdapter;
    RecyclerView.LayoutManager layoutManager;

    public FatoraNotDone() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fatora_not_done, container, false);

        initView();

        initRecyclerView();
        return view;
    }

    List<Fatora> fatoraList;
    private void initRecyclerView() {
        fatoraList = new ArrayList<>();
        fatoraList.add(new Fatora("mohamed safwat","01150500021",55));
        fatoraList.add(new Fatora("Ali","01150500021",100));
        fatoraList.add(new Fatora("Seif","01150500021",400));
        fatoraList.add(new Fatora("Nassar","01150500021",999));
        fatoraList.add(new Fatora("Nassar","01150500021",999));
        fatoraList.add(new Fatora("Nassar","01150500021",999));
        fatoraList.add(new Fatora("Nassar","01150500021",999));
        fatoraList.add(new Fatora("Nassar","01150500021",999));
        fatoraAdapter = new FatoraAdapter(getActivity(),fatoraList);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewFatoraNotDone.setHasFixedSize(true);
        recyclerViewFatoraNotDone.setAdapter(fatoraAdapter);
        recyclerViewFatoraNotDone.setLayoutManager(layoutManager);

    }

    private void initView() {
        recyclerViewFatoraNotDone = view.findViewById(R.id.recyclerView_fatora_NotDone);
        fab = view.findViewById(R.id.add_fat);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.add_fatora);

                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                //dialog.getWindow().setGravity(Gravity.CENTER);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                dialog.setCancelable(false);
                dialog.show();
                Button addfatora = dialog.findViewById(R.id.Add_fatora);
                Button cancel = dialog.findViewById(R.id.cancel);

                addfatora.setOnClickListener(new View.OnClickListener() {
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
        });


    }
}