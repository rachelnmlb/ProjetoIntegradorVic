package com.rachel.projetointegrador.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rachel.projetointegrador.databinding.ActivitySystemFaliedBinding

class SystemFailedActivity : AppCompatActivity()  {

    private val binding by lazy {
        ActivitySystemFaliedBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.txtTryAgain.setOnClickListener {
            onBackPressed()
        }
    }
}