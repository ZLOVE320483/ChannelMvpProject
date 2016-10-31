package com.zlove.channelmvp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.zlove.channelmvp.fragment.user.LoginFragment;

public class MainActivity extends FragmentActivity {

    private LoginFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_act_fragment_container);
        fragment = new LoginFragment();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.id_framework, fragment).commit();
        }
    }
}
