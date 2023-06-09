package com.example.marvel_app

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class CharacterDetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        // Retrieve the selected CharacterModel from the intent
        val image = intent.getStringExtra("characterImage")
        val name = intent.getStringExtra("characterName")
        val description = intent.getStringExtra("characterDescription")

        // Display the character details
        val characterImage = findViewById<ImageView>(R.id.image)
        val characterName = findViewById<TextView>(R.id.name)
        val characterDescription = findViewById<TextView>(R.id.description)

        // Load the character image using Glide
        Glide.with(this)
            .load(image)
            .centerCrop()
            .into(characterImage)

        characterName.text = name
        characterDescription.text = description
    }

}