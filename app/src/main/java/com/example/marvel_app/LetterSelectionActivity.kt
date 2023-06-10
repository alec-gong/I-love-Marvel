package com.example.marvel_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class LetterSelectionActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_letter_selection)

        val letterButtons = listOf<Button>(
            findViewById(R.id.btn_A),
            findViewById(R.id.btn_B),
            findViewById(R.id.btn_C),
            findViewById(R.id.btn_D),
            findViewById(R.id.btn_E),
            findViewById(R.id.btn_F),
            findViewById(R.id.btn_G),
            findViewById(R.id.btn_H),
            findViewById(R.id.btn_I),
            findViewById(R.id.btn_J),
            findViewById(R.id.btn_K),
            findViewById(R.id.btn_L),
            findViewById(R.id.btn_M),
            findViewById(R.id.btn_N),
            findViewById(R.id.btn_O),
            findViewById(R.id.btn_P),
            findViewById(R.id.btn_Q),
            findViewById(R.id.btn_R),
            findViewById(R.id.btn_S),
            findViewById(R.id.btn_T),
            findViewById(R.id.btn_U),
            findViewById(R.id.btn_V),
            findViewById(R.id.btn_W),
            findViewById(R.id.btn_X),
            findViewById(R.id.btn_Y),
            findViewById(R.id.btn_Z)
        )
        val color = ContextCompat.getColor(this, R.color.marvel_red)
        for(button in letterButtons){
            button.setBackgroundColor(color)
        }

        // Set click listeners for each letter button
        for (button in letterButtons) {
            button.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("letter", button.text.toString())
                startActivity(intent)
            }
        }
    }

}