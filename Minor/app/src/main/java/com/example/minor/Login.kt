package com.example.minor

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {
    var email: EditText? = null
    var password: EditText? = null
    var btnSubmit: Button? = null
    var createAcc: TextView? = null
    var dbHelper: DBHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val e = false
        val p = false
        setContentView(R.layout.activity_login)
        val abc=prdlistmst(this)
        abc.InsertProduct()
        val ad=catlistmst(this)
        ad.InsertCategory()
        val email = findViewById<EditText>(R.id.text_email)
        val password = findViewById<EditText>(R.id.text_pass)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit_login)
        dbHelper = DBHelper(this)
        btnSubmit.setOnClickListener(View.OnClickListener {
            val emailCheck = email.getText().toString().trim()
            val passCheck = password.getText().toString().trim()
            if (emailCheck.toString().isEmpty()){
                email.error="fill the email"
            }
            if(passCheck.toString().isEmpty()){
                password.error="fill the email"

            }else{
                val cursor = dbHelper!!.data
                if (cursor.count == 0) {
                    Toast.makeText(this@Login, "No entries Exists", Toast.LENGTH_LONG).show()
                }
                if (loginCheck(cursor, emailCheck, passCheck)) {
                    val intent = Intent(this@Login, home::class.java)
                    intent.putExtra("email", emailCheck)
                    email.setText("")
                    password.setText("")
                    startActivity(intent)
                } else {
                    val builder = AlertDialog.Builder(this@Login)
                    builder.setCancelable(true)
                    builder.setTitle("Wrong Credential")
                    builder.setMessage("Wrong Credential")
                    builder.show()
                }
                dbHelper!!.close()

            }

        })
        val createAcc = findViewById<TextView>(R.id.createAcc)
        createAcc.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Login, SignUp::class.java)
            startActivity(intent)
        })
    }

    companion object {
        fun loginCheck(cursor: Cursor, emailCheck: String, passCheck: String): Boolean {
            while (cursor.moveToNext()) {
                if (cursor.getString(0) == emailCheck) {
                    return if (cursor.getString(2) == passCheck) {
                        true
                    } else false
                }
            }
            return false
        }
    }
}