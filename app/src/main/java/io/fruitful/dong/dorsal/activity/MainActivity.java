package io.fruitful.dong.dorsal.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import io.fruitful.dong.dorsal.R;
import io.fruitful.dong.dorsal.fragment.LoginFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginFragment fragmentListStories = new LoginFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame, fragmentListStories);
        fragmentTransaction.commit();


    }
}
