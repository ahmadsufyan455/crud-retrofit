package com.zerodev.crudretrofit.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zerodev.crudretrofit.R
import com.zerodev.crudretrofit.databinding.ActivitySuccessSubmitBinding

class SuccessSubmitActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuccessSubmitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessSubmitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = getString(R.string.success)

        binding.btnReturn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        }
    }
}