package com.example.uipractiseapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.uipractiseapp.drag_bubble.DragBubbleActivity;
import com.example.uipractiseapp.modify_density.ModifyDensityActivity;
import com.example.uipractiseapp.path_measure.PathMeasureActivity;
import com.example.uipractiseapp.percent_adaption.PercentAdaptionActivity;
import com.example.uipractiseapp.pixel_adaption.PixelAdaptionActivity;
import com.example.uipractiseapp.splash_view.SplashActivity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView splashTv, dragBubbleTv, pathMeasureTv, pixelAdaptionTv, percentAdaptionTv, mModifyDensityTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        splashTv = findViewById(R.id.splash_tv);
        dragBubbleTv = findViewById(R.id.drag_bubble_tv);
        pathMeasureTv = findViewById(R.id.path_measure_tv);
        pixelAdaptionTv = findViewById(R.id.pixel_adaption_tv);
        percentAdaptionTv = findViewById(R.id.percent_adaption_tv);
        mModifyDensityTv = findViewById(R.id.modify_density);
    }

    private void initListener() {
        splashTv.setOnClickListener(this);
        dragBubbleTv.setOnClickListener(this);
        pathMeasureTv.setOnClickListener(this);
        pixelAdaptionTv.setOnClickListener(this);
        percentAdaptionTv.setOnClickListener(this);
        mModifyDensityTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.splash_tv:
                startActivity(new Intent(this, SplashActivity.class));
                break;
            case R.id.drag_bubble_tv:
                startActivity(new Intent(this, DragBubbleActivity.class));
                break;
            case R.id.path_measure_tv:
                startActivity(new Intent(this, PathMeasureActivity.class));
                break;
            case R.id.pixel_adaption_tv:
                startActivity(new Intent(this, PixelAdaptionActivity.class));
                break;
            case R.id.percent_adaption_tv:
                startActivity(new Intent(this, PercentAdaptionActivity.class));
                break;
            case R.id.modify_density:
                startActivity(new Intent(this, ModifyDensityActivity.class));
                break;
            default:
                break;
        }
    }
}
