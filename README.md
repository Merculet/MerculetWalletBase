# MerculetWalletBase

Merculet Simplet App 的框架，Simplet 基于此进行开发。

开源出此框架的目的：为了展示如何使用 AAC + Dagger2 + Retrofit + Kotlin 开发安全高效的 App。


## Features

* App 完全基于 Kotlin 开发
* 组件化架构
* 采用 Android Architecture Components 架构
* 采用 RxJava 2.x 框架
* 采用依赖注入框架 Dagger 2
* 网络框架采用 Retrofit

## Example

```kotlin
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
```


## TODO List

* 开源出常用的各个组件


## Licence
Apache License 2.0

