package com.livin.privacypolicygenerator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_item_type.view.*
import java.util.*

class HomeListAdapter internal constructor(
    private val homeItems: ArrayList<HomeItem>,
    private val onHomeItemClickedListener: (HomeItem) -> Unit
) : RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): HomeListViewHolder {
        val context = viewGroup.context
        val layoutInflater = LayoutInflater.from(context)
        val stickerPackRow =
            layoutInflater.inflate(R.layout.layout_item_type, viewGroup, false)
        return HomeListViewHolder(stickerPackRow)
    }

    override fun onBindViewHolder(viewHolder: HomeListViewHolder, index: Int) {
        val home = homeItems[index]
        viewHolder.itemView.itemText.text = home.itemName
        viewHolder.itemView.itemImage.setImageResource(home.itemImage)
        viewHolder.itemView.setOnClickListener { view: View? ->
            onHomeItemClickedListener(home)
        }
        //  viewHolder.imageHolder.setBackgroundColor(getRandomMaterialColor());
    }

    override fun getItemCount(): Int {
        return homeItems.size
    }

    interface OnHomeItemClickedListener {
        fun onAddButtonClicked(stickerPack: HomeItem?)
    }

    class HomeListViewHolder(container: View) :
        RecyclerView.ViewHolder(container)
}