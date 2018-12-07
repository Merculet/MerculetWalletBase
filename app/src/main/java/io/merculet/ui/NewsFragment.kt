package io.merculet.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import io.merculet.NewsConstants
import io.merculet.R
import io.merculet.adapter.NewsSourceAdapter
import io.merculet.core.ui.BaseFragment
import io.merculet.db.SourceEntity
import io.merculet.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news.*

/**
 * Created by Aaron on 01/11/17.
 */
class NewsFragment : BaseFragment(), (View, SourceEntity) -> Unit {


    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsSourceAdapter: NewsSourceAdapter
    private val sourceList = ArrayList<SourceEntity>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_news, container, false)
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        return view
    }

    override fun initView(view: View) {

        newsSourceAdapter = NewsSourceAdapter(this, sourceList)
        recyclerView.adapter = newsSourceAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        newsViewModel.getNewsSource(null, null, null)
                .observe(this, Observer { newsSource ->
                    if (newsSource?.data != null && newsSource.data!!.isNotEmpty()) {
                        multiStatusView.showContent()

                        newsSourceAdapter.updateDataSet(newsSource.data!!)
                    } else {
                        multiStatusView.showEmpty()
                    }

                })
    }

    //点击事件
    override fun invoke(view: View, source: SourceEntity) {
        val bundle = Bundle()
        bundle.putString(NewsConstants.BUNDLE_DETAIL_URL, source.url)

        Navigation.findNavController(view).navigate(R.id.action_page2, bundle)

    }

}