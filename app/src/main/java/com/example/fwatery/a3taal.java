package com.example.fwatery;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class a3taal extends Fragment {

    Dialog dialog;
    View view ;
    FloatingActionButton fab ;

    public a3taal() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_a3taal, container, false);
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
        });





        return view;
    }
}