package com.uz.uzpcl

import android.content.Context
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.Formatter
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var wifiManager: WifiManager? = null
    var myWebView : WebView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager?
        val ipAddress = Formatter.formatIpAddress(wifiManager!!.connectionInfo.ipAddress)

        //ip.setText(ipAddress)


        myWebView = findViewById<View>(R.id.myWebView) as WebView?
        myWebView!!.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        val webSettings = myWebView!!.settings
        webSettings.javaScriptEnabled = true
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        webSettings.setAppCacheEnabled(true)
        webSettings.domStorageEnabled = true
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        webSettings.useWideViewPort = true
        webSettings.setEnableSmoothTransition(true)
        myWebView!!.loadUrl("http://192.168.1.20/uzpcl-copy/index.php")

    }

    override fun onBackPressed() {
        if(myWebView!!.canGoBack()){
            myWebView!!.goBack()
        }
    }
}
