package com.example.marvel_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
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

                // extract json information
                val responseBody = response.body?.string()
                if(response.isSuccessful && responseBody != null){
                    try{
                        // extract information
                        val jsonResponse = JSONObject(responseBody)
                        Log.d("API success", jsonResponse.toString())
                    } catch(e: JSONException) {
                        Log.e("API error", "Error parsing JSON: ${e.message}")
                    }
                } else {
                    Log.d("API error", "Response unsuccessful")
                }
            }
        })
    }
}