package com.example.carlos.ac_android.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.carlos.ac_android.R;
import com.example.carlos.ac_android.di.Injectable;
import com.example.carlos.ac_android.repository.local.entity.User;
import com.example.carlos.ac_android.viewmodel.UserViewModel;
import com.example.carlos.ac_android.vo.Resource;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements Injectable {

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView tvName = findViewById(R.id.tvName);

        userViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel.class);
        userViewModel.user.observe(this, new Observer<Resource<User>>() {
            @Override
            public void onChanged(@Nullable Resource<User> userResource) {
                if (userResource.data != null) {
                    tvName.setText(userResource.data.getName());
                }
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userViewModel.setLogin(1);
            }
        });
    }
}
