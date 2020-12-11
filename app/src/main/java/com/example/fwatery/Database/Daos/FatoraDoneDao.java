package com.example.fwatery.Database.Daos;

import com.example.fwatery.Database.RealtimeDatabase;
import com.example.fwatery.Models.Fatora;
import com.example.fwatery.Models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class FatoraDoneDao {

    public static String FatoraId;

    public static void AddFatoraDone(Fatora fatora, OnSuccessListener<Void> onSuccessListener, OnFailureListener onFailureListener){

        FatoraId = RealtimeDatabase.getFatoraDone().push().getKey();
        fatora.setId(FatoraId);
        RealtimeDatabase.getFatoraDone()
                .child(fatora.getId())
                .setValue(fatora)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }


    public static void getAllFatoraDone(ValueEventListener valueEventListener){
        DatabaseReference reference = RealtimeDatabase.getFatoraDone();
        reference.addValueEventListener(valueEventListener);
    }

    public static void deleteFatoraDone(String fatoraId, OnSuccessListener onSuccessListener){
        RealtimeDatabase.getFatoraDone()
                .child(fatoraId)
                .removeValue()
                .addOnSuccessListener(onSuccessListener);

        //DatabaseReference reference = RealtimeDatabase.getFatoraNotDone();
        //reference.addValueEventListener(valueEventListener);
    }
}