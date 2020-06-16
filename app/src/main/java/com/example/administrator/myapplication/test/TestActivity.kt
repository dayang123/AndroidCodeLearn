package com.example.administrator.myapplication.test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.administrator.myapplication.R
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        initView()
    }

    private fun initView() {
        testEditText.setOnClickListener{TestEditTextActivity.startActivity(this)}
        testDrawableMemory.setOnClickListener{TestDrawableMemoryActivity.startActivity(this)}
        testLifeCycle.setOnClickListener{startActivity(Intent(this, TestLifeCycleActivityA::class.java))}
        testHandler.setOnClickListener{startActivity(Intent(this, TestHandlerActivity::class.java))}
    }

    companion object {
        @JvmStatic
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, TestActivity::class.java))
        }
    }

}