package com.example.administrator.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.administrator.myapplication.database.TestDataActivity

import com.example.administrator.myapplication.test.TestActivity
import kotlinx.android.synthetic.main.activity_main.jump_to_test
import kotlinx.android.synthetic.main.activity_main.jump_to_test_database

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        jump_to_test.setOnClickListener { v -> TestActivity.startActivity(v.context) }
        jump_to_test_database.setOnClickListener { v -> TestDataActivity.startActivity(v.context) }
    }
}
