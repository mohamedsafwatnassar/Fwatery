package com.example.fwatery;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.fwatery.Models.A3tal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class RepairDone extends Fragment implements View.OnClickListener {

    RecyclerView recyclerViewA3tal;

    A3tal_VM vm;
    A3taalAdapter a3taalAdapter;
    RecyclerView.LayoutManager layoutManager;

    public RepairDone() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vm = new ViewModelProvider(getActivity()).get(A3tal_VM.class);
        view = inflater.inflate(R.layout.repair_done, container, false);
        getActivity().setTitle("أعطال                                  ");

        initView();
        initRecyclerView();

        return view;
    }

    private void initView() {
        recyclerViewA3tal = view.findViewById(R.id.a3tal_Recycler);
    }

    Dialog dialog;
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_3otl) {
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

    private void initRecyclerView() {
        a3taalAdapter = new A3taalAdapter(getActivity(), null);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewA3tal.setHasFixedSize(true);
        recyclerViewA3tal.setAdapter(a3taalAdapter);
        recyclerViewA3tal.setLayoutManager(layoutManager);

        vm.A3talDone.observe(getActivity(), new Observer<List<A3tal>>() {
            @Override
            public void onChanged(List<A3tal> a3tals) {
                a3taalAdapter.Change(a3tals);
            }
        });

        a3taalAdapter.setOnItem3tlOnClickListener(new A3taalAdapter.onItem3tlOnClickListener() {
            @Override
            public void onClick(int postion, A3tal a3tal) {
                dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.a3tal_done_onclick);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                //dialog.getWindow().setGravity(Gravity.CENTER);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                dialog.setCancelable(false);
                dialog.show();

                Button cancel = dialog.findViewById(R.id.cancel);
                TextView Name = dialog.findViewById(R.id.Name);
                Name.setText(a3tal.getName());
                TextView Address = dialog.findViewById(R.id.Phone);
                Address.setText(a3tal.getAddress());
                TextView Phone = dialog.findViewById(R.id.Address);
                Phone.setText(a3tal.getPhone());
                TextView Note = dialog.findViewById(R.id.Note);
                Note.setText(a3tal.getNote());
                TextView Date = dialog.findViewById(R.id.Date);
                Date.setText(a3tal.getDate());

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }

        });

        a3taalAdapter.setOnDeleteClickListner(new A3taalAdapter.onDeleteClickListner() {
            @Override
            public void onClick(int position, A3tal fatora) {
                vm.Delete3otlDone(fatora);
                a3taalAdapter.Delete(position);
                Toast.makeText(getActivity(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }

}