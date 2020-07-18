package com.example.automaocasa
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*



class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
       // Thread.sleep(2000)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        lbl_Register.setOnClickListener {
            openNextActivity()
        }
        authentication()
    }

    private fun openNextActivity(){
        val intent = Intent (this, RegisterActivity::class.java)
        startActivity(intent)
    }
    private fun authentication (){
        btn_acess.setOnClickListener {
            if (edit_email_login.text.isNotEmpty() && edit_password_login.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(edit_email_login.text.toString(),
                        edit_password_login.text.toString()).addOnCompleteListener {
                        if(it.isSuccessful)
                        {
                            showHome()

                        }else{
                            showAlert()

                        }
                    }
            }
        }
    }
    private fun showAlert (){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Atenção")
        builder.setMessage("E-mail ou senha invalido.")
        builder.setPositiveButton("OK",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun showHome(){
        val homeIntent = Intent(this,HomeActivity::class.java)
        startActivity(homeIntent)
    }




}