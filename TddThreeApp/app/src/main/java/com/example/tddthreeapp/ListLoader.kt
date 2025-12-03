package com.example.tddthreeapp

import androidx.test.espresso.idling.CountingIdlingResource
import java.util.Timer
import java.util.TimerTask

open class ListLoader(private val idlingResource: CountingIdlingResource?) {

    open fun loadList(count: Int, callback: (List<ListItem>) -> Unit) {
        idlingResource?.increment()
        Timer().schedule(object : TimerTask() {
            override fun run() {
                val list = (1..count).map { ListItem("Item $it") }
                callback(list)
                idlingResource?.decrement()
            }
        }, 1000)
    }
}