package com.chapter05.forceoffline.demo.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.chapter05.forceoffline.demo.LoginActivity;
import com.chapter05.forceoffline.demo.utils.ActivityCollector;

/**
 * Created by Archer on 2017/10/31.
 */

public class BaseActivity extends AppCompatActivity {
    public static String ACTION_FORCE_OFFLINE = "com.chapter05.forceoffline.demo.FORCE_OFFLINE";
    private ForceOfflineReceiver mForceOfflineReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_FORCE_OFFLINE);
        mForceOfflineReceiver = new ForceOfflineReceiver();
        registerReceiver(mForceOfflineReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mForceOfflineReceiver != null) {
            unregisterReceiver(mForceOfflineReceiver);
            mForceOfflineReceiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    class ForceOfflineReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("You are forced to be offline.\nPlease try to login again.");
            builder.setCancelable(false);
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.finishAll();
                    Intent loginIntent = new Intent(context, LoginActivity.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(loginIntent);
                }
            });
            builder.show();
        }
    }
}
