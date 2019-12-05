package com.example.administrator.myapplication.test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.administrator.myapplication.R
import kotlinx.android.synthetic.main.activity_test_drawable.button_1
import kotlinx.android.synthetic.main.activity_test_drawable.button_2
import kotlinx.android.synthetic.main.activity_test_drawable.button_3
import kotlinx.android.synthetic.main.activity_test_drawable.iv_1
import kotlinx.android.synthetic.main.activity_test_drawable.iv_2
import kotlinx.android.synthetic.main.activity_test_drawable.iv_3

/**
 * 测试 相同的图片在不同的drawable 文件夹下面的 所占内存大小
 * 测试 2倍 3倍 在相同的drawable 文件夹下面所占的内存大小
 * 使用的内存检测工具是  android studio 提供的 分析工具
 */

class TestDrawableMemoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_drawable)
        setViews()
    }


    private fun setViews() {
        button_1.setOnClickListener(onClickListener)
        button_2.setOnClickListener(onClickListener)
        button_3.setOnClickListener(onClickListener)
    }

    private val onClickListener = View.OnClickListener {
        when(it.tag.toString().toInt()) {
            1 -> {iv_1.visibility = View.VISIBLE}
            2 -> {iv_2.visibility = View.VISIBLE}
            3 -> { iv_3.visibility = View.VISIBLE}
        }
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, TestDrawableMemoryActivity::class.java))
        }
    }
}