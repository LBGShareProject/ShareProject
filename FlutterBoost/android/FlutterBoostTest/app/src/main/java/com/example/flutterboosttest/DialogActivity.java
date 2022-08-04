package com.example.flutterboosttest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.idlefish.flutterboost.containers.FlutterBoostActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author wangguanghan
 * date:2022/7/13
 * Description:
 */
public class DialogActivity extends FlutterBoostActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void closeThisDialog(CloseDialog event){
        Log.e("wgh","hahaha");
        overridePendingTransition(0,0);
        this.finish();
    }
}
