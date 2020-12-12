package com.example.fwatery.Database.Daos;

import com.example.fwatery.Database.RealtimeDatabase;
import com.example.fwatery.Models.A3tal;
import com.example.fwatery.Models.Fatora;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class A3talNotDoneDao {

    public static String id;

    public static void Add3otlNotDone(A3tal a3tal, OnSuccessListener<Void> onSuccessListener, OnFailureListener onFailureListener){

        id = RealtimeDatabase.getA3talNotDone().push().getKey();
        a3tal.setId(id);
        RealtimeDatabase.getA3talNotDone()
                .child(a3tal.getId())
                .setValue(a3tal)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }


    public static void getAlla3talNotDone(ValueEventListener valueEventListener){
        DatabaseReference reference = RealtimeDatabase.getA3talNotDone();
        reference.addValueEventListener(valueEventListener);
    }

    public static void delete3otlNotDone(String id, OnSuccessListener onSuccessListener){
        RealtimeDatabase.getA3talNotDone()
                .child(id)
                .removeValue()
                .addOnSuccessListener(onSuccessListener);

        //DatabaseReference reference = RealtimeDatabase.getFatoraNotDone();
        //reference.addValueEventListener(valueEventListener);
    }




}