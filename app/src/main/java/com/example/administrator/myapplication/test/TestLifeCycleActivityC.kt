package com.example.administrator.myapplication.test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.administrator.myapplication.R
import kotlinx.android.synthetic.main.activity_test_lifecycle_a.*

/**
 * 这个Activity的启动模式是 singletask
 */

class TestLifeCycleActivityC : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_lifecycle_c)
        initView()
        Log.d(TAG, "OnCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "OnStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "OnRestart")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d(TAG, "OnNewIntent")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "OnDestroy")
    }

    private fun initView(){
        to_b_button.setOnClickListener{startActivity(Intent(this, TestLifeCycleActivityB::class.java))}
    }

    companion object {
        const val TAG = "LifeCycle + Activity C"
    }
}