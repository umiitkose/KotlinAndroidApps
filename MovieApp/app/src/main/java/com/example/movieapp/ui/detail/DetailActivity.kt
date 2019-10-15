package com.example.movieapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.R
import com.example.movieapp.common.BaseActivity
import com.example.movieapp.common.ViewPagerAdapter
import com.example.movieapp.databinding.ActivityDetailBinding
import com.example.movieapp.model.movie.MovieResults
import com.example.movieapp.util.Constans
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity<ActivityDetailBinding,DetailViewModel>() {

    override fun getLayoutRes(): Int = R.layout.activity_detail

    override fun getViewModel(): Class<DetailViewModel> = DetailViewModel::class.java

    private var movie: MovieResults? = null

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        setupUI()

        intent.extras.let{
            movie= it?.getParcelable(Constans.EXTRA_POPULAR_MOVIES)
            setupViewPager(movie)
            fabBehaviour(movie)
            detail_tabs.setupWithViewPager(detail_viewpager)
            dataBinding.movie= movie
        }

    }

    private fun setupUI(){
        setSupportActionBar(detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupViewPager(movie: MovieResults?){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.apply {  }
        detail_viewpager.adapter = adapter
    }

    private fun fabBehaviour(movie: MovieResults?){
        detail_appbar_layout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, vertical ->
            if(Math.abs(vertical)-appBarLayout.totalScrollRange == 0){
                favorite_fab.hide()

                supportActionBar?.setDisplayShowTitleEnabled(true)
                detail_toolbar.title = movie?.original_title
            }
            else{
                favorite_fab.show()
                supportActionBar?.setDisplayShowTitleEnabled(false)
                detail_toolbar.title = " "
            }
        })

        detail_collapsing_toolbarlayout.setExpandedTitleColor(resources.getColor(android.R.color.transparent))
    }
}
