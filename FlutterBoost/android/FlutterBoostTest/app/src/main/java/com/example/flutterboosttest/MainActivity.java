package com.example.flutterboosttest;

import static com.idlefish.flutterboost.containers.FlutterActivityLaunchConfigs.ACTIVITY_RESULT_KEY;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import com.idlefish.flutterboost.containers.FlutterBoostActivity;

import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import io.flutter.Log;
import io.flutter.embedding.android.FlutterActivityLaunchConfigs;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int REQUEST_CODE = 999;
    private TextView mOpenFlutter;
    private TextView mOpenFlutterFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0){
            finish();
            return;
        }

        setContentView(R.layout.native_page);

        mOpenFlutter = findViewById(R.id.open_flutter);
        mOpenFlutterFragment = findViewById(R.id.open_flutter_fragment);

        mOpenFlutter.setOnClickListener(this);
        mOpenFlutterFragment.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        Map<String, Object> params = new HashMap<>();
        params.put("string","im string params");
        params.put("bool", true);
        params.put("int", 666);
        //Add some params if needed.
        if (v == mOpenFlutter) {
            Intent intent = new FlutterBoostActivity.CachedEngineIntentBuilder(FlutterBoostActivity.class)
                    .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.opaque)
                    .destroyEngineWithActivity(false)
                    .url("flutterPage")
                    .urlParams(params)
                    .build(this);
            startActivityForResult(intent, REQUEST_CODE);
        } else if (v == mOpenFlutterFragment) {
            NativeRouter.openPageByUrl(this, NativeRouter.FLUTTER_FRAGMENT_PAGE_URL,params);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}