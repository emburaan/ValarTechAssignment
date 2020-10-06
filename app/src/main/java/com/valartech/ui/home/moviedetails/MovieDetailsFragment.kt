package com.valartech.ui.home.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.valartech.R
import com.valartech.base.BaseFragment
import com.valartech.databinding.FragmentFourBinding
import com.valartech.global.helper.Navigation
import com.valartech.global.helper.ViewModelFactory
import javax.inject.Inject


class MovieDetailsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MovieDetailsViewModel
    lateinit var bind:FragmentFourBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_four, container, false)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieDetailsViewModel::class.java)

        // Register the UI for XMLBinding
        bind = FragmentFourBinding.bind(view)
        bind.viewModel = viewModel
        bind.lifecycleOwner = viewLifecycleOwner

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieDetails(arguments?.get("id").toString(),resources.getString(R.string.api_key))
    }

    /**
     * Register the fragment for base observer
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        registerBaseObserver(viewModel)
        registerFourObservers()
    }


    /**
     * register UI Four Observers
     */
    private fun registerFourObservers() {
viewModel.moviedetailsdata.observe(viewLifecycleOwner, Observer {


    if (it != null){
        bind.imageurl=it.Poster
        bind.picasso=getPicasso()
        bind.placeHolder=resources.getDrawable(R.drawable.ic_popcorn)


    }
})    }


    /**
     * handling navigation event
     */
    override fun navigate(navigation: Navigation) {
        when (navigation.navigateTo) {

        }
    }
}





