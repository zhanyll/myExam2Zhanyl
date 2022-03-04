package com.example.myexam2zhanyl

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CharactersAdapter(private val click: (character: Character) -> Unit): RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {
    private var list: List<Character> = mutableListOf()

    fun setData(list: List<Character>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler, parent, false)
        return ViewHolder(itemView, click)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View,
                     private val click: (character: Character) -> Unit): RecyclerView.ViewHolder(itemView) {
        fun bind(item: Character) {
            val name = itemView.findViewById<AppCompatTextView>(R.id.item_txt)
            val status = itemView.findViewById<AppCompatTextView>(R.id.status)
            val species = itemView.findViewById<AppCompatTextView>(R.id.species)
            val image = itemView.findViewById<AppCompatImageView>(R.id.image)
            name.text = item.name
            status.text = item.status
            species.text = item.species

            Glide.with(itemView.context)
                .load(item.image)
                .into(image)

            itemView.setOnClickListener {
                click.invoke(item)
            }
        }
    }
}