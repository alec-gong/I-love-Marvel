package com.example.marvel_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CharacterAdapter(private val characterList: List<CharacterModel>): RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val characterImage: ImageView
        val characterName: TextView

        init{
            characterImage = view.findViewById(R.id.character_image)
            characterName = view.findViewById(R.id.character_name)
        }
    }

    // Define a listener interface for image clicks
    interface OnItemClickListener {
        fun onItemClick(character: CharacterModel)
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characterList[position]

        Glide.with(holder.itemView)
            .load(character.characterImage)
            .centerCrop()
            .into(holder.characterImage)

        holder.characterName.text = character.characterName

        // Set click listener for image view
        holder.characterImage.setOnClickListener {
            onItemClickListener?.onItemClick(character)
        }
    }

}