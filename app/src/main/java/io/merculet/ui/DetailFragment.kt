package io.merculet.ui

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.navigation.Navigation
import io.merculet.NewsConstants
import io.merculet.R
import io.merculet.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)

        arguments?.getString(NewsConstants.BUNDLE_DETAIL_URL)?.let {
            viewModel.url = it
        }

        webview.settings.javaScriptEnabled = true
        webview.settings.setSupportZoom(true)
        webview.settings.builtInZoomControls = false
        webview.settings.useWideViewPort = true
        webview.settings.domStorageEnabled = true
        webview.settings.loadWithOverviewMode = true
        webview.isVerticalScrollBarEnabled = false
        webview.isHorizontalScrollBarEnabled = false
        webview.webChromeClient = WebChromeClient()
        webview.webViewClient = WebViewClient()

        webview?.loadUrl(viewModel.url)

        iv_back.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }

    }


    override fun onResume() {
        super.onResume()
        webview?.onResume()
    }

    override fun onStop() {
        webview?.stopLoading()
        super.onStop()
    }

    override fun onDestroy() {
        webview?.destroy()
        super.onDestroy()
    }

}
