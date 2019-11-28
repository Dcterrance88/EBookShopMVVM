package com.me.ebookshopmvvm.db.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.me.ebookshopmvvm.db.BooksDatabase;
import com.me.ebookshopmvvm.db.dao_interfaces.BookDAO;
import com.me.ebookshopmvvm.db.models.Book;

import java.util.List;

public class BookRepository {

    private BookDAO bookDAO;
    private LiveData<List<Book>>books;

    public BookRepository(Application application) {
        BooksDatabase booksDatabase = BooksDatabase.getInstance(application);
        bookDAO = booksDatabase.bookDAO();
    }

    //Note 1
    public LiveData<List<Book>> getBooks(int categoryId) {
        return bookDAO.getBooks(categoryId);
    }

    //Insert Books
    public void insertBook(Book book){
        new InsertBookAsyncTask(bookDAO).execute(book);
    }

    //Delete Books
    public void deleteBook(Book book){
        new DeleteBookAsyncTask(bookDAO).execute(book);
    }

    //Update Books
    public void updateBook(Book book){
        new UpdateBookAsyncTask(bookDAO).execute(book);
    }

    //Async Tasks Insert Books
    private static class InsertBookAsyncTask extends AsyncTask<Book, Void, Void>{

        private BookDAO bookDAO;

        public InsertBookAsyncTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDAO.insert(books[0]);
            return null;
        }
    }

    //Async Task Delete Books
    private static class DeleteBookAsyncTask extends AsyncTask<Book, Void, Void>{

        private BookDAO bookDAO;

        public DeleteBookAsyncTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDAO.delete(books[0]);
            return null;
        }
    }

    //Async Task Update Books
    private static class UpdateBookAsyncTask extends AsyncTask<Book, Void, Void>{

        private BookDAO bookDAO;

        public UpdateBookAsyncTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDAO.update(books[0]);
            return null;
        }
    }


}

/*
* Note 1
* this method get all books belong to a given category, also we should change the return value as bookDAO.getBooks().
* */
