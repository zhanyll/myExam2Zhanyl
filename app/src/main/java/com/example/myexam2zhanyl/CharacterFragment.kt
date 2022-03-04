package com.example.myexam2zhanyl

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myexam2zhanyl.databinding.CharacterFragmentBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterFragment: Fragment(R.layout.character_fragment) {
    private val api get() = Injector.rickMortyApi
    private var _binding: CharacterFragmentBinding ?= null
    private val binding get() = _binding!!
    private lateinit var listener: Clicked

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as Clicked
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = CharacterFragmentBinding.bind(view)
        val id = arguments?.getLong("id") ?: 1L

        binding.apply {
            api.getById(id)
                .subscribeOn(Schedulers.io())
                .map { it }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess {
                    charName.text = "Name: ${it.name}"
                    charStatus.text = "Status: ${it.status}"
                    charSpecies.text = "Species: ${it.species}"
                    charType.text = "Type: ${it.type}"
                    charGender.text = "Gender: ${it.gender}"
                    charUrl.text = "Url: ${it.url}"
                    charCreated.text = "Created: ${it.created}"

                    val img = charImage
                    Glide.with(requireActivity())
                        .load(it.image)
                        .into(img)
                }
                .subscribe()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}