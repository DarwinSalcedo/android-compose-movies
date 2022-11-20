package com.compose.movies.presentation.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.compose.movies.R
import com.compose.movies.databinding.FragmentDetailBinding
import com.compose.movies.domain.ApiStatus
import com.compose.movies.domain.model.Show
import com.compose.movies.framework.AppConstants.DATA_PARAM
import com.compose.movies.framework.loadUrl
import com.compose.movies.presentation.home.ShowGridAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var adapter: ShowGridAdapter
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val data = arguments?.getParcelable<Show>(DATA_PARAM)
            ?: throw IllegalArgumentException("$context (DATA) must be not null.")
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        initViews(data)
        setEvents()
        setObservers()
        return binding.root
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
                }
                ApiStatus.ERROR, ApiStatus.DONE -> {
                    binding.statusImage.visibility = View.GONE
                }
                null -> Unit
            }
        }
    }

    private fun initViews(data: Show) {
        viewModel.start(data)

        binding.apply {
            mainPhotoImage.loadUrl(data.posterPath)
            name.text = data.name
            overview.text = data.overview.ifEmpty { getString(R.string.not_description) }
            review.text = data.voteAverage.toString()
        }
    }

    private fun setEvents() {
        adapter = ShowGridAdapter(ShowGridAdapter.OnClickListener {
            Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
            binding.mainScroll.fullScroll(ScrollView.FOCUS_UP);
            initViews(it)
        })
        binding.recyclerViewSuggested.adapter = adapter
    }

}