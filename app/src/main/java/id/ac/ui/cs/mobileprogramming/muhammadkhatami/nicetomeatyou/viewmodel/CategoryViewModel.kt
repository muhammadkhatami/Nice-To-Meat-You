package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Category
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.repository.CategoryRepository

class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    private var categoryRepository = CategoryRepository(application)
    private var categories: LiveData<List<Category>>? = categoryRepository.getCategories()

    fun getCategories(): LiveData<List<Category>>? {
        return categories
    }
}