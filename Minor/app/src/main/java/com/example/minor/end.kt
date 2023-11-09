package com.example.minor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class end : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end)
        var btn=findViewById<Button>(R.id.button2)
        btn.setOnClickListener {
            val intent = Intent(this, home::class.java)
            startActivity(intent)
        }


    }

}