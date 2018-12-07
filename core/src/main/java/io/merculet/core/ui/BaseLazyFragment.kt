package io.merculet.core.ui

import android.os.Bundle
import android.view.View
import io.merculet.core.ui.BaseFragment

/**
 * @author Aaron
 * @email aaron@merculet.io
 * @date 18/03/2018 22:13
 * @description
 */
abstract class BaseLazyFragment : BaseFragment() {


    private var isPrepared: Boolean = false
    private var isVisibleLocal: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isPrepared = true
        dispatchUserVisibleHint()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        //懒加载 注意没用isVisibleToUser
        if (userVisibleHint) {
            isVisibleLocal = true
            dispatchUserVisibleHint()
        } else {
            isVisibleLocal = false
        }
    }

    private fun dispatchUserVisibleHint() {
        if (!isVisibleLocal || !isPrepared) {
            return
        }

        onLazyLoad()

        isPrepared = false
    }

    protected abstract fun onLazyLoad()

}