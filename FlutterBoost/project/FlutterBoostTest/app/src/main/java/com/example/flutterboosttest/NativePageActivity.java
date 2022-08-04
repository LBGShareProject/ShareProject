package com.example.flutterboosttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.idlefish.flutterboost.EventListener;
import com.idlefish.flutterboost.FlutterBoost;
import com.idlefish.flutterboost.ListenerRemover;
import com.idlefish.flutterboost.containers.FlutterBoostActivity;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import io.flutter.Log;
import io.flutter.embedding.android.FlutterActivityLaunchConfigs;


public class NativePageActivity extends AppCompatActivity implements View.OnClickListener {

    private ListenerRemover remover;
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.flutter_jump_native_page);

        EventListener listener = (key, args) -> {
            //deal with your event here
            Log.e("EventListener  -key-   ", key);
            FlutterUtil.handleEvent(args.get("event").toString(),args);
        };
        remover = FlutterBoost.instance().addEventListener(FlutterUtil.methodChannel, listener);

        Bundle bundle = getIntent().getExtras();
        SerializableMap serializableMap = (SerializableMap) bundle.get("map");
        Map map = serializableMap.getMap();
        TextView tv_params = findViewById(R.id.tv_params);
        String params = map.get("params").toString();
        if (!TextUtils.isEmpty(params)){
            tv_params.setText(params);
        }
        Log.e("serializableMap :  " , map.size()+"");
        Log.e("serializableMap :  " , params);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        remover.remove();
    }
}