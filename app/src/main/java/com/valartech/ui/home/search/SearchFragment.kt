package com.valartech.ui.home.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProviders
import com.valartech.R
import com.valartech.base.BaseFragment
import com.valartech.databinding.FragmentOneBinding
import com.valartech.global.helper.Navigation
import com.valartech.global.helper.ViewModelFactory
import com.valartech.ui.home.search.adapter.MovieListAdapter
import com.valartech.ui.home.moviedetails.MovieDetailsFragment
import kotlinx.android.synthetic.main.fragment_one.*
import javax.inject.Inject


class SearchFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: SearchViewModel

    @Inject
    lateinit var movieListAdapter: MovieListAdapter
    override fun navigate(navigationTo: Navigation) {
        when(navigationTo.navigateTo.simpleName){
            MovieDetailsFragment::class.simpleName->{

                val actionsearchtodetails=SearchFragmentDirections.actionTask1ToTask4(navigationTo.extra[0]as String,navigationTo.extra[0]as String)
                findNavController()?.navigate(actionsearchtodetails)

            }

        }
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_one, container, false)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)

        // Register the UI for XMLBinding
        val bind = FragmentOneBinding.bind(view)
        bind.viewModel = viewModel
        bind.lifecycleOwner = viewLifecycleOwner


        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        registerBaseObserver(viewModel)
        registerOneObservers()
        setupListner()
    }

    private fun setupListner() {
        editText.setOnEditorActionListener { textView, i, keyEvent ->
          if (i==EditorInfo.IME_ACTION_SEARCH){

              viewModel.onRefresh(textView.text)
              return@setOnEditorActionListener true
          }
            return@setOnEditorActionListener false
        }
    }


    /**
     * register UI One Observers
     */
    private fun registerOneObservers() {
        registerRecycler()
        //TODO
    }

    private fun registerRecycler() {
      movieListAdapter.viewModel =viewModel
        movieListAdapter.notifyDataSetChanged()
        rv_movielist.adapter=movieListAdapter
        rv_movielist.recycledViewPool.clear()
    }


    /**
     * handling navigation event
     */
}





