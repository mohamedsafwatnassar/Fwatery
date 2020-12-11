package com.example.fwatery;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
    Fatora fatoraNotDone ;

    public List<Fatora> getALLFatoraNotDone(){
        NotDoneList = new ArrayList<>();
        fatoraNotDone = new Fatora();
        FatoraNotDoneDao.getAllFatoraNotDone(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
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

}
