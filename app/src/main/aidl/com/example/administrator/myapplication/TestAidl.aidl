// TestAidl.aidl
package com.example.administrator.myapplication;
import com.example.administrator.myapplication.multiprogress.aidl.AidlMessage;

// Declare any non-default types here with import statements

interface TestAidl {
    void sendMessage(in AidlMessage aidlMessage);
}
