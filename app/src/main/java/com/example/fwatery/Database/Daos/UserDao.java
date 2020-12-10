package com.example.fwatery.Database.Daos;

import com.example.fwatery.Database.RealtimeDatabase;
import com.example.fwatery.Models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class UserDao {

    public static String UserId;

    public static void addUser(User user, OnSuccessListener<Void> onSuccessListener, OnFailureListener onFailureListener){

        UserId = RealtimeDatabase.getUserRef().push().getKey();
        user.setId(UserId);
        RealtimeDatabase.getUserRef()
                .child(user.getId())
                .setValue(user)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }

    public static void getUser(String authId, ValueEventListener valueEventListener){
        DatabaseReference reference = RealtimeDatabase.getUserRef().child(authId);
        reference.addListenerForSingleValueEvent(valueEventListener);
    }

    public static void getAllUser(ValueEventListener valueEventListener){
        DatabaseReference reference = RealtimeDatabase.getUserRef();
        reference.addValueEventListener(valueEventListener);
    }
}