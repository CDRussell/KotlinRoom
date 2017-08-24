package com.cdrussell.myapplication.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.cdrussell.myapplication.MyApplication
import com.cdrussell.myapplication.R
import com.cdrussell.myapplication.domain.Bin
import com.cdrussell.myapplication.domain.BinDao
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.content_list.*

class ListActivity : AppCompatActivity() {

    val binDao: BinDao = MyApplication.database?.binDao()!!
    val adapter = BinAdapter(binDao)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {

            Single.fromCallable {
                binDao.insert(Bin())
            }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
        }

        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler.adapter = adapter

        binDao.getAllBins()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { bins ->
                    adapter.addEntries(bins)
                }
    }
}
