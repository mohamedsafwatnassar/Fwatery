package com.example.fwatery.Database.Daos;

import com.example.fwatery.Database.RealtimeDatabase;
import com.example.fwatery.Models.A3tal;
import com.example.fwatery.Models.Fatora;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class A3talDoneDao {

    public static String Id;

    public static void Add3otlDone(A3tal a3tal, OnSuccessListener<Void> onSuccessListener, OnFailureListener onFailureListener){

        Id = RealtimeDatabase.getA3talDone().push().getKey();
        a3tal.setId(Id);
        RealtimeDatabase.getA3talDone()
                .child(a3tal.getId())
                .setValue(a3tal)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }


    public static void getAlla3talDone(ValueEventListener valueEventListener){
        DatabaseReference reference = RealtimeDatabase.getA3talDone();
        reference.addValueEventListener(valueEventListener);
    }

    public static void delete3otlDone(String id, OnSuccessListener onSuccessListener){
        RealtimeDatabase.getA3talDone()
                .child(id)
                .removeValue()
                .addOnSuccessListener(onSuccessListener);

        //DatabaseReference reference = RealtimeDatabase.getFatoraNotDone();
        //reference.addValueEventListener(valueEventListener);
    }
}