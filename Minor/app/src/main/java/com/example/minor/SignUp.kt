package com.example.minor

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUp : AppCompatActivity() {
    var name: EditText? = null
    var number: EditText? = null
    var email: EditText? = null
    var pass: EditText? = null
    var login: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val name = findViewById<EditText>(R.id.textName)
        val number = findViewById<EditText>(R.id.textNumber)
        val email = findViewById<EditText>(R.id.textEmail)
        val pass = findViewById<EditText>(R.id.textPass)
        val add = findViewById<EditText>(R.id.textAdd)
        val signUpAcc = findViewById<Button>(R.id.btnSignUpAcc)
        var dbHelper = DBHelper(this)

        signUpAcc.setOnClickListener {
            val name1 = name.getText().toString().trim()
            val number1 = number.getText().toString().trim()
            val email1 = email.getText().toString().trim()
            val pass1 = pass.getText().toString().trim()
            val add1 = add.getText().toString().trim()
            if (name1.toString().isEmpty()){
                name.error="fill the name"
            }
            if(pass1.toString().isEmpty()){
                pass.error="fill the pass"

            }
            if(email1.toString().isEmpty()){
                email.error="fill the email"

            }
            if(number1.toString().isEmpty()){
                number.error="fill the pass"

            }
            if(add1.toString().isEmpty()){
                add.error="fill the Address"

            }else{
                val b = dbHelper!!.insetUserData(name1, number1, email1, pass1)
                if (b) {
                    Toast.makeText(this, "Data inserted", Toast.LENGTH_SHORT).show()
                    val i = Intent(this, Login::class.java)
                    startActivity(i)
                } else {
                    Toast.makeText(this, "Failed To insert Data", Toast.LENGTH_SHORT).show()
                }
            }
            val login = findViewById<TextView>(R.id.loginAcc)
            login.setOnClickListener(View.OnClickListener {
                val i = Intent(this, Login::class.java)
                startActivity(i)
            })
        }
    }

}
