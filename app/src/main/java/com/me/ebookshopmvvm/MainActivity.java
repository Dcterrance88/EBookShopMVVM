package com.me.ebookshopmvvm;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.me.ebookshopmvvm.databinding.ActivityMainBinding;
import com.me.ebookshopmvvm.db.models.Book;
import com.me.ebookshopmvvm.db.models.Category;
import com.me.ebookshopmvvm.view_model.MainActivityViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    //Note 1
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandlers handlers;
    private Category selectedCategory;
    private ArrayList<Category> categoriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentViewBinding();

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mainActivityViewModel.getAllCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoriesList=(ArrayList<Category>)categories;
                for(Category c:categories){
                    Log.i("MyTAG1", c.getCategoryName());
                }
                showOnSpinner();
            }

            
        });

        mainActivityViewModel.getBooksOfASelectedCategory(3).observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                for (Book b: books){
                    Log.i("MyTAG2", b.getBookName());
                }
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void showOnSpinner() {
        ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<Category>(this, R.layout.spinner_item, categoriesList);
        categoryArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        activityMainBinding.setSpinnerAdapter(categoryArrayAdapter);
    }

    private void setContentViewBinding(){
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        handlers = new MainActivityClickHandlers();
        activityMainBinding.setClickHandlers(handlers);

    }

    public class MainActivityClickHandlers{

        public void onFABClicked(View view){
            Toast.makeText(getApplicationContext(), "FAB Clicked", Toast.LENGTH_LONG).show();
        }

        //Note 2
        public void onSelected(AdapterView<?> parent, View view, int position, long id){

            selectedCategory = (Category) parent.getItemAtPosition(position);
            String message = "ID is:" + selectedCategory.getId() + "\n Name is: " + selectedCategory.getCategoryName() + "\n Description is: " + selectedCategory.getCategoryDescription();
            Toast.makeText(parent.getContext(), message, Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
/*
* Note 1
* Reference to the MainActivityBindingClass
*
* Note 2
* this is just a standard onSelectedItem code for an appCompact spinner, there are four
* parameters. Parent parameter give the list of objects we passed to the spinner adapter.
* Using this position value you can get the selected object.
* i have written codes to get the selected category object and show the details of the
* category object and show the details of the category on a toast message.
* */