package com.compose.movies.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.compose.movies.R
import com.compose.movies.presentation.ui.ui.theme.MovieItems
import com.compose.movies.presentation.ui.ui.theme.MoviesTheme
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalMaterial3Api
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        //  setObservers()
        //setEvents()
        return ComposeView(requireContext()).apply {
            setContent {
                MoviesTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background) {
                        Scaffold(topBar = {
                            SmallTopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })
                        }) {
                            MovieItems()
                        }
                    }
                }
            }
        }

    }

    /*  private fun setEvents() {
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
      }*/
}