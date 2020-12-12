package com.example.fwatery;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.fwatery.Database.Daos.A3talDoneDao;
import com.example.fwatery.Database.Daos.A3talNotDoneDao;
import com.example.fwatery.Database.Daos.FatoraDoneDao;
import com.example.fwatery.Database.Daos.FatoraNotDoneDao;
import com.example.fwatery.Models.A3tal;
import com.example.fwatery.Models.Fatora;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class A3tal_VM extends AndroidViewModel {
    public MutableLiveData<Boolean> success = new MutableLiveData<>(null);
    public MutableLiveData<Boolean> Failed = new MutableLiveData<>(null);
    public MutableLiveData<List<A3tal>> A3talNotDone = new MutableLiveData<>(null);
    public MutableLiveData<List<A3tal>> A3talDone = new MutableLiveData<>(null);



    public A3tal_VM(@NonNull Application application) {
        super(application);
        getALLA3talNotDone();
        getALLA3taDone();
    }

    public void AddA3talNotDone(A3tal a3tal){
        A3talNotDoneDao.Add3otlNotDone(a3tal, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                success.setValue(true);
                getALLA3talNotDone();
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Failed.setValue(true);
            }
        });
    }


    List<A3tal> NotDoneList;
    A3tal a3taalNotDone;

    public List<A3tal> getALLA3talNotDone(){

        A3talNotDoneDao.getAlla3talNotDone(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                NotDoneList = new ArrayList<>();
                a3taalNotDone = new A3tal();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    a3taalNotDone = snapshot1.getValue(A3tal.class);
                    NotDoneList.add(a3taalNotDone);

                }
                Collections.reverse(NotDoneList);
                A3talNotDone.setValue(NotDoneList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return NotDoneList;

    }


    List<A3tal> A3talDoneList;
    A3tal a3talDone;
    public List<A3tal> getALLA3taDone(){

        A3talDoneDao.getAlla3talDone(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                a3talDone = new A3tal();
                A3talDoneList = new ArrayList<>();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    a3talDone = snapshot1.getValue(A3tal.class);
                    A3talDoneList.add(a3talDone);
                }
                Collections.reverse(A3talDoneList);
                A3talDone.setValue(A3talDoneList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return A3talDoneList;

    }

    public void tmtsle7(A3tal a3tal){
        A3talNotDoneDao.delete3otlNotDone(a3tal.getId(), new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                A3talDoneDao.Add3otlDone(a3tal, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //getALLFatoraNotDone();
                        success.setValue(true);
                    }
                }, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        });
    }

    public void Delete3otlNotDone(A3tal a3tal){

        A3talNotDoneDao.delete3otlNotDone(a3tal.getId(), new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                success.setValue(true);
            }
        });

    }

    public void Delete3otlDone(A3tal a3tal){
        A3talDoneDao.delete3otlDone(a3tal.getId(), new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                success.setValue(true);
            }
        });

    }

}
