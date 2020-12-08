package com.example.fwatery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fwatery.Models.Fatora;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FatoraNotDone extends Fragment {

    private RecyclerView recyclerViewFatoraNotDone;
    private FloatingActionButton fab;

    FatoraAdapter fatoraAdapter;
    RecyclerView.LayoutManager layoutManager;

    public FatoraNotDone() {
        // Required empty public constructor
    }

    View view;
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
        fatoraList.add(new Fatora("mohamed safwat","01150500021","12/10/2020",55));
        fatoraList.add(new Fatora("Ali","01150500021","12/10/2020",100));
        fatoraList.add(new Fatora("Seif","01150500021","12/10/2020",400));
        fatoraList.add(new Fatora("Nassar","01150500021","12/10/2020",999));
        fatoraList.add(new Fatora("Nassar","01150500021","12/10/2020",999));
        fatoraList.add(new Fatora("Nassar","01150500021","12/10/2020",999));
        fatoraList.add(new Fatora("Nassar","01150500021","12/10/2020",999));
        fatoraList.add(new Fatora("Nassar","01150500021","12/10/2020",999));
        fatoraAdapter = new FatoraAdapter(getActivity(),fatoraList);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewFatoraNotDone.setHasFixedSize(true);
        recyclerViewFatoraNotDone.setAdapter(fatoraAdapter);
        recyclerViewFatoraNotDone.setLayoutManager(layoutManager);

    }

    private void initView() {
        recyclerViewFatoraNotDone = view.findViewById(R.id.recyclerView_fatora_NotDone);
        fab = view.findViewById(R.id.fab);
    }
}