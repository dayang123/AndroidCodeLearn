package com.example.administrator.myapplication.multiprogress.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.administrator.myapplication.TestAidl;

public class AIDLService extends Service {

    private static final String TAG = "AIDLService";

    IBinder message = new TestAidl.Stub() {
        @Override
        public void sendMessage(AidlMessage aidlMessage) throws RemoteException {
            Log.d(TAG, "穿过来的信息是" + aidlMessage.message);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return message;
    }
}
