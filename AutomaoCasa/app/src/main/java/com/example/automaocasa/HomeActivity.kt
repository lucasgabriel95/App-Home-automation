package com.example.automaocasa

import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.view.*
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.activity_register.view.*


class HomeActivity : AppCompatActivity() {
    private var cancellationSignal: CancellationSignal? = null
    private val authenticationCallback: BiometricPrompt.AuthenticationCallback
    get() =
        @RequiresApi(Build.VERSION_CODES.P)
        object : BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                notifyUser("Erro na autenticação: $errString")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                notifyUser("Autenticado com sucesso!")

                val status = StatusButtonMenu ( txt_button_portao.text.toString());
                txt_button_portao.setTextColor(status.ColorButton())
                txt_button_portao.text = status.StatusButton()

            }
        }
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        checkBiometricSupport()
        button_lampada_principal.textView

        button_lampada_principal.setOnClickListener {
            val status = StatusButtonMenu ( txt_button_Lampada.text.toString());
            txt_button_Lampada.setTextColor(status.ColorButton())
            txt_button_Lampada.text = status.StatusButton()
            imv_button_lampada.setImageResource(status.ImbgButton());



        }

        button_lampada_sanca.setOnClickListener {
            val status = StatusButtonMenu ( txt_button_Sanca.text.toString());
            txt_button_Sanca.setTextColor(status.ColorButton())
            txt_button_Sanca.text = status.StatusButton()
            imv_button_sanca.setImageResource(status.ImbgButton());
        }

        button_fita_led.setOnClickListener {
            val status = StatusButtonMenu ( txt_button_fita.text.toString());
            txt_button_fita.setTextColor(status.ColorButton())
            txt_button_fita.text = status.StatusButton()
            imv_button_fita.setImageResource(status.ImbgButton());
        }

        button_spoot_focal.setOnClickListener {
            val status = StatusButtonMenu ( txt_button_spot_focal.text.toString());
            txt_button_spot_focal.setTextColor(status.ColorButton())
            txt_button_spot_focal.text = status.StatusButton()
            imv_button_spot_focal.setImageResource(status.ImbgButton());
        }

        button_ventilador.setOnClickListener {
            val status = StatusButtonMenu ( txt_button_ventilador.text.toString());
            txt_button_ventilador.setTextColor(status.ColorButton())
            txt_button_ventilador.text = status.StatusButton()
            imv_button_ventilador.setImageResource(status.ImbgButton());
        }

        button_cameras.setOnClickListener {
            showCameras()
        }

        button_controle_portao.setOnClickListener {


            val biometricPrompt = BiometricPrompt.Builder(this)
                .setTitle("Autenticação da digital")
                .setSubtitle("Autenciação para abertura do portão")
                .setDescription("Para a abertura do portão, por favor coloque seu deto no leitor biometrico. ")
                .setNegativeButton("Cancelar",this.mainExecutor, DialogInterface.OnClickListener { dialog, which ->
                    notifyUser("Autenticação cancelada")
                }).build()

            biometricPrompt.authenticate(getCancellationSignal(),mainExecutor,authenticationCallback)

        }





    }
    private fun notifyUser(message:String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
    private fun getCancellationSignal(): CancellationSignal{
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener{
            notifyUser("Autenticação cancelada pelo usuario")
        }
        return cancellationSignal as CancellationSignal
    }

    private fun checkBiometricSupport ():Boolean{
        val keyguardManager  = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

        if(!keyguardManager.isKeyguardSecure){
            notifyUser("A opção de da digital não esta ativa em configurações")
            return false
        }

        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.USE_BIOMETRIC)!= PackageManager.PERMISSION_GRANTED){
            notifyUser("Você não tem permissão para adasdad")
            return false
        }

        return if (packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT))
        {
            true
        }else true
    }

    private fun showCameras(){
        val homeIntent = Intent(this,CamerasActivity::class.java)
        startActivity(homeIntent)
    }

    private fun timer ()
    {

    }

}