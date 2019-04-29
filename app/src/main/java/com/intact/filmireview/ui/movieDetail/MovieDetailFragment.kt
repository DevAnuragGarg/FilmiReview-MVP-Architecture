package com.intact.filmireview.ui.movieDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.intact.filmireview.R
import com.intact.filmireview.ui.base.BaseFragment
import com.intact.filmireview.data.model.MovieDetailDTO
import com.intact.filmireview.util.IMAGE_BASE_URL_ORIGINAL
import com.intact.filmireview.util.MOVIE_ID
import com.squareup.picasso.Picasso
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Anurag Garg on 2019-04-24.
 */
class MovieDetailFragment : BaseFragment() {

    @BindView(R.id.movieBanner)
    lateinit var movieBannerImage: ImageView

    @Inject
    lateinit var picasso: Picasso

    private lateinit var movieId: String
    private lateinit var unBinder: Unbinder

    // creating new instance using static function
    companion object {

        fun newInstance(movieId: String) = MovieDetailFragment().apply {
            arguments = bundleOf(
                MOVIE_ID to movieId
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieId = arguments?.getString(MOVIE_ID)!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movie_detail, container, false)
        initializeVariables(view)
        return view
    }

    private fun initializeVariables(view: View) {
        Timber.d("Initialize variables")

        // initialize butterknife
        unBinder = ButterKnife.bind(this@MovieDetailFragment, view)
    }

    // updating the UI
    private fun updateUI(movieDetailDTO: MovieDetailDTO) {

        movieDetailDTO.apply {
            picasso.load(IMAGE_BASE_URL_ORIGINAL + backdrop_path).into(movieBannerImage)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unBinder.unbind()
    }
}