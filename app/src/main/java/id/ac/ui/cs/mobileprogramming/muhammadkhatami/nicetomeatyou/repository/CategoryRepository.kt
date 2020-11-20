package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.repository

import android.app.Application
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.dao.CateogryDao
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Category
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.room.NiceToMeatYouRoomDatabase
import kotlinx.coroutines.*

class CategoryRepository(application: Application) {

    private val categoryDao: CateogryDao?
    private var categories: LiveData<List<Category>>? = null
    val applicationScope = CoroutineScope(SupervisorJob())

    init {
        val db = NiceToMeatYouRoomDatabase.getDatabase(application.applicationContext, applicationScope)
        categoryDao = db?.categoryDao()
        categories = categoryDao?.getCategories()
    }

    fun getCategories(): LiveData<List<Category>>? {
        return categories
    }

    fun insert(category: Category) = runBlocking {
        this.launch(Dispatchers.IO) {
            categoryDao?.insertCategory(category)
        }
    }

    fun delete(category: Category) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                categoryDao?.deleteCategory(category)
            }
        }
    }

    fun update(category: Category) = runBlocking {
        this.launch(Dispatchers.IO) {
            categoryDao?.updateCategory(category)
        }
    }
}