package com.example.fwatery.Database.Daos;

import com.example.fwatery.Database.RealtimeDatabase;
import com.example.fwatery.Models.Fatora;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class FatoraNotDoneDao {

    public static String FatoraId;

    public static void AddFatoraNotDone(Fatora fatora, OnSuccessListener<Void> onSuccessListener, OnFailureListener onFailureListener){

        FatoraId = RealtimeDatabase.getFatoraNotDone().push().getKey();
        fatora.setId(FatoraId);
        RealtimeDatabase.getFatoraNotDone()
                .child(fatora.getId())
                .setValue(fatora)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }


    public static void getAllFatoraNotDone(ValueEventListener valueEventListener){
        DatabaseReference reference = RealtimeDatabase.getFatoraNotDone();
        reference.addValueEventListener(valueEventListener);
    }

    public static void deleteFatoraNotDone(String fatoraId, OnSuccessListener onSuccessListener){
       RealtimeDatabase.getFatoraNotDone()
               .child(fatoraId)
               .removeValue()
               .addOnSuccessListener(onSuccessListener);

        //DatabaseReference reference = RealtimeDatabase.getFatoraNotDone();
        //reference.addValueEventListener(valueEventListener);
    }

    /*
    public static void deleteMessageByRoomId(Message message, OnCompleteListener<Void> onCompleteListener)  {
        OnlineDatabase.getMessagesRef()
                .child(message.getId())
                .removeValue()
                .addOnCompleteListener(onCompleteListener);
    }
     */
}
