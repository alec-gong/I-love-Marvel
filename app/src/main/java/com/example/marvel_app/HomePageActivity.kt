package com.example.marvel_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class HomePageActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val buttonBrowse = findViewById<Button>(R.id.btn_browse)
        val buttonSearch = findViewById<Button>(R.id.btn_search)

        // change button color to red
        val color = ContextCompat.getColor(this, R.color.marvel_red)
        buttonBrowse.setBackgroundColor(color)
        buttonSearch.setBackgroundColor(color)

        // Set click listeners for each letter button

        buttonBrowse.setOnClickListener {
            val intent = Intent(this, LetterSelectionActivity::class.java)
            startActivity(intent)
        }

        buttonSearch.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

    }
}