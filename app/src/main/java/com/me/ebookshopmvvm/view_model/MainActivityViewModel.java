package com.me.ebookshopmvvm.view_model;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.me.ebookshopmvvm.db.models.Book;
import com.me.ebookshopmvvm.db.models.Category;
import com.me.ebookshopmvvm.db.repositories.BookRepository;
import com.me.ebookshopmvvm.db.repositories.CategoryRepository;

import java.util.List;
//Note 1
public class MainActivityViewModel extends AndroidViewModel {

    private BookRepository bookRepository;
    private CategoryRepository categoryRepository;
    //Note 3
    private LiveData<List<Category>> allCategories;
    private LiveData<List<Book>> booksOfASelectedCategory;

    //Note 2
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        bookRepository = new BookRepository(application);
        categoryRepository = new CategoryRepository(application);

    }

    //Note 4
    public LiveData<List<Category>> getAllCategories() {
        allCategories = categoryRepository.getCategories();
        return allCategories;
    }

    //Note 5
    public LiveData<List<Book>> getBooksOfASelectedCategory(int categoryId) {
        booksOfASelectedCategory = bookRepository.getBooks(categoryId);
        return booksOfASelectedCategory;
    }

    public void addNewBook(Book book){
        bookRepository.insertBook(book);
    }

    public void deleteBook(Book book){
        bookRepository.deleteBook(book);
    }

    public void updateBook(Book book){
        bookRepository.updateBook(book);
    }
}

/*
* Note 1
* We extend AndroidViewModel when we want to use the application context. Otherwise
* you can just extended the ViewModel instead of AndroidViewModel but in this project
* when we are creating an instance of repository we need to pass the application as an
* argument therefore we have to chose AndroidViewModel
*
* Note 2
* We need a BookRepository and CategoryRepository instance to invoke repository methods
*
* Note 3
* we need a livedata of list of categories and books to get all categories and books
*
* Note 4
* we can get the list from the repository
*
* Note 5
* we need to set category id as a parameter, now we can invoke the getBooks method of the BookRepository
* passing category id as an argument
* */
