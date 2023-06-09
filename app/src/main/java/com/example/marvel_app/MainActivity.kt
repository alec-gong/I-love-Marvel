package com.example.marvel_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var characterList: MutableList<CharacterModel>
    private lateinit var rvCharacters: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        characterList = mutableListOf()
        rvCharacters = findViewById(R.id.character_list)

        val marvelApiClient = MarvelApiClient()
        marvelApiClient.fetchCharacters(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("API error", e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d("API success", "success")

                // extract json information
                val responseBody = response.body?.string()

                if(response.isSuccessful && responseBody != null){
                    try{

                        // extract information
                        val jsonResponse = JSONObject(responseBody)
                        val data = jsonResponse.getJSONObject("data")
                        val results = data.getJSONArray("results")

                        // extract all the characters
                        for(i in 0 until results.length()){

                            val character = results.getJSONObject(i)
                            val name = character.getString("name")

                            // get description; uses filler if none
                            val description: String
                            if(character.getString("description") == ""){
                                description = "No description at the moment."
                            } else {
                                description = character.getString("description")
                            }

                            // filter out characters without images
                            val thumbnail = character.getJSONObject("thumbnail")
                            val thumbnailPath = thumbnail.getString("path")
                            if(!thumbnailPath.endsWith("image_not_available")){
                                val imageUrl = thumbnailPath + "." + thumbnail.getString("extension")
                                val characterModel = CharacterModel(imageUrl, name, description)
                                // add to character list
                                characterList.add(characterModel)
                            }
                        }

                        // initialize adapter
                        runOnUiThread{
                            val adapter = CharacterAdapter(characterList)
                            rvCharacters.adapter = adapter
                            rvCharacters.layoutManager = LinearLayoutManager(this@MainActivity)

                            // Set item click listener on adapter
                            adapter.setOnItemClickListener(object : CharacterAdapter.OnItemClickListener {
                                override fun onItemClick(character: CharacterModel) {
                                    val intent = Intent(this@MainActivity, CharacterDetailsActivity::class.java)
                                    intent.putExtra("characterImage", character.characterImage)
                                    intent.putExtra("characterName", character.characterName)
                                    intent.putExtra("characterDescription", character.characterDescription)
                                    startActivity(intent)
                                }
                            })
                        }

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