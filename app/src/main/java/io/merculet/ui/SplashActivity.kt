package io.merculet.ui

import android.content.Intent
import android.os.Bundle
import io.merculet.R
import io.merculet.core.ui.BaseActivity

/**
 * Created by Aaron on 30/10/17.
 */

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState ?: Bundle())
        setContentView(R.layout.activity_splash)

        navigateToHome()
//        Observable.interval(3, TimeUnit.SECONDS)
//                .take(1)
//                .observeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe { navigateToHome() }.addTo(compositeDisposable)
    }

    private fun navigateToHome() {
        val homeIntent = Intent(this, MainActivity::class.java)
        startActivity(homeIntent)
        finish()
    }
}
