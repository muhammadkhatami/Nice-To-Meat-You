package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Category
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.repository.CategoryRepository

class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    private var categoryRepository = CategoryRepository(application)
    private var categories: LiveData<List<Category>>? = categoryRepository.getCategories()

    fun insertCategory(category: Category) {
        categoryRepository.insert(category)
    }

    fun getCategories(): LiveData<List<Category>>? {
        return categories
    }

    fun deleteCategory(category: Category) {
        categoryRepository.delete(category)
    }

    fun updateCategory(category: Category) {
        categoryRepository.update(category)
    }
}