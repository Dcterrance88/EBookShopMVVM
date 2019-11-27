package com.me.ebookshopmvvm.db.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "categories_table")
public class Category extends BaseObservable {

    @PrimaryKey(autoGenerate = true) //Note 1
    private int id;

    @ColumnInfo(name = "category_name")
    private String categoryName;

    @ColumnInfo(name = "category_description")
    private String categoryDescription;

    public Category() {
    }

    public Category(int id, String categoryName, String categoryDescription) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    @Bindable //Note 2
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id); //Note 3
    }

    @Bindable
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        notifyPropertyChanged(BR.categoryName);
    }

    @Bindable
    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
        notifyPropertyChanged(BR.categoryDescription);
    }
}

/*
* Note 1
* this allows the database to auto increment the id values of te new category records,
* otherwise you need to write codes for it
*
* Note 2
* @Bindable annotation should be applied to any getter accessor method of an Observable
* class Bindable will generate a field in the BR class to identify the field that has changed
*
* Note 3
* For setter methods we are going to add invocations for notifyPropertyChanged() method
* passing BR value, if BR class is not showing you should rebuild the project
* */
