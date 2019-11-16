package com.example.administrator.myapplication.database

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.administrator.myapplication.R

class TestDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_database)
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, TestDataActivity::class.java))
        }
    }
}