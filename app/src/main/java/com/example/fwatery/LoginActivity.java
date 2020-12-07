package com.example.fwatery;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.fwatery.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity {

    Auth_VM authVm;

    ActivityLoginBinding loginBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        authVm = new ViewModelProvider(this).get(Auth_VM.class);

        loginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        loginBinding.setLvm(authVm);

        subscribeToLiveData();
    }

    private void subscribeToLiveData() {
        authVm.emailError.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null){
                    loginBinding.Lemail.setError(s);
                }
            }
        });

        authVm.passwordError.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null){
                    loginBinding.Lpassword.setError(s);
                }
            }
        });

        authVm.progress.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean != null && aBoolean){
                    showProgressDialog("Loading...");
                }else if (aBoolean != null && !aBoolean) {
                    hideProgressDialog();
                }
            }
        });

        authVm.x.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s == "x"){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                    Log.e("x",loginBinding.Lemail.getEditText().getText().toString());
                    Log.e("x",loginBinding.Lpassword.getEditText().getText().toString());
                }
            }
        });

    }

}