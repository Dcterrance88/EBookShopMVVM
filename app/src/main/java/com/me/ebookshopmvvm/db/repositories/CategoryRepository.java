package com.me.ebookshopmvvm.db.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.me.ebookshopmvvm.db.BooksDatabase;
import com.me.ebookshopmvvm.db.dao_interfaces.CategoryDAO;
import com.me.ebookshopmvvm.db.models.Category;

import java.util.List;

public class CategoryRepository {

    //Note 1
    private CategoryDAO categoryDAO;
    //Note 2
    private LiveData<List<Category>> categories;
    //Note 3
    public CategoryRepository(Application application) {

        BooksDatabase booksDatabase = BooksDatabase.getInstance(application);
        categoryDAO=booksDatabase.categoryDAO();

    }
    //Note 4
    public LiveData<List<Category>> getCategories() {
        return categoryDAO.getAllCategories();
    }


    //Insert Category
    public void insertCategory(Category category){
        new InsertCategoryAsyncTask(categoryDAO).execute(category);
    }

    //Delete Category
    public void deleteCategory(Category category){
        new DeleteCategoryAsyncTask(categoryDAO).execute(category);
    }

    public void updateCategory(Category category){
        new UpdateCategoryAsyncTask(categoryDAO).execute(category);
    }

    //Note 5
    //Async Tasks Insert Category
    private static class InsertCategoryAsyncTask extends AsyncTask<Category, Void, Void>{

        private CategoryDAO categoryDAO;

        public InsertCategoryAsyncTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }
        //Note 6
        @Override
        protected Void doInBackground(Category... categories) {
            categoryDAO.insert(categories[0]);
            return null;
        }
    }

    //Async Task Delete Category
    private static class DeleteCategoryAsyncTask extends AsyncTask<Category, Void, Void>{

        private CategoryDAO categoryDAO;

        public DeleteCategoryAsyncTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDAO.delete(categories[0]);
            return null;
        }
    }

    //Async Task Update Category
    private static class UpdateCategoryAsyncTask extends AsyncTask<Category, Void, Void>{

        private CategoryDAO categoryDao;

        public UpdateCategoryAsyncTask(CategoryDAO categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDao.update(categories[0]);
            return null;
        }
    }
}

/*
* Note 1
* reference variable for DAO interfaces
*
* Note 2
* we need to get a list of categories as live data
*
* Note 3
* Parameter of the constructor should be the Application. Inside this constructor
* let's create a new BooksDatabase instance passing application as a parameter.
* Now, we can get DAO instances using it.
*
* Note 4
* Method to get all categories
* ge can change the return value to categoryDao.getAllCategories();
*
* Note 5
* Now to insert, update and delete categories we need to use async tasks
*
* Note 6
* In this method we can call to the insert method of categoryDAO. Here this method
* parameter takes category object as an array but, we know we are passing just one object.
* so let's get the first object of the array
*
* */
