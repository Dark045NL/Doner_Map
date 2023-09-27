package com.example.doner_map

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivityMap : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_map)

        val webView:WebView=findViewById(R.id.webView)
        webView.webViewClient= WebViewClient()

        val url="https://www.google.com/maps/search/d%C3%B6ner"
        webView.loadUrl(url)

        val webSettings=webView.settings
        webSettings.javaScriptEnabled=true
    }
}
