package com.example.automaocasa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.edit_password_register

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        lbl_login.setOnClickListener {
            openNextActivity()
        }
        authentication()
    }

   private  fun openNextActivity (){
       val intent = Intent(this,LoginActivity::class.java)
       startActivity(intent)
   }
    private fun authentication (){
        btn_register.setOnClickListener {

            if (edit_email_register.text.isNotEmpty() && edit_password_register.text.isNotEmpty()){
                if (edit_password_register.text.toString() == edit_password_confirm_register.text.toString())
                {
                    FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(edit_email_register.text.toString(),
                            edit_password_register.text.toString()).addOnCompleteListener {
                            if(it.isSuccessful)
                            {
                                showHome()
                            }else{
                                showAlert()
                            }
                        }
                }
                else
                {
                    showAlert()
                }
            }
            else
            {
                showAlert()
            }
        }
    }
    private fun showAlert (){
        var message = ""
        if (edit_email_register.text.isEmpty() && edit_password_register.text.isEmpty() && edit_password_confirm_register.text.isEmpty())
        {
            message = ("Os campos não pode ficar em branco.")
        }
        else if (edit_email_register.text.isEmpty())
        {
            message = "o campo E-mail não pode ficar em branco."
        }
        else if (edit_password_register.text.isEmpty())
        {
            message = "O campo senha não pode ficar em branco."
        }
        else if (edit_password_confirm_register.text.isEmpty())
        {
            message = "O campo confirmar senha não pode ficar em branco."
        }
        else if (edit_password_confirm_register.text.toString() != edit_password_register.text.toString())
        {
            message = "A senha digita não confere."
        }
        else
        {
            message = "E-mail já cadastrado ou fora do padrão (@examplo.com)"
        }
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Atenção")
        builder.setMessage(message)
        builder.setPositiveButton("OK",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(){
        val homeIntent = Intent(this,HomeActivity::class.java)
        startActivity(homeIntent)
    }

}