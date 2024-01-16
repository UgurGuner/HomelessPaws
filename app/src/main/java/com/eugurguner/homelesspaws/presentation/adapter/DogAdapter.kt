package com.eugurguner.homelesspaws.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eugurguner.homelesspaws.data.model.Dog
import com.eugurguner.homelesspaws.databinding.ItemDogBinding

class DogAdapter : RecyclerView.Adapter<DogAdapter.DogViewHolder>() {
    private var dogList: List<Dog> = emptyList()

    class DogViewHolder(private val binding: ItemDogBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dog: Dog) {
            Glide.with(binding.imgDog.context).load(dog.imageUrl).into(binding.imgDog)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val binding = ItemDogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.bind(dogList[position])
    }

    override fun getItemCount(): Int = dogList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setDogs(list: List<Dog>) {
        dogList = list
        notifyDataSetChanged()
    }
}