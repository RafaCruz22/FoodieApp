package com.example.foodieapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddItemActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AddIteamActivity";
    public static final String MEAL_DATA = "MEAL_DATA";

    EditText editTitle,editDescription,editIngredient,editCalories,editLink;
    Button doneButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Log.d(TAG, "inside onCreate method");


        editTitle = findViewById(R.id.editText_Title);
        editDescription = findViewById(R.id.editText_Description);
        editIngredient = findViewById(R.id.editText_Ingredients);
        editCalories = findViewById(R.id.editText_Calories);
        editLink = findViewById(R.id.editText_weblink);

        doneButton = findViewById(R.id.doneBTN);
        doneButton.setOnClickListener(this);


        Log.d(TAG, "end of onCreate method");

    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "inside onCreate method");
        ArrayList<String> mealDataPassBack = new ArrayList<String>();

        String title = editTitle.getText().toString();
        String description = editDescription.getText().toString();
        String ingredients = editIngredient.getText().toString();
        String calories = editCalories.getText().toString();
        String link = editLink.getText().toString();


        mealDataPassBack.clear();

        mealDataPassBack.add(title);
        mealDataPassBack.add(description);
        mealDataPassBack.add(ingredients);
        mealDataPassBack.add(calories);
        mealDataPassBack.add(link);

        Log.d("ssd", " " + mealDataPassBack.isEmpty());

        final Intent newItem = new Intent();
        newItem.putStringArrayListExtra(MEAL_DATA, mealDataPassBack);
        setResult(MainActivity.RESULT_OK, newItem);
        finish();
        Log.d(TAG, "end of onCreate method");


    }

}
