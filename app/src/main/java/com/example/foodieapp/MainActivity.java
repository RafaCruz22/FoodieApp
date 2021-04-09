package com.example.foodieapp;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    static final int ADD_MEAL_ITEM_REQUEST = 1;

    private RecyclerView recyclerView;
    private ArrayList<MealItem> mealData;
    private MealItemAdapter mealAdapter;

    private int girdColumnCount;

//    ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d(TAG, "inside of method");
        Log.d(TAG,"end of method");

        girdColumnCount = getResources().getInteger(R.integer.grid_column_count);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, girdColumnCount));

        mealData = new ArrayList<>();
        mealAdapter = new MealItemAdapter(this, mealData);
        recyclerView.setAdapter(mealAdapter);

        loadData();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "inside of onClick for FAB method");
                Intent addIntent = new Intent(getApplicationContext(), AddItemActivity.class);
                startActivityForResult(addIntent,ADD_MEAL_ITEM_REQUEST);
                //add meal item to list
                Log.d(TAG,"end of onClick for FAB method");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_MEAL_ITEM_REQUEST){
            if(resultCode == RESULT_OK){
                final ArrayList<String> result = data.getStringArrayListExtra(AddItemActivity.MEAL_DATA);
                TypedArray defaultImage = getResources().obtainTypedArray(R.array.default_image);
                Log.d("list a", " "+ result.size());
                if(result.get(0).isEmpty() != true){
                    mealData.add(new MealItem(result.get(0),result.get(1),result.get(2),result.get(3),defaultImage.getResourceId(0,0), result.get(4)));
                }
                mealAdapter.notifyDataSetChanged();
            }
        }
    }

    public void loadData() {
        mealData.clear();//clears data to ensure no copies

        String [] mealTitles = getResources().getStringArray(R.array.meal_titles);
        String [] mealDescription = getResources().getStringArray(R.array.meal_description);
        String [] mealIngredients = getResources().getStringArray(R.array.meal_ingredients);
        String [] mealCalories = getResources().getStringArray(R.array.meal_calories);//got to get integer array
        TypedArray mealImages = getResources().obtainTypedArray(R.array.meal_images);
        String [] mealLinks = getResources().getStringArray((R.array.meal_web_link));

        Log.d("check", ""+ mealTitles[0]);

        for(int i=0; i<mealTitles.length; i++) {
            mealData.add(new MealItem(mealTitles[i], mealDescription[i],mealIngredients[i],mealCalories[i],mealImages.getResourceId(i,0),mealLinks[i]));
        }


        mealAdapter.notifyDataSetChanged();
        mealImages.recycle();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "inside of onCreateOptionsMenu method");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Log.d(TAG,"end of onCreateOptionsMenu method");
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