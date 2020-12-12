package com.example.fwatery;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fwatery.Base.BaseFragment;
import com.example.fwatery.Models.A3tal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class RepairNotDone extends BaseFragment {

    RecyclerView recyclerViewA3tal;
    A3taalAdapter a3taalAdapter;
    RecyclerView.LayoutManager layoutManager;

    A3tal_VM vm;

    FloatingActionButton fab;
    Dialog dialog;
    View view;

    public RepairNotDone() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vm = new ViewModelProvider(getActivity()).get(A3tal_VM.class);
        view = inflater.inflate(R.layout.repair_not_done, container, false);
        getActivity().setTitle("أعطال                                ");

        initView();
        initRecyclerView();


        return view;
    }

    private void initView() {
        recyclerViewA3tal = view.findViewById(R.id.a3tal_Recycler);
        fab = view.findViewById(R.id.add_3otl);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                EditText Name = dialog.findViewById(R.id.Name);
                EditText Address = dialog.findViewById(R.id.Phone);
                EditText Phone = dialog.findViewById(R.id.Address);
                EditText Note = dialog.findViewById(R.id.Note);

                add3otl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        A3tal a3tal = new A3tal();
                        a3tal.setName(Name.getText().toString());
                        a3tal.setAddress(Address.getText().toString());
                        a3tal.setNote(Note.getText().toString());
                        a3tal.setPhone(Phone.getText().toString());
                        vm.AddA3talNotDone(a3tal);
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

    private void initRecyclerView() {
        a3taalAdapter = new A3taalAdapter(getActivity(), null);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewA3tal.setHasFixedSize(true);
        recyclerViewA3tal.setAdapter(a3taalAdapter);
        recyclerViewA3tal.setLayoutManager(layoutManager);

           a3taalAdapter.setOnDeleteClickListner(new A3taalAdapter.onDeleteClickListner() {
            @Override
            public void onClick(int position, A3tal fatora) {
                vm.Delete3otlNotDone(fatora);
                a3taalAdapter.Delete(position);
                Toast.makeText(getActivity(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
            }
        });


        vm.A3talNotDone.observe(getActivity(), new Observer<List<A3tal>>() {
            @Override
            public void onChanged(List<A3tal> a3tals) {
                if (a3tals != null) {
                    a3taalAdapter.Change(a3tals);
                }
            }
        });


        a3taalAdapter.setOnItem3tlOnClickListener(new A3taalAdapter.onItem3tlOnClickListener() {
            @Override
            public void onClick(int postion, A3tal a3tal) {
                dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.a3tal_notdone_onclick);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                //dialog.getWindow().setGravity(Gravity.CENTER);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                dialog.setCancelable(false);
                dialog.show();

                Button cancel = dialog.findViewById(R.id.cancel);
                Button Done_tasle7 = dialog.findViewById(R.id.Done_tasle7);
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

                Done_tasle7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vm.tmtsle7(a3tal);
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