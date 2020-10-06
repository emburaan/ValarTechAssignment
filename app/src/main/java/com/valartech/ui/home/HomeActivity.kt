package com.valartech.ui.home

import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.valartech.R
import com.valartech.base.BaseActivity
import com.valartech.data.model.user.User
import com.valartech.databinding.ActivityHomeBinding
import com.valartech.global.helper.ViewModelFactory
import com.valartech.global.utils.ExtraKeys
import com.valartech.ui.home.search.SearchFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject
import kotlin.reflect.KClass


class HomeActivity : BaseActivity(), HasAndroidInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: HomeViewModel

    private lateinit var navController: NavController

    private var currentFragment: KClass<out Any>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)


        navController = Navigation.findNavController(this, R.id.navHostFragment)
        navController.setGraph(R.navigation.nav_graph, intent.extras)


        registerBindingAndBaseObservers(binding)
        registerHomeObservers()
    }


    /**
     * register UI Home activity Observers
     */
    private fun registerHomeObservers() {
        //TODO
    }


    private fun onNavigationItemSelected(menu: MenuItem): Boolean {
        when (menu.itemId) {
            R.id.task1 -> viewModel.onActionOneClicked()
            R.id.task2 -> viewModel.onActionTwoClicked()
        }
        return true
    }


    /**
     * handling bottom navigation listener
     */
    override fun navigate(navigation: com.valartech.global.helper.Navigation) {
        when (navigation.navigateTo) {
            SearchFragment::class -> {
                if (currentFragment?.equals(SearchFragment::class) == true) return
                currentFragment = SearchFragment::class
                val navigation1 = NavOptions.Builder().setPopUpTo(R.id.task1, true).setLaunchSingleTop(true).build()
                val b = Bundle()
                b.putParcelable(ExtraKeys.HomeActivity.HOME_EXTRA_USER_KEY, navigation.extra[0] as User)
                navController.navigate(R.id.task1, b, navigation1)
            }




        }
    }

    /**
     * Register the UI for XMLBinding
     * Register the activity for base observers
     */
    private fun registerBindingAndBaseObservers(binding: ActivityHomeBinding) {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        registerBaseObservers(viewModel)
    }


    /**
     * Required for SupportNavigateUp
     */
    override fun onSupportNavigateUp() = Navigation
        .findNavController(this, R.id.navHostFragment).navigateUp()


    /**
     * Required for fragment jnjection
     */


    override fun androidInjector(): AndroidInjector<Any> {
        return fragmentDispatchingAndroidInjector
    }
}
