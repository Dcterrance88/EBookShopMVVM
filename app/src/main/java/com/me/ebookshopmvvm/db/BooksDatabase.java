package com.me.ebookshopmvvm.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.me.ebookshopmvvm.db.dao_interfaces.BookDAO;
import com.me.ebookshopmvvm.db.dao_interfaces.CategoryDAO;
import com.me.ebookshopmvvm.db.models.Book;
import com.me.ebookshopmvvm.db.models.Category;

@Database(entities = {Category.class, Book.class}, version = 1)
public abstract class BooksDatabase extends RoomDatabase {

    //Note 1
    public abstract CategoryDAO categoryDAO();
    public abstract BookDAO bookDAO();
    //Note 2
    private static BooksDatabase instance;

    //Note3
    public static synchronized BooksDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), BooksDatabase.class, "books_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    //Note 4
    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InitialDataAsyncTask(instance).execute();
        }
    };

    //Note 5
    private static class InitialDataAsyncTask extends AsyncTask<Void, Void, Void>{

        //Note 6
        private CategoryDAO categoryDAO;
        private BookDAO bookDAO;

        public InitialDataAsyncTask(BooksDatabase booksDatabase) {
            categoryDAO = booksDatabase.categoryDAO();
            bookDAO = booksDatabase.bookDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Category category1 = new Category();
            category1.setCategoryName("Text Books");
            category1.setCategoryDescription("Text Books Description");

            Category category2 = new Category();
            category2.setCategoryName("Novels");
            category2.setCategoryDescription("Novels Description");

            Category category3 = new Category();
            category3.setCategoryName("Other Books");
            category3.setCategoryDescription("Text Books Description");

            categoryDAO.insert(category1);
            categoryDAO.insert(category2);
            categoryDAO.insert(category3);

            Book book1 = new Book();
            book1.setBookName("High school Java");
            book1.setUnitPrice("$150");
            book1.setCategoryId(1);

            Book book2 = new Book();
            book2.setBookName("Mathematics for beginners");
            book2.setUnitPrice("$150");
            book2.setCategoryId(1);

            Book book3 = new Book();
            book3.setBookName("Object Oriented Android App Design");
            book3.setUnitPrice("$150");
            book3.setCategoryId(1);

            Book book4 = new Book();
            book4.setBookName("Astrology for beginners");
            book4.setUnitPrice("$190");
            book4.setCategoryId(1);

            Book book5 = new Book();
            book5.setBookName("High school Magic Tricks");
            book5.setUnitPrice("$150");
            book5.setCategoryId(1);

            Book book6 = new Book();
            book6.setBookName("Chemistry for secondary school students");
            book6.setUnitPrice("$250");
            book6.setCategoryId(1);

            Book book7 = new Book();
            book7.setBookName("A Game of Cats");
            book7.setUnitPrice("$19.99");
            book7.setCategoryId(2);

            Book book8 = new Book();
            book8.setBookName("High school Java");
            book8.setUnitPrice("$150");
            book8.setCategoryId(2);

            Book book9 = new Book();
            book9.setBookName("Adventure of Joe Finn");
            book9.setUnitPrice("$13");
            book9.setCategoryId(2);

            Book book10 = new Book();
            book10.setBookName("Arc of witches");
            book10.setUnitPrice("$19.99");
            book10.setCategoryId(2);

            Book book11 = new Book();
            book11.setBookName("Can I run");
            book11.setUnitPrice("$16.99");
            book11.setCategoryId(2);

            Book book12 = new Book();
            book12.setBookName("Story of a joker");
            book12.setUnitPrice("$13");
            book12.setCategoryId(2);

            Book book13 = new Book();
            book13.setBookName("Notes of a alien life cycle researcher");
            book13.setUnitPrice("$1250");
            book13.setCategoryId(3);

            Book book14 = new Book();
            book14.setBookName("Top 9 myths about UFOs");
            book14.setUnitPrice("$789");
            book14.setCategoryId(3);

            Book book15 = new Book();
            book15.setBookName("How to become a millionaire in 24 hours");
            book15.setUnitPrice("$1250");
            book15.setCategoryId(3);

            Book book16 = new Book();
            book16.setBookName("1 hour work month");
            book16.setUnitPrice("$199");
            book16.setCategoryId(3);

            bookDAO.insert(book1);
            bookDAO.insert(book2);
            bookDAO.insert(book3);
            bookDAO.insert(book4);
            bookDAO.insert(book5);
            bookDAO.insert(book6);
            bookDAO.insert(book7);
            bookDAO.insert(book8);
            bookDAO.insert(book9);
            bookDAO.insert(book10);
            bookDAO.insert(book11);
            bookDAO.insert(book12);
            bookDAO.insert(book13);
            bookDAO.insert(book14);
            bookDAO.insert(book15);
            bookDAO.insert(book16);






            return null;
        }
    }


}

/*
* Note 1
* abstract reference to DAO interfaces
*
* Note 2
* this code is to get the singleton instance of the database class.
*
* Note 3
* - name of the data base class => BooksDatabase.class
* - name we give to the SQLite database => books_database
* - .fallbackToDestructiveMigration() => So this means when we are changing the version
* number, when we are migrating data, this will delete the database table and recreate
* the table
*
* Note 4
* if we can add some initial categories and books data it will be very helpful to list them
*
* Note 5
* Inserting data to database should be done in a separate background thread so i am created a
* new async task for that here.
*
* Note 6
* we nee to define DAO interfaces here and we should get them in the constructor
* now we can use them to add new categories and books data to database only once when the
* database creates
* */

