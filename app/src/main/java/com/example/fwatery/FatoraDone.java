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

import java.util.ArrayList;
import java.util.List;

public class FatoraDone extends Fragment {

    RecyclerView recyclerViewFatoraDone;

    FatoraAdapter fatoraAdapter;
    RecyclerView.LayoutManager layoutManager;

    public FatoraDone() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
    List<Fatora> fatoraList;
    private void initRecyclerView() {
        fatoraList = new ArrayList<>();
        fatoraList.add(new Fatora("mohamed safwat","01117796570",55));
        fatoraList.add(new Fatora("Ali","01117796570",100));
        fatoraList.add(new Fatora("Seif","01117796570",400));
        fatoraList.add(new Fatora("Nassar","01117796570",555));
        fatoraList.add(new Fatora("Hamada","01117796570",111));
        fatoraList.add(new Fatora("Omar","01117796570",777));
        fatoraList.add(new Fatora("Nassar","01117796570",22));
        fatoraList.add(new Fatora("Nassar","01117796570",999));
        fatoraAdapter = new FatoraAdapter(getActivity(),fatoraList);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewFatoraDone.setHasFixedSize(true);
        recyclerViewFatoraDone.setAdapter(fatoraAdapter);
        recyclerViewFatoraDone.setLayoutManager(layoutManager);

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