package com.example.contactappa.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contactappa.databinding.ActivitySplashBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.root.postDelayed({
            startActivity(Intent(this, ContactActivity::class.java))
            finish()
        }, 2000)
    }
}