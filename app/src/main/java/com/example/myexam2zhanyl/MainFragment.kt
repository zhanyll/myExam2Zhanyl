package com.example.myexam2zhanyl

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myexam2zhanyl.database.Characters
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainFragment: Fragment(R.layout.main_fragment) {
    private val api get() = Injector.rickMortyApi
    private val dbInstance get() = Injector.database
    private lateinit var listener: Clicked
    private lateinit var adapter: CharactersAdapter
    private lateinit var runnable: Runnable
    private lateinit var progress: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as Clicked
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress = view.findViewById(R.id.progress_Bar) as ProgressBar
        progress.visibility = View.VISIBLE

        adapter = CharactersAdapter{ listener.onClick(it.id) }
        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
        getAll()

        swipeRefresh = view.findViewById(R.id.swipeRefreshLayout)
        swipeRefresh.setOnRefreshListener { getAll() }
        swipeRefresh.setColorSchemeResources(
            android.R.color.holo_blue_bright
        )
    }

    private fun getAll() {
        api.getAllCharacters()
            .subscribeOn(Schedulers.io())
            .map {
                val listEp = mutableListOf<Characters>()
                it.results.forEach {
                    val character = Characters(
                        id = it.id,
                        name = it.name,
                        status = it.status,
                        species = it.species,
                        type = it.type,
                        gender = it.gender,
                        image = it.image,
                        url = it.url,
                        created = it.created
                    )
                    listEp.add(character)
                }
                listEp.toList()
                dbInstance.characterDao().insertList(listEp)
                it.results
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { adapter.setData(it) }
            .doFinally { progress.isVisible = false }
            .doFinally { swipeRefresh.isRefreshing = false }
            .subscribe()
    }
}