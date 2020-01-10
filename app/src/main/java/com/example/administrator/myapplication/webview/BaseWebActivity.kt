package com.example.administrator.myapplication.webview

import android.annotation.TargetApi
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.administrator.myapplication.R
import kotlinx.android.synthetic.main.activity_base_webview.toolbar
import kotlinx.android.synthetic.main.activity_base_webview.web

/**
 * 参考于 https://juejin.im/post/59a56b2151882524424a1862#heading-5 可以作为WebView基础 参考
 *  https://juejin.im/post/5a94fb046fb9a0635865a2d6#heading-5    作为进阶 参考
 *  https://juejin.im/entry/573534f82e958a0069b27646     讲的是java和js 的相互调用
 */

abstract class BaseWebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_webview)
        initView()
        initWebView()
    }

    private fun initView() {
        toolbar.title = getToolbarTitle()
    }

    private fun initWebView() {
        setWebView()
        addJs()
        loadUrl()
    }

    /**
     * loadData() 用于加载本地HTML
     * loadUrl() 用于加载链接
     */
    private fun loadUrl() {
        web.loadUrl(getUrl())
    }

    fun reload() {
        if(web.url.isNullOrEmpty()) {
            throw Exception("url is null or empty, can't reload")  // TODO
        }
        web.reload()
    }

    private fun addJs() {
        web.addJavascriptInterface(JsInterface(this), JS_2_ANDROID)   // 在js 中通过  js_2_android.say() 调用里面的方法    js 调用 android
    }

    /**
     * 执行js命令
     * API19 以上 异步执行 js 命令 回调返回结果
     */
    fun js(jsOrder: String) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            web.evaluateJavascript("111 + 222") {
                Log.d(TAG, "result is $it")
            }
        } else {
            web.loadUrl("javascript:alert('hello')")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        web.webViewClient = null
        web.webChromeClient = null
        web.removeJavascriptInterface(JS_2_ANDROID)
        web.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
        web.clearHistory()
        // 传入true 表示清除内存和缓存   false表示仅清除缓存
        // 内核缓存是全局的  所以不仅仅针对webview 还有整个应用程序
        web.clearCache(true)
    }

    private val webViewClient = object: WebViewClient() {

        // 拦截页面加载，返回true表示宿主app拦截并处理了该url，否则返回false由当前WebView处理
        // 此方法在API24被废弃，不处理POST请求
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            Log.d(TAG, "shouldOverrideUrlLoading")
            return super.shouldOverrideUrlLoading(view, url)
        }

        // 拦截页面加载，返回true表示宿主app拦截并处理了该url，否则返回false由当前WebView处理
        // 此方法添加于API24，不处理POST请求，可拦截处理子frame的非http请求
        @TargetApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            Log.d(TAG, "shouldOverrideUrlLoading")
            request?.url
            return super.shouldOverrideUrlLoading(view, request)
        }

        // 此方法废弃于API21，调用于非UI线程
        // 拦截资源请求并返回响应数据，返回null时WebView将继续加载资源
        override fun shouldInterceptRequest(view: WebView?, url: String?): WebResourceResponse? {
            Log.d(TAG, "shouldInterceptRequest")
            return super.shouldInterceptRequest(view, url)
        }

        // 此方法添加于API21，调用于非UI线程
        // 拦截资源请求并返回数据，返回null时WebView将继续加载资源
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse? {
            Log.d(TAG, "shouldInterceptRequest")
            return super.shouldInterceptRequest(view, request)
        }

        //页面url开始加载
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            Log.d(TAG, "onPageStarted")
            super.onPageStarted(view, url, favicon)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            Log.d(TAG, "onPageFinished")
            super.onPageFinished(view, url)
        }

        //  每次加载资源就会调用
        override fun onLoadResource(view: WebView?, url: String?) {
            Log.d(TAG, "onLoadResource")
            super.onLoadResource(view, url)
        }

        // 此方法废弃于API23
        // 主框架加载资源时出错
        override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
            Log.d(TAG, "onReceivedError")
            view?.loadUrl("file://")
            super.onReceivedError(view, errorCode, description, failingUrl)
        }

        // 此方法添加于API23
        // 加载资源时出错，通常意味着连接不到服务器
        // 由于所有资源加载错误都会调用此方法，所以此方法应尽量逻辑简单
        @TargetApi(Build.VERSION_CODES.M)
        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            Log.d(TAG, "onReceivedError")
            if(request?.isForMainFrame == true) {
                onReceivedError(view, error?.errorCode?:0 , error?.description.toString(), request.url.toString())
            }
        }

        // 此方法添加于API23
        // 在加载资源(iframe,image,js,css,ajax...)时收到了 HTTP 错误(状态码>=400)
        override fun onReceivedHttpError(view: WebView?, request: WebResourceRequest?, errorResponse: WebResourceResponse?) {
            Log.d(TAG, "onReceivedHttpError")
            super.onReceivedHttpError(view, request, errorResponse)
        }

        //通知页面缩放系数变化
        override fun onScaleChanged(view: WebView?, oldScale: Float, newScale: Float) {
            Log.d(TAG, "onScaleChanged")
            super.onScaleChanged(view, oldScale, newScale)
        }
    }

    private val webChromeClient = object: WebChromeClient(){

        // 获得所有访问历史项目的列表，用于链接着色。
        override fun getVisitedHistory(callback: ValueCallback<Array<String>>?) {
            super.getVisitedHistory(callback)
        }

        // 当全屏的视频正在缓冲时，此方法返回一个占位视图(比如旋转的菊花)
        override fun getVideoLoadingProgressView(): View? {
            return super.getVideoLoadingProgressView()
        }

        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            Log.d(TAG, "onProgressChanged")
            super.onProgressChanged(view, newProgress)
        }

        override fun onReceivedTitle(view: WebView?, title: String?) {
            Log.d(TAG, "onReceivedTitle")
            super.onReceivedTitle(view, title)
        }

        // 获取网页图标
        override fun onReceivedIcon(view: WebView?, icon: Bitmap?) {
            Log.d(TAG, "onReceivedIcon")
            super.onReceivedIcon(view, icon)
        }
    }

    private fun setWebView(): WebSettings{
        web.webViewClient = webViewClient
        web.webChromeClient = webChromeClient
        return web.settings.apply {
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true            // 是否启用JavaScript 打开窗口 默认false

            setSupportZoom(true)                                   // 是否支持缩放
            displayZoomControls = false                             // 是否显示内置缩放控件
            useWideViewPort = true                                  // 自适应手机屏幕
            loadWithOverviewMode = true                             // 当页面宽度大于webview 的时候  所以页面宽度 等于webview

            defaultTextEncodingName = "UTF-8"                       //设置编码格式

            domStorageEnabled = true                                 // 启用HTML5 DOM storage API，默认值 false TODO
            databaseEnabled = true
        }
    }

    abstract fun getToolbarTitle(): String

    abstract fun getUrl(): String

    companion object {
        private const val TAG = "WebView"
        private const val JS_2_ANDROID = "js2Android"
    }

}