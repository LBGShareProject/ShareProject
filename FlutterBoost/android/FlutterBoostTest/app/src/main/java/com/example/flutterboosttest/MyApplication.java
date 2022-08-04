package com.example.flutterboosttest;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.idlefish.flutterboost.EventListener;
import com.idlefish.flutterboost.FlutterBoost;
import com.idlefish.flutterboost.FlutterBoostDelegate;
import com.idlefish.flutterboost.FlutterBoostRouteOptions;
import com.idlefish.flutterboost.ListenerRemover;
import com.idlefish.flutterboost.containers.FlutterBoostActivity;

import java.util.HashMap;
import java.util.Map;

import io.flutter.Log;
import io.flutter.embedding.android.FlutterActivityLaunchConfigs;
import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.StandardMessageCodec;

/**
 * @author wangguanghan
 * date:2022/7/7
 * Description:
 */
public class MyApplication extends Application {
    private static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        FlutterBoost.instance().setup(this, new FlutterBoostDelegate() {
            @Override
            public void pushNativeRoute(FlutterBoostRouteOptions options) {
                //这里根据options.pageName来判断你想跳转哪个页面，这里简单给一个
                Log.e("pushNativeRoute  --   ", options.pageName());
                if (options.pageName().contains("native/page") && options.pageName().contains("saasAllTools")) {
                    Intent intent = new Intent(FlutterBoost.instance().currentActivity(), NativePageActivity.class);
                    final SerializableMap myMap = new SerializableMap();
                    myMap.setMap(options.arguments());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("map", myMap);
                    intent.putExtras(bundle);
                    FlutterBoost.instance().currentActivity().startActivityForResult(intent, options.requestCode());
                } else if (options.pageName().contains("native/func")) {
                    Map<Object,Object> map = new HashMap<>();
                    Map<String, Object> arguments = options.arguments();
                    map.put("args",arguments);
                    FlutterUtil.handleEvent(options.arguments().get("event").toString(),map);
                } else {
                    Intent intent = new Intent(FlutterBoost.instance().currentActivity(), TargetActivity.class);
                    FlutterBoost.instance().currentActivity().startActivityForResult(intent, options.requestCode());
                }
            }

            @Override
            public void pushFlutterRoute(FlutterBoostRouteOptions options) {
                Log.e("pushFlutterRoute  --   ", options.pageName());
                if ("openDialog".equals(options.pageName())) {
                    Intent intent = new DialogActivity.CachedEngineIntentBuilder(DialogActivity.class)
                            .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
                            .destroyEngineWithActivity(false)
                            .uniqueId(options.uniqueId())
                            .url(options.pageName())
                            .urlParams(options.arguments())
                            .build(FlutterBoost.instance().currentActivity());
                    FlutterBoost.instance().currentActivity().startActivityForResult(intent, 111);
                    Log.e("pushFlutterRoute  --   ", FlutterBoost.instance().currentActivity().toString());
                } else {
                    Intent intent = new FlutterBoostActivity.CachedEngineIntentBuilder(FlutterBoostActivity.class)
                            .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
                            .destroyEngineWithActivity(false)
                            .uniqueId(options.uniqueId())
                            .url(options.pageName())
                            .urlParams(options.arguments())
                            .build(FlutterBoost.instance().currentActivity());
                    FlutterBoost.instance().currentActivity().startActivity(intent);
                }
            }
        }, engine -> {
        });

        FlutterUtil.initFlutter(this);
    }

    public static MyApplication getInstance() {
        return application;
    }

}

