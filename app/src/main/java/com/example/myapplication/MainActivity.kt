package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    lateinit var email:EditText
    lateinit var pass_word:EditText
    lateinit var btnRegister:Button
    lateinit var btnLogin :Button

    lateinit var  mAuth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance();

        email=findViewById(R.id.edt_email)
        pass_word= findViewById(R.id.edt_password)
        btnLogin= findViewById(R.id.btn_login)
        btnRegister= findViewById(R.id.btn_register)

        btnRegister.setOnClickListener{
            var email_data= email.text.toString().trim()
            var password_data= pass_word.text.toString().trim()
            if(email_data.isEmpty()||password_data.isEmpty())
            {
                Toast.makeText(
                    applicationContext,
                    "please enter your data Right  ",
                    Toast.LENGTH_LONG
                ).show()
            }

            else {
                signup(email_data, password_data)
            }
        }
        btnLogin.setOnClickListener {

                view ->
            var email_data = email.text.toString().trim()
            var password_data = pass_word.text.toString().trim()
            if (email_data.isEmpty() || password_data.isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "please enter your data Right  ",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                signin(view,email_data , password_data)

            }
        }


    }


    fun signup( email_data:String, pass_word:String)
    {

        mAuth.createUserWithEmailAndPassword(email_data, pass_word)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(
                        applicationContext,
                        "email is $email_data and password is $pass_word  ",
                        Toast.LENGTH_LONG
                    ).show()
                    //val user = mAuth.currentUser
                  //  updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(
                        applicationContext,
                        "Authentication failed ",
                        Toast.LENGTH_LONG
                    ).show()
                    //updateUI(null)
                }


            }

    }

    fun signin(view: View, user_email:String, user_password:String)
    {

        Toast.makeText(applicationContext, "enterd .... ..  ", Toast.LENGTH_SHORT).show()
        mAuth.signInWithEmailAndPassword(user_email, user_password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    // Sign in success, update UI with the signed-in user's information
                   Toast.makeText(applicationContext, "Loging ..  ", Toast.LENGTH_SHORT).show()
                   var intent=Intent(this,LoginActivity::class.java)
                    intent.putExtra("email_id",user?.email)
                    startActivity(intent)
                }
                else {

                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        applicationContext , "loging failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                        // updateUI(null)
                }

            }


    }


}
