package com.example.marvel_app

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity


class SearchActivity: AppCompatActivity() {
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchView = findViewById(R.id.searchView)

        // Set up listener for search button or text change
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    val intent = Intent(this@SearchActivity, MainActivity::class.java)
                    intent.putExtra("query", query)
                    startActivity(intent)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                newText?.let {
//                    val intent = Intent(this@SearchActivity, MainActivity::class.java)
//                    intent.putExtra("query", newText)
//                    startActivity(intent)
//                }
                return true
            }
        })
    }

}