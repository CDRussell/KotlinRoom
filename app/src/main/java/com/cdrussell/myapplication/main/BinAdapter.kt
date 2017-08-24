package com.cdrussell.myapplication.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cdrussell.myapplication.R
import com.cdrussell.myapplication.domain.Bin
import com.cdrussell.myapplication.domain.BinDao
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.list_item_bin.view.*


class BinAdapter(private val binDao: BinDao) : RecyclerView.Adapter<BinAdapter.ViewHolder>() {

    private val items: MutableList<Bin>

    init {
        items = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_bin, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bin = items[position]
        holder.itemView.title.text = bin.id.toString()
        holder.itemView.tag = bin

        holder.itemView.deleteButton.setOnClickListener {
            Single.fromCallable {
                binDao.deleteBin(bin)
            }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.title
    }

    fun addEntries(bins: List<Bin>) {
        items.clear()
        items.addAll(bins)
        notifyDataSetChanged()
    }

}