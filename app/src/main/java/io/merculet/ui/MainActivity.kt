package io.merculet.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import androidx.navigation.Navigation
import io.merculet.core.ui.BaseActivity
import io.merculet.R
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : BaseActivity(), HasSupportFragmentInjector {
    private var newsFragment: NewsFragment? = null
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState ?: Bundle())
        setContentView(R.layout.activity_main)
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this,R.id.nav_host_fragment).navigateUp()
    }
}
