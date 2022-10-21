package com.compose.movies.presentation.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.compose.movies.R
import com.compose.movies.databinding.FragmentHomeBinding
import com.compose.movies.domain.ApiStatus
import com.compose.movies.framework.AppConstants.DATA_PARAM
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ShowGridAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setObservers()
        setEvents()
        return binding.root
    }

    private fun setEvents() {
        binding.moviesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.getListShows()
                }
            }
        })
        binding.svSearch.setOnQueryTextListener(this)

        adapter = ShowGridAdapter(ShowGridAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })
        binding.moviesRecyclerView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setObservers() {

        viewModel.listData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        }

        viewModel.status.observe(viewLifecycleOwner) { status ->
            when (status) {
                ApiStatus.LOADING -> {
                    binding.statusImage.visibility = View.VISIBLE
                    binding.statusImage.setImageResource(R.drawable.loading_animation)
                }
                ApiStatus.ERROR -> {
                    binding.statusImage.visibility = View.VISIBLE
                    binding.statusImage.setImageResource(R.drawable.ic_connection_error)
                }
                ApiStatus.DONE -> {
                    binding.statusImage.visibility = View.GONE
                }
                null -> Unit
            }
        }

        viewModel.navigateToDetail.observe(viewLifecycleOwner) {
            it?.let {

                findNavController().navigate(R.id.action_showDetail, Bundle().apply {
                    putParcelable(DATA_PARAM, it)
                })
                viewModel.displayPropertyDetailsComplete()
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let {
            viewModel.search(it)
        }
        return false
    }
}