package com.example.flutterboosttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.idlefish.flutterboost.containers.FlutterBoostActivity;

import java.util.HashMap;
import java.util.Map;

import io.flutter.embedding.android.FlutterActivityLaunchConfigs;

/**
 * @author wangguanghan
 * date:2022/7/7
 * Description:
 */
public class TargetActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.native_page);
        findViewById(R.id.open_native).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.open_flutter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> params = new HashMap<>();
                params.put("string","im string params");
                params.put("bool", true);
                params.put("int", 666);
                Intent intent = new FlutterBoostActivity.CachedEngineIntentBuilder(FlutterBoostActivity.class)
                        .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.opaque)
                        .destroyEngineWithActivity(false)
                        .url("flutterPage")
                        .urlParams(params)
                        .build(TargetActivity.this);
                startActivityForResult(intent, 111);
            }
        });
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra("msg","This message is from Native!!!");
        setResult(Activity.RESULT_OK, intent);  // 返回结果给dart
        super.finish();
    }
}
