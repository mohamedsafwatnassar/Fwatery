package com.example.fwatery.Database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RealtimeDatabase {

    private static final String User_Ref = "Users";
    private static final String FatoraDone_Ref = "Fatorda_Done";

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

}
