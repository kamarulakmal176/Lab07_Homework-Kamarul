package com.metech.lab07homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.metech.lab07homework.databinding.ActivityMainBinding
import com.metech.lab07homework.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}