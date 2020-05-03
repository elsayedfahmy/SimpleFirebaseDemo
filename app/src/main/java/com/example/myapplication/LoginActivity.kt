package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    lateinit var  mAuth_out : FirebaseAuth
    lateinit var btn_LoginOut : Button
    lateinit    var txt_dataview:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth_out = FirebaseAuth.getInstance();
        txt_dataview= findViewById(R.id.txtviewemail)
        btn_LoginOut= findViewById(R.id.btn_Logout)

       // var myintent: Intent
       // txt_dataview.text=myintent.getStringExtra("email_id")

        txt_dataview.text=  mAuth_out.currentUser?.email.toString()
        btn_LoginOut.setOnClickListener {
                view ->


                Toast.makeText(
                    applicationContext,
                    "LogOuting ...  ",
                    Toast.LENGTH_LONG
                ).show()

                signout()
        }
        mAuth_out.addAuthStateListener{
            if(mAuth_out.currentUser==null){this.finish()}
        }



    }
    fun signout(){
        mAuth_out.signOut()
    }
}
