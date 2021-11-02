package com.osorio.dogswelove.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.osorio.dogswelove.R
import com.osorio.dogswelove.databinding.ActivityMainBinding
import com.osorio.dogswelove.framework.DogsViewModel
import com.osorio.dogswelove.framework.DogsViewModelFactory
import com.osorio.dogswelove.presentation.adapters.DogsAdapter

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    // view model declarations
    private lateinit var binding: ActivityMainBinding
    lateinit var dogsViewModel: DogsViewModel

    private val adapter = DogsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init view model
        dogsViewModel = ViewModelProvider(this, DogsViewModelFactory()).get(DogsViewModel::class.java)

        binding.recyclerview.adapter = adapter

        dogsViewModel.isBusy.observe(this, Observer {
            binding.loadingBar.isVisible = it
            binding.recyclerview.isVisible = !it
        })

        dogsViewModel.onSuccess.observe(this, {
            if (!it)
                Toast.makeText(applicationContext, dogsViewModel.onErrorMessage.value, Toast.LENGTH_SHORT).show();
        })

        dogsViewModel.dogs.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setDogList(it)
        })

        dogsViewModel.initialize()
    }
}