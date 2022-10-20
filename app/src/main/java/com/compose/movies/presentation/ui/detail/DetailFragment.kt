package com.compose.movies.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.compose.movies.databinding.FragmentDetailBinding
import com.compose.movies.domain.model.Show
import com.compose.movies.framework.AppConstants.DATA_PARAM
import com.compose.movies.framework.loadUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val data = arguments?.getParcelable<Show>(DATA_PARAM)
            ?: throw IllegalArgumentException("$context (DATA) must be not null.")
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.apply {
            mainPhotoImage.loadUrl(data.posterPath)
            name.text = data.name
            title.text = data.overview
            review.text = data.voteAverage.toString()
        }
        return binding.root
    }

}