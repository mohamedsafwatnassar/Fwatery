package com.example.fwatery;

import androidx.annotation.NonNull;
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
import java.util.List;

public class Fatora_Vm extends ViewModel {

    public MutableLiveData<Boolean> success = new MutableLiveData<>(null);
    public MutableLiveData<Boolean> Failed = new MutableLiveData<>(null);
    public MutableLiveData<List<Fatora>> FatoraNotDone = new MutableLiveData<>(null);
    public MutableLiveData<List<Fatora>> FatoraDone = new MutableLiveData<>(null);

    public void AddFatoraNotDone(Fatora fatora){
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
    public List<Fatora> getALLFatoraNotDone(){
        NotDoneList = new ArrayList<>();
        FatoraNotDoneDao.getAllFatoraNotDone(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fatoraNotDone = new Fatora();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    fatoraNotDone = snapshot1.getValue(Fatora.class);
                    NotDoneList.add(fatoraNotDone);
                }
                FatoraNotDone.setValue(NotDoneList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return NotDoneList;

    }


    public void addFatoraDone(Fatora fatora){
        FatoraDoneDao.AddFatoraDone(fatora, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                success.setValue(true);
                //getALLFatoraNotDone();
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Failed.setValue(true);
            }
        });
    }

    List<Fatora> fatoraDoneList;
    Fatora fatoraDone;
    public List<Fatora> getALLFatoraDone(){
        fatoraDoneList = new ArrayList<>();
        FatoraDoneDao.getAllFatoraDone(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fatoraDone = new Fatora();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    fatoraDone = snapshot1.getValue(Fatora.class);
                    fatoraDoneList.add(fatoraDone);
                }
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
                getALLFatoraNotDone();
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
}
