package com.livin.privacypolicygenerator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        prepareView()
    }


    private fun prepareView() {
        val homeItems = ArrayList<HomeItem>()
        homeItems.add(HomeItem(1, "Privacy Policy Generator", R.drawable.ic_privacy_policy))
        homeItems.add(
            HomeItem(
                2,
                "Terms and Conditions Generator",
                R.drawable.ic_terms_and_conditions
            )
        )
        homeItems.add(HomeItem(3, "Cookies Policy Generator", R.drawable.ic_cookie))
        homeItems.add(HomeItem(4, "Disclaimer Generator", R.drawable.ic_disclaimer))


        homeRecycler.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        val allStickerPacksListAdapter = HomeListAdapter(homeItems) { selectedItem: HomeItem ->
            startActivity(Intent(this, GeneratorActivity::class.java).apply {
                putExtra("item_type", selectedItem.id)
            })
        }
        homeRecycler.adapter = allStickerPacksListAdapter
    }
}