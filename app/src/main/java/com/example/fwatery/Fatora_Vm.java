package com.example.fwatery;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fwatery.Database.Daos.FatoraDoneDao;
import com.example.fwatery.Database.Daos.FatoraNotDoneDao;
import com.example.fwatery.Models.Fatora;
import com.example.fwatery.Models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fatora_Vm extends AndroidViewModel {

    public MutableLiveData<Boolean> success = new MutableLiveData<>(null);
    public MutableLiveData<Boolean> Failed = new MutableLiveData<>(null);
    public MutableLiveData<List<Fatora>> FatoraNotDone = new MutableLiveData<>(null);
    public MutableLiveData<List<Fatora>> FatoraDone = new MutableLiveData<>(null);
    public MutableLiveData<Double> TotalNot = new MutableLiveData<>(0.0);
    public MutableLiveData<Double> Totaldone = new MutableLiveData<>(0.0);
    public MutableLiveData<Boolean> Progress = new MutableLiveData<>(null);

    Context context ;

    public Fatora_Vm(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        getALLFatoraNotDone();
        getALLFatoraDone();
    }

    public void AddFatoraNotDone(Fatora fatora){
        Progress.setValue(true);
        FatoraNotDoneDao.AddFatoraNotDone(fatora, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                success.setValue(true);
                getALLFatoraNotDone();
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Failed.setValue(true);
            }
        });
    }

    List<Fatora> NotDoneList;
    Fatora fatoraNotDone;
    Double totalNotDone ;
    Double totalDone ;
    public List<Fatora> getALLFatoraNotDone(){
        Progress.setValue(true);

        FatoraNotDoneDao.getAllFatoraNotDone(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                NotDoneList = new ArrayList<>();
                totalNotDone = 0.0;
                fatoraNotDone = new Fatora();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    fatoraNotDone = snapshot1.getValue(Fatora.class);
                    NotDoneList.add(fatoraNotDone);
                    totalNotDone+=fatoraNotDone.getPrice()+ fatoraNotDone.getExtraPackage();
                }
                Collections.reverse(NotDoneList);
                TotalNot.setValue(totalNotDone);
                FatoraNotDone.setValue(NotDoneList);
                Progress.setValue(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            Progress.setValue(false);
            }
        });
        return NotDoneList;

    }



    List<Fatora> fatoraDoneList;
    Fatora fatoraDone;
    public List<Fatora> getALLFatoraDone(){

        FatoraDoneDao.getAllFatoraDone(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fatoraDone = new Fatora();
                fatoraDoneList = new ArrayList<>();
                totalDone = 0.0;
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    fatoraDone = snapshot1.getValue(Fatora.class);
                    fatoraDoneList.add(fatoraDone);
                    totalDone+=fatoraDone.getPrice()+ fatoraDone.getExtraPackage();
                }
                Progress.setValue(false);
                Totaldone.setValue(totalDone);
                Collections.reverse(fatoraDoneList);
                FatoraDone.setValue(fatoraDoneList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return fatoraDoneList;

    }

    public void tmEldf3(Fatora fatora){
        FatoraNotDoneDao.deleteFatoraNotDone(fatora.getId(), new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                FatoraDoneDao.AddFatoraDone(fatora, new OnSuccessListener<Void>() {
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

    public void DeleteFatoraNotDone(Fatora fatora){
        Progress.setValue(true);
        FatoraNotDoneDao.deleteFatoraNotDone(fatora.getId(), new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                success.setValue(true);
                Progress.setValue(false);
            }
        });

    }

    public void DeleteFatoraDone(Fatora fatora){
        Progress.setValue(true);
        FatoraDoneDao.deleteFatoraDone(fatora.getId(), new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                success.setValue(true);
                Progress.setValue(false);
            }
        });

    }
}
