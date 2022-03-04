package com.example.myexam2zhanyl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myexam2zhanyl.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Clicked {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, MainFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onMain() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MainFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onClick(id: Long) {
        val characterFragment = CharacterFragment()
        val bundle = Bundle()
        bundle.putLong("id", id)
        characterFragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, characterFragment)
            .addToBackStack(null)
            .commit()
    }
}