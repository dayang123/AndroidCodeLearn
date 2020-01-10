package com.example.administrator.myapplication.webview

import android.content.Context
import android.webkit.JavascriptInterface
import android.widget.Toast

class JsInterface(private val context: Context) {


    @JavascriptInterface                              // 标签标注的方法 才会注入js
    fun say(string: String) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "JsInterface"
    }
}