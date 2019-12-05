package com.example.administrator.myapplication.multiprogress.aidl;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.TestAidl;

public class AidlClientActivity extends AppCompatActivity {

    private TestAidl aidl;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_client);
        setupService();
    }

    /**
     * bindService 多个客户端可以同时绑定一个service 但是所有的客户端退出之后  service就会停止运行
     * startService 可以保证客户端都退出之后  service 仍然正常运行
     */

    void setupService() {
        Intent intent = new Intent(this, AIDLService.class);
        bindService(intent, connection, Service.BIND_AUTO_CREATE);
        startService(intent);
    }


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

}
