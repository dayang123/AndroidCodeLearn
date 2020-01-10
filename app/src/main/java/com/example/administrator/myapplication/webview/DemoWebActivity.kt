package com.example.administrator.myapplication.webview

import android.content.Context
import android.content.Intent

class DemoWebActivity : BaseWebActivity() {

    override fun getUrl(): String {
        return "file:///android_asset/js2Android.html"
    }

    override fun getToolbarTitle(): String {
        return "我是百度"
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, DemoWebActivity::class.java))
        }
    }
}