package com.example.foodieapp;

import android.util.Log;

public class MealItem {

    private static final String TAG = "MealIteam Class";
    private String title;
    private String description;
    private String ingredients;
    private String calories;
    private String link;
    private int imageResource;

    MealItem(String title, String description){
        this.title = title;
        this.description = description;

    }

    MealItem(String title, String description, int imageId){
        this.title = title;
        this.description = description;
        this.imageResource = imageId;

    }

    MealItem(String title, String description, String ingredients, String calories, int imageId,String link) {
        Log.d(TAG, "inside of MealItem method");
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.calories = calories;
        this.imageResource = imageId;
        this.link = link;
        Log.d(TAG,"end of MealItem method");
    }

    public String getTitle() {return title;}

    public String getDescription() {return description;}

    public String getIngredients() {return ingredients;}

    public String getCalories() {return calories;}

    public String getLink(){return link;}

    public int getImageId() {return imageResource;}

}

