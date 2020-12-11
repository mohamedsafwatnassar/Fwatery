package com.example.fwatery;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fwatery.Models.Fatora;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FatoraNotDone extends Fragment {

    RecyclerView recyclerViewFatoraNotDone;
    Dialog dialog;
    View view ;
    FloatingActionButton fab;

    Fatora_Vm vm;
    FatoraAdapter fatoraAdapter;
    RecyclerView.LayoutManager layoutManager;

    public FatoraNotDone() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vm = new ViewModelProvider(getActivity()).get(Fatora_Vm.class);

        view = inflater.inflate(R.layout.fragment_fatora_not_done, container, false);


        getActivity().setTitle("فواتير غير مدفوعه                          ");

        initView();

        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {

        fatoraAdapter = new FatoraAdapter(getActivity(),null);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewFatoraNotDone.setHasFixedSize(true);
        recyclerViewFatoraNotDone.setAdapter(fatoraAdapter);
        recyclerViewFatoraNotDone.setLayoutManager(layoutManager);
        vm.getALLFatoraNotDone();
        vm.FatoraNotDone.observe(getActivity(), new Observer<List<Fatora>>() {
            @Override
            public void onChanged(List<Fatora> fatoras) {
                if(fatoras != null){
                    fatoraAdapter.Change(fatoras);
                    Collections.reverse(fatoras);
                }
            }
        });


        fatoraAdapter.setOnFatoraClickListener(new FatoraAdapter.onFatoraClickListener() {
            @Override
            public void onClick(int position, Fatora fatora) {
                dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.fatora_notdone_onclick);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                //dialog.getWindow().setGravity(Gravity.CENTER);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                dialog.setCancelable(false);
                dialog.show();

                Button cancel = dialog.findViewById(R.id.cancel);
                Button Daf3 = dialog.findViewById(R.id.Add_fatora);
                TextView Name = dialog.findViewById(R.id.Name);
                Name.setText(fatora.getName());
                TextView Address = dialog.findViewById(R.id.Phone);
                Address.setText(fatora.getAddress());
                TextView Phone = dialog.findViewById(R.id.Address);
                Phone.setText(fatora.getPhone());
                TextView Price = dialog.findViewById(R.id.Price);
                //Price.setText(fatora.getPrice());
                TextView Package = dialog.findViewById(R.id.ExtraPackage);
                //Package.setText(Integer.parseInt(String.valueOf(fatora.getExtraPackage())));
                TextView Note = dialog.findViewById(R.id.Note);
                Note.setText(fatora.getNote());
                TextView Date = dialog.findViewById(R.id.Date);
                Date.setText(fatora.getDate());

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                Daf3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vm.tmEldf3(fatora);
                        dialog.dismiss();
                    }
                });
            }
        });

    }

    private void initView() {
        recyclerViewFatoraNotDone = view.findViewById(R.id.recyclerView_fatora_NotDone);
        fab = view.findViewById(R.id.add_fat);

        if(Hawk.get("User").equals(false)){
            fab.setVisibility(View.INVISIBLE);
        }

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
                EditText Name = dialog.findViewById(R.id.Name);
                EditText Address = dialog.findViewById(R.id.Phone);
                EditText Phone = dialog.findViewById(R.id.Address);
                EditText Price = dialog.findViewById(R.id.Price);
                EditText Package = dialog.findViewById(R.id.ExtraPackage);
                EditText Note = dialog.findViewById(R.id.Note);

                addfatora.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fatora fatora1 = new Fatora();
                        fatora1.setName(Name.getText().toString());
                        fatora1.setAddress(Address.getText().toString());
                        fatora1.setNote(Note.getText().toString());
                        fatora1.setPhone(Phone.getText().toString());
                        fatora1.setPrice(Integer.parseInt(Price.getText().toString()));
                        fatora1.setExtraPackage(Integer.parseInt(Package.getText().toString()));
                        vm.AddFatoraNotDone(fatora1);
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