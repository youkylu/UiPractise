package com.example.uipractiseapp.modify_density;

import android.os.Bundle;

import com.example.uipractiseapp.modify_density.Density;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Density.setDensity(getApplication(),this);
    }
}
