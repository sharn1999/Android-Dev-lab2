package com.example.animalsapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.animalsapi.databinding.ItemAnimalBinding
import com.example.animalsapi.model.entity.Animal

class AnimalListAdapter : RecyclerView.Adapter<AnimalListAdapter.ViewHolder>() {

    private val items: ArrayList<Animal> = arrayListOf()

    fun updateItems(newItems: List<Animal>) {
        val diffCallback = AnimalDiffCallback(this.items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.items.clear()
        this.items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAnimalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(
        private val binding: ItemAnimalBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(animal: Animal) {

            val taxonomy = animal.taxonomy
            val characteristics = animal.characteristics

            var locationsString = ""


            animal.locations.forEach{
                locationsString += it + " "
            }

            with(binding) {
                animalName.text = animal.name
                animalKingdom.text = taxonomy.kingdom
                animalFamily.text = taxonomy.family
                animalLocations.text = locationsString
                animalDiet.text = characteristics.diet

            }
        }

    }

    class AnimalDiffCallback(
        private val oldList: List<Animal>,
        private val newList: List<Animal>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].name == newList[newItemPosition].name
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}

