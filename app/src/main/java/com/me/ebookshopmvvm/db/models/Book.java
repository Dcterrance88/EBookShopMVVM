package com.me.ebookshopmvvm.db.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

//Note 1
@Entity(tableName = "books_table", foreignKeys = @ForeignKey(entity = Category.class,
        parentColumns = "id", childColumns = "category_id", onDelete = CASCADE))
public class Book extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "book_id")
    private int bookId;

    @ColumnInfo(name = "book_name")
    private String bookName;

    @ColumnInfo(name = "unit_price")
    private String unitPrice;

    @ColumnInfo(name = "category_id")
    private int categoryId;

    public Book() {
    }

    public Book(int bookId, String bookName, String unitPrice, int categoryId) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.unitPrice = unitPrice;
        this.categoryId = categoryId;
    }

    @Bindable
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
        notifyPropertyChanged(BR.bookName);
    }

    @Bindable
    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
        notifyPropertyChanged(BR.unitPrice);
    }

    @Bindable
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        notifyPropertyChanged(BR.unitPrice);
    }
}

/*
* Note 1
* Here in this books_table we use categoryId, which is the primary key of the
* category_table as the foreign key. For this simple apps scenario, categories_table
* and books table has one to many relationship.
* One category has zero to many books. One book has only one category.
* if we delete one category all the books belong to that category should also be
* deleted. Now let's define that relationship here
* foreingKeys equals to ..., we need to provide details of the foreign key here.
* @Entity(tableName = "books_table", foreignKeys = @ForeignKey(entity = Category.class, parentColumns = "id",
* childColumns = "category_id", onDelete = CASCADE)
*
* parentColumns is the column name of the categories_table
* childColumns is the column name of the books_table
* onDelete = CASCADE => here we tell specifically that if category row will be deleted
* we'd like to delete also all of the books.
*
*
* */
