package com.example.automaocasa

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_cameras.*

class CamerasActivity : AppCompatActivity() {
    private val BASE_URL_CAM1 ="http://192.168.1.101:8080/video"
    private val BASE_URL_CAM2 ="http://192.168.1.101':8080/video"
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
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



        button_lampada_principal.setOnClickListener{

            val homeIntent = Intent(this,FullCamActivity::class.java)
            startActivity(homeIntent)
        }





    }
}