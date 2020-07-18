package com.example.automaocasa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_cameras.*
import kotlinx.android.synthetic.main.activity_full_cam.*

class FullCamActivity : AppCompatActivity() {
    private val BASE_URL_CAM1 ="http://192.168.1.103:8080/video"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_full_cam)

        //Cam1
        webview_full.webChromeClient = object : WebChromeClient(){

        }
        webview_full.webViewClient = object : WebViewClient(){

        }
        val cam1 = webview_full.settings
        cam1.javaScriptEnabled = true
        webview_full.loadUrl(BASE_URL_CAM1)
    }
}