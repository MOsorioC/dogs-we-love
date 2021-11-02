package com.osorio.dogswelove.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.osorio.core.data.Dog
import com.osorio.dogswelove.R
import com.osorio.dogswelove.databinding.DogItemBinding

class DogsAdapter(val context: Context): RecyclerView.Adapter<MainViewHolder>() {
    var dogs = mutableListOf<Dog>()

    fun setDogList(dogs: List<Dog>) {
        this.dogs = dogs.toMutableList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = DogItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val dog = dogs[position]
        holder.binding.tvName.text = dog.dogName
        holder.binding.tvDescription.text = dog.description
        holder.binding.tvAge.text = context.resources.getString(R.string.age, dog.age)

        // Load image
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(16))
        Glide.with(holder.itemView.context).load(dog.image).apply(requestOptions).into(holder.binding.imgItem)
    }

    override fun getItemCount(): Int {
        return dogs.size
    }
}

class MainViewHolder(val binding: DogItemBinding) : RecyclerView.ViewHolder(binding.root) {

}