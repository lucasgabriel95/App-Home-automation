package com.example.automaocasa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_cameras.*

class CamerasActivity : AppCompatActivity() {
    private val BASE_URL_CAM1 ="http://192.168.1.103:8080/video"
    private val BASE_URL_CAM2 ="http://192.168.1.103:8080/video"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cameras)


        //Cam1
       webView_cam1.webChromeClient = object : WebChromeClient(){

        }
        webView_cam1.webViewClient = object : WebViewClient(){

        }
        val cam1 = webView_cam1.settings
        cam1.javaScriptEnabled = true
        webView_cam1.loadUrl(BASE_URL_CAM1)

        button_webView_cam1.setOnClickListener{
            val homeIntent = Intent(this,FullCamActivity::class.java)
            startActivity(homeIntent)
        }





    }
}