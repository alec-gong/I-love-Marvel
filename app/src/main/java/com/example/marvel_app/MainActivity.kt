package com.example.marvel_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val marvelApiClient = MarvelApiClient()
        marvelApiClient.fetchCharacters(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("API error", e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d("API success", response.toString())
            }
        })
    }
}