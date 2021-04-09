package com.example.foodieapp;

import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";

    TextView title,description,ingredients,calories,link;
    ImageView mealImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Log.d(TAG, "inside of onCreate method");

        Intent dataIntent = getIntent();

        final String Title = dataIntent.getExtras().getString("Title");
        final int img = dataIntent.getExtras().getInt("Image");
        final String Description = dataIntent.getExtras().getString("Description");
        final String Link = dataIntent.getExtras().getString("Link");
        final String Calories = dataIntent.getExtras().getString("Calories");
        final String Ingredients = dataIntent.getExtras().getString("Ingredients");


        title = findViewById(R.id.title);
        mealImage = findViewById(R.id.imageViewMeal);
        description = findViewById(R.id.description);
        link = findViewById(R.id.link);
        calories = findViewById(R.id.calories);
        ingredients = findViewById(R.id.ingredients);


        title.setText(Title);
        description.setText("Food Description: " + Description);
        link.setText("Link: " + Link);
        calories.setText("Calories: " + Calories);
        ingredients.setText("Ingredients:" + Ingredients);


        Glide.with(this).load(img).into(mealImage);

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("oh", "it worked");
                Intent webLinkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Link));
                startActivity(webLinkIntent);
            }
        });
        Log.d(TAG, "end of onCreate method");
    }

}