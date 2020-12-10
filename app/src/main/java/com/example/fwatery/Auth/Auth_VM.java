package com.example.fwatery.Auth;

import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fwatery.Database.Daos.UserDao;
import com.example.fwatery.Models.User;
import com.google.android.gms.common.util.DataUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Auth_VM extends ViewModel {

    public ObservableField<String> email = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");
    public MutableLiveData<String> emailError = new MutableLiveData<>(null);
    public MutableLiveData<String> passwordError = new MutableLiveData<>(null);
    public MutableLiveData<Boolean> progress = new MutableLiveData<>(null);
    public MutableLiveData<Boolean> success = new MutableLiveData<>(null);
    public MutableLiveData<String> Failed = new MutableLiveData<>(null);

    public void LoginCLick(){
        progress.setValue(true);
        if (validate()) {
            Login();
        }
            /*x.setValue("x");
        }else {
            progress.setValue(false);
        }*/
    }

    FirebaseAuth mAuth;
    private void Login() {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email.get(), password.get())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //saveUser(mAuth.getCurrentUser().getUid());
                            success.setValue(true);
                            progress.setValue(false);
                        }
                        else {
                            success.setValue(false);
                            progress.setValue(false);
                        }
                    }
                });
    }

    private void AddNewUser() {
        final User user = new User();
        user.setEmail(email.get());
        user.setId(mAuth.getCurrentUser().getUid());
        UserDao.addUser(user, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                success.setValue(true);
                progress.setValue(false);
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Failed.setValue(e.getLocalizedMessage());
                progress.setValue(false);
            }
        });
    }

    public Boolean validate(){
        boolean isValid = true;
        if (email.get().trim().isEmpty()){
            emailError.setValue("required");
            isValid = false;
        }else if (!ValidEmail()){
            emailError.setValue("Please enter a valid email");
            isValid = false;
        }else {
            emailError.setValue(null);
        }

        if (password.get().trim().isEmpty()) {
            passwordError.setValue("required");
            isValid = false;
        } else {
            passwordError.setValue(null);
        }
        return isValid;
    }

    private boolean ValidEmail() {
        if (Patterns.EMAIL_ADDRESS.matcher(email.get()).matches())
            return true;
        return false;
    }
}
