package com.example.fwatery;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fwatery.Models.Fatora;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FatoraDone extends Fragment {

    RecyclerView recyclerViewFatoraDone;

    FatoraAdapter fatoraAdapter;
    RecyclerView.LayoutManager layoutManager;

    public FatoraDone() {
        // Required empty public constructor
    }

    Fatora_Vm vm;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vm = new ViewModelProvider(getActivity()).get(Fatora_Vm.class);
        view = inflater.inflate(R.layout.fragment_fatora_done, container, false);

        getActivity().setTitle("فواتير مدفوعه                            ");
        initView();
        initRecyclerView();
        return view;
    }

    private void initView() {
        recyclerViewFatoraDone = view.findViewById(R.id.recyclerView_fatora_Done);
    }

    Dialog dialog;
    private void initRecyclerView() {
        fatoraAdapter = new FatoraAdapter(getActivity(),null);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewFatoraDone.setHasFixedSize(true);
        recyclerViewFatoraDone.setAdapter(fatoraAdapter);
        recyclerViewFatoraDone.setLayoutManager(layoutManager);

        vm.getALLFatoraDone();
        vm.FatoraDone.observe(getActivity(), new Observer<List<Fatora>>() {
            @Override
            public void onChanged(List<Fatora> fatoras) {
                if(fatoras!=null) {
                    fatoraAdapter.Change(fatoras);
                    Collections.reverse(fatoras);
                }
            }
        });

        fatoraAdapter.setOnFatoraClickListener(new FatoraAdapter.onFatoraClickListener() {
            @Override
            public void onClick(int position, Fatora fatora) {
                dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.fatora_done_onclick);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                //dialog.getWindow().setGravity(Gravity.CENTER);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                dialog.setCancelable(false);
                dialog.show();

                Button cancel = dialog.findViewById(R.id.cancel);

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