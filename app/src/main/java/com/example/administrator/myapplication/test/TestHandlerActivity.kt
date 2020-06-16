package com.example.administrator.myapplication.test

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class TestHandlerActivity : AppCompatActivity(){

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "A")
        Log.d(TAG, "B")
        handler.post({Log.d(TAG, "C")})
        Log.d(TAG, "D")
        Log.d(TAG, "E")
    }

    companion object{
        private const val TAG = "Handler Test"
    }
}