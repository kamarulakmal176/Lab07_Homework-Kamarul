package com.metech.lab07homework

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.metech.lab07homework.databinding.ActivityMainBinding
import com.metech.lab07homework.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val myWebView = binding.webview
        myWebView.webViewClient = WebViewClient()
        myWebView.loadUrl("https://www.androidatc.com")
        myWebView.setBackgroundColor(Color.TRANSPARENT)
        myWebView.settings.javaScriptEnabled = true
        myWebView.settings.loadsImagesAutomatically = true
        myWebView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY


    }
}