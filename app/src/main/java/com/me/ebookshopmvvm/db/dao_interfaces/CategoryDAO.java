package com.me.ebookshopmvvm.db.dao_interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.me.ebookshopmvvm.db.models.Category;

import java.util.List;

@Dao
public interface CategoryDAO {

    @Insert
    void insert(Category category);

    @Update
    void update(Category category);

    @Delete
    void delete(Category category);

    @Query("SELECT * FROM categories_table")
    LiveData<List<Category>> getAllCategories();
}
