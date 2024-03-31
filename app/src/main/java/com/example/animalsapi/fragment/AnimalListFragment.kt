package com.example.animalsapi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animalsapi.adapter.AnimalListAdapter
import com.example.animalsapi.databinding.FragmentAnimalListBinding
import com.example.animalsapi.model.entity.Animal
import com.example.animalsapi.model.network.createApiService

import retrofit2.Call
import retrofit2.Callback

class AnimalListFragment : Fragment(){
    companion object {
        fun newInstance() = AnimalListFragment()
    }

    private var _binding: FragmentAnimalListBinding? = null
    private val binding
        get() = _binding!!

    private val adapter = AnimalListAdapter();

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAnimalListBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        setupUI()

        val service = createApiService()
        binding.searchView.setIconified(false)
        binding.searchView.queryHint = "Введите запрос для поиска"
        binding.searchView.requestFocus()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                service.getAnimals(query).enqueue(object : Callback<List<Animal>> {
                    override fun onResponse(call: retrofit2.Call<List<Animal>>, response: retrofit2.Response<List<Animal>>) {
                        if (response.isSuccessful) {
                            val offerList = response.body() ?: listOf()
                            if (offerList != null) {
                                adapter.updateItems(offerList)
                            }
                        }

                    }
                    override fun onFailure(call: Call<List<Animal>>, t: Throwable) {
                    }
                })
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
    }





    private fun setupUI() {
        with(binding) {
            animalList.layoutManager = LinearLayoutManager(context)
            animalList.adapter = adapter
        }
    }
}