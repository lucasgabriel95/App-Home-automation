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
                startActivity(Intent(this@HomeActivity,OpenGateActivity::class.java))
            }
        }
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        checkBiometricSupport()

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
}