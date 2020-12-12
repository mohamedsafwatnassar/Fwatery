package com.example.fwatery;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fwatery.Models.Fatora;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.fwatery.MainActivity.bottomNavigation;

public class FatoraDone extends Fragment {

    RecyclerView recyclerViewFatoraDone;
    TextView Total ;
    TextView numList ;

    FatoraAdapter fatoraAdapter;
    RecyclerView.LayoutManager layoutManager;

    public FatoraDone() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bottomNavigation.show(2,false);
    }

    Fatora_Vm vm;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vm = new ViewModelProvider(getActivity()).get(Fatora_Vm.class);
        view = inflater.inflate(R.layout.fragment_fatora_done, container, false);


        getActivity().setTitle("فواتير مدفوعه                           ");
        initView();
        initRecyclerView();
        return view;
    }

    private void initView() {
        recyclerViewFatoraDone = view.findViewById(R.id.recyclerView_fatora_Done);
        Total = view.findViewById(R.id.Total);
        numList = view.findViewById(R.id.numList);

        vm.Totaldone.observe(getActivity(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                Total.setText(""+aDouble+" $");
            }
        });

    }

    Dialog dialog;
    private void initRecyclerView() {
        fatoraAdapter = new FatoraAdapter(getActivity(),null);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewFatoraDone.setHasFixedSize(true);
        recyclerViewFatoraDone.setAdapter(fatoraAdapter);
        recyclerViewFatoraDone.setLayoutManager(layoutManager);

        vm.FatoraDone.observe(getActivity(), new Observer<List<Fatora>>() {
            @Override
            public void onChanged(List<Fatora> fatoras) {
                if(fatoras!=null) {
                    fatoraAdapter.Change(fatoras);
                    numList.setText(""+fatoras.size());
                }
            }
        });


        fatoraAdapter.setOnDeleteClickListner(new FatoraAdapter.onDeleteClickListner() {
            @Override
            public void onClick(int position, Fatora fatora) {
                vm.DeleteFatoraDone(fatora);
                fatoraAdapter.Delete(position);
                Toast.makeText(getActivity(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
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
                TextView Name = dialog.findViewById(R.id.Name);
                Name.setText(fatora.getName());
                TextView Address = dialog.findViewById(R.id.Phone);
                Address.setText(fatora.getAddress());
                TextView Phone = dialog.findViewById(R.id.Address);
                Phone.setText(fatora.getPhone());
                TextView Price = dialog.findViewById(R.id.Price);
                Price.setText(Float.toString(fatora.getPrice()));
                TextView Package = dialog.findViewById(R.id.ExtraPackage);
                Package.setText(Float.toString(fatora.getExtraPackage()));
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
            }
        });
    }
}