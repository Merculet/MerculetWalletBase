package io.merculet.core.ui

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import io.reactivex.disposables.CompositeDisposable

/**
 * @author Aaron
 * @email aaron@merculet.io
 * @date 18/03/2018 22:13
 * @description
 */
abstract class BaseFragment : Fragment() {

    protected lateinit var mContext: Context
    protected var compositeDisposable = CompositeDisposable()

    /**
     * Deprecated on API 23
     * @param activity
     */
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        if (Build.VERSION.SDK_INT < 23) {
            this.mContext = activity
        }
    }

    @TargetApi(23)
    override fun onAttach(context: Context) {
        super.onAttach(context)
//        if (context is Activity) {
//            this.mContext = context
//        }
        this.mContext = context

    }

    override fun
            onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    protected abstract fun initView(view: View)

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear() // 防止内存泄露
    }
}