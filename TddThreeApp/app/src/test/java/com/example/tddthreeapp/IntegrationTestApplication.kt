package com.example.tddthreeapp

class IntegrationTestApplication : MyApplication() {
    override fun createListLoader(): ListLoader {
        // Return a loader that runs immediately (no timer) for Robolectric tests
        return object : ListLoader(null) {
            override fun loadList(count: Int, callback: (List<ListItem>) -> Unit) {
                val list = (1..count).map { ListItem("Item $it") }
                callback(list)
            }
        }
    }
}