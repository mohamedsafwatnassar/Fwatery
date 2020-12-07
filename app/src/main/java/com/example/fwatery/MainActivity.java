package com.example.fwatery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bottonNav);

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_email));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_email));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_email));
        bottomNavigation.show(1,true);

        bottomNavigation.setOnClickMenuListener(model -> {
            switch (model.getId()) {
                case 1:
                    goToFragment(new FatoraNotDone());
                    break;
                case 2:
                    goToFragment(new FatoraDone());
                    break;
                case 3:
                    goToFragment(new a3taal());
                    break;
            }
            return null;
        });

        goToFragment(new FatoraNotDone());
    }

    private void goToFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null);
        transaction.replace(R.id.fragment_container, fragment)
                .commit();

    }

}