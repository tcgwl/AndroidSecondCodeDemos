package com.chapter10.downloadservice.demo;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chapter10.downloadservice.demo.service.DownloadService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button startDownloadBtn;
    private Button pauseDownloadBtn;
    private Button cancelDownloadBtn;

    private DownloadService.DownloadBinder mDownloadBinder;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mDownloadBinder = (DownloadService.DownloadBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startDownloadBtn = (Button) findViewById(R.id.start_download_btn);
        pauseDownloadBtn = (Button) findViewById(R.id.pause_download_btn);
        cancelDownloadBtn = (Button) findViewById(R.id.cancel_download_btn);

        startDownloadBtn.setOnClickListener(this);
        pauseDownloadBtn.setOnClickListener(this);
        cancelDownloadBtn.setOnClickListener(this);

        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{ Manifest.permission. WRITE_EXTERNAL_STORAGE }, 1);
        }
    }

    @Override
    public void onClick(View v) {
        if (mDownloadBinder == null)
            return;
        if (v == startDownloadBtn) {
            String url = "http://download1.douding.cn/android/DocinReaderX6_3.8.2_DocIn.apk";
//            String url = "https://raw.githubusercontent.com/guolindev/eclipse/master/eclipse-inst-win64.exe";
            mDownloadBinder.startDownload(url);
        } else if (v == pauseDownloadBtn) {
            mDownloadBinder.pauseDownload();
        } else if (v == cancelDownloadBtn) {
            mDownloadBinder.cancelDownload();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
    }
}
