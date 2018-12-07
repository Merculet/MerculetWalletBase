package io.merculet.core.ui

import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import io.reactivex.disposables.CompositeDisposable

open class BaseActivity : AppCompatActivity() {

    /*override fun onCreate(savedInstanceState: Bundle?) {
        //		AndroidInjection.inject(this);
        super.onCreate(savedInstanceState)
    }*/
    protected var compositeDisposable = CompositeDisposable()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    fun addFragment(fragment: BaseFragment, layoutResId: Int, tag: String) {
        supportFragmentManager.beginTransaction()
                .add(layoutResId, fragment, tag)
                .commit()
    }

    fun addFragmentWithBackStack(fragment: BaseFragment, layoutResId: Int, tag: String) {
        supportFragmentManager.beginTransaction()
                .add(layoutResId, fragment, tag)
                .addToBackStack(tag)
                .commit()
    }


    override fun onDestroy() {
        compositeDisposable.clear() // 防止内存泄露
        super.onDestroy()
    }
}
