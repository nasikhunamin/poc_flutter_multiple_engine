package com.nasikhunamin.imhost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.nasikhunamin.imhost.im.ImModule
import com.nasikhunamin.imhost.opty.OptyModule

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imBtn = findViewById<Button>(R.id.btn_open_im)
        imBtn.setOnClickListener {
            val imIntent = ImModule.getInstance().buildWithCachedEngine(this)
            startActivity(imIntent)
        }

        val optyBtn = findViewById<Button>(R.id.btn_open_opty)
        optyBtn.setOnClickListener {
            val imIntent = OptyModule.getInstance().buildWithCachedEngine(this)
            startActivity(imIntent)
        }
    }
}