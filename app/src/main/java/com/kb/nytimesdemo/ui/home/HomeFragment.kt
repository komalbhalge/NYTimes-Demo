package com.kb.nytimesdemo.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.kb.moviedb.ui.main.adapter.ArticleListAdapter
import com.kb.nytimesdemo.data.model.Category
import com.kb.nytimesdemo.databinding.FragmentArticlesBinding
import com.kb.nytimesdemo.ui.adapter.CategoryAdapter
import com.kb.nytimesdemo.utils.*

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentArticlesBinding? = null

    private val listAdapter = ArticleListAdapter(
        onItemClicked = { position -> onListItemClick(position) }
    )

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var categoryAdapter: CategoryAdapter

    private val category: MutableList<Category> by lazy {
        mutableListOf(
            Category("Business", NY_BUSINESS),
            Category("Education", NY_EDUCATION),
            Category("Science", NY_SCIENCE),
            Category("Space", NY_SPACE),
            Category("Sports", NY_SPORTS),
            Category("Tech", NY_TECH),
            Category("Your money", NY_YOURMONEY)
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticlesBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    private fun setup() {
        initCategoryRv()
        initList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        observeData()
    }

    private fun initList() {
        binding.articleRv.apply {
            setHasFixedSize(true)
            adapter = listAdapter
            val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
        }
    }

    private fun initCategoryRv() = with(binding) {
        // init category rv
        categoryAdapter = CategoryAdapter(category).also {
            categoryRv.rootView.post {
                binding.categoryRv.adapter = it
                binding.categoryRv.addItemDecoration(SpacesItemDecorator(16))
            }
        }
    }

    private fun observeData() {
        with(homeViewModel) {
            retrieveMostViewedList(1)
            with(viewLifecycleOwner) {
                articlesList.observe(this) {
                    Log.e("TAG:", "Size:" + it.results.size)
                    listAdapter.setArticles(it.results)
                }
            }
        }
    }

    private fun onListItemClick(position: Int) {
        //Handle List item click
        val bundle = bundleOf("ARTICLE_ID" to 1)
        //navContoller.navigate(R.id.articleDetailFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}