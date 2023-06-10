package com.example.marvel_app

import okhttp3.*
import java.security.MessageDigest
import java.sql.Timestamp
import java.util.*

class MarvelApiClient {
    // api keys
    private val publicKey = "ec29073fe4d61d620e8503f63861130f"
    private val privateKey = "fb0e4a00da149fc36e4fa02398d392839c426683"

    fun fetchCharacters(letter: String, callback: Callback){
        val url = buildUrl(letter,"/v1/public/characters")

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(callback)
    }

    // helper function: build API request URL
    private fun buildUrl(letter: String, path: String): HttpUrl{
        val timestamp = System.currentTimeMillis().toString()
        val hash = generateHash(timestamp)

        return HttpUrl.Builder()
            .scheme("https")
            .host("gateway.marvel.com")
            .addPathSegments(path.trim('/'))
            .addQueryParameter("ts", timestamp)
            .addQueryParameter("apikey", publicKey)
            .addQueryParameter("hash", hash)
            .addQueryParameter("limit", "100")
            .addQueryParameter("nameStartsWith", letter)
            .build()
    }

    // helper function for buildUrl
    private fun generateHash(timestamp: String): String {
        val input = timestamp + privateKey + publicKey
        val md5 = MessageDigest.getInstance("MD5")
        val digest = md5.digest(input.toByteArray())
        return digest.joinToString("") { "%02x".format(it) }
    }
}