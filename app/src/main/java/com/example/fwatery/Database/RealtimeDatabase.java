package com.example.fwatery.Database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RealtimeDatabase {

    private static final String User_Ref = "Users";
    private static final String FatoraDone_Ref = "Fatorda_Done";
    private static final String FatoraNotDone_Ref = "Fatorda_Not_Done";
    private static final String A3talNotDone_Ref = "A3tal_Not_Done";
    private static final String A3talDone_Ref = "A3tal_Done";

    private static FirebaseDatabase database;

    public static FirebaseDatabase getInstance() {
        if (database == null) {
            database = FirebaseDatabase.getInstance();
        }
        return database;
    }

    public static DatabaseReference getUserRef(){
        return getInstance().getReference().child(User_Ref);
    }
    public static DatabaseReference getFatoraDone(){
        return getInstance().getReference().child(FatoraDone_Ref);
    }

   public static DatabaseReference getFatoraNotDone(){
        return getInstance().getReference().child(FatoraNotDone_Ref);
    }

    public static DatabaseReference getA3talNotDone(){
        return getInstance().getReference().child(A3talNotDone_Ref);
    }

    public static DatabaseReference getA3talDone(){
        return getInstance().getReference().child(A3talDone_Ref);
    }

}
