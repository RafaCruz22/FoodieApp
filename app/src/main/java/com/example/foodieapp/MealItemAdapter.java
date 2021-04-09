package com.example.foodieapp;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MealItemAdapter extends RecyclerView.Adapter <MealItemAdapter.ViewHolder> {

    private static final String TAG = "MealItemAdapter";

    private int selectedItem = -1;
    private ArrayList<MealItem> mealData;
    private Context context;


    public MealItemAdapter(Context context, ArrayList<MealItem> mealArrayList){
        this.context = context;
        mealData = mealArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "inside onCreateViewHolder method");
        return new ViewHolder((LayoutInflater.from(context).inflate(R.layout.meal_items,parent,false)));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealItem currMeal = mealData.get(position);
        holder.bindItem(currMeal);

    }

    @Override
    public int getItemCount() {
        return mealData.size();
    }


    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {


        private TextView textTitle, textDescription, textIngredients, textCalories, textLink;
        private ImageView imageViewMeal;


        public ViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "inside ViewHolder method");

            textTitle = itemView.findViewById(R.id.title);
            textDescription = itemView.findViewById(R.id.description);
            textIngredients = itemView.findViewById(R.id.ingredients);
            textCalories = itemView.findViewById(R.id.calories);
            imageViewMeal = itemView.findViewById(R.id.imageViewMeal);
            textLink = itemView.findViewById(R.id.link);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            Log.d(TAG, "end of ViewHolder method");
        }

        public void bindItem(MealItem currentMeal) {
            Log.d(TAG, "inside bindItem methods");

            textTitle.setText(currentMeal.getTitle());
            textDescription.setText(currentMeal.getDescription());
            textIngredients.setVisibility(View.INVISIBLE);
            textCalories.setVisibility(View.INVISIBLE);
            textLink.setVisibility(View.INVISIBLE);

            Glide.with(context).load(currentMeal.getImageId()).into(imageViewMeal);

            Log.d(TAG, "end of bindItem method");

        }

        @Override
        public void onClick(View view) {

            Log.d(TAG,"inside onClick method");
            selectedItem = getLayoutPosition();
            Intent dataIntent = new Intent(context, DetailActivity.class);
            dataIntent.putExtra("Title",mealData.get(selectedItem).getTitle());
            dataIntent.putExtra("Description",mealData.get(selectedItem).getDescription());
            dataIntent.putExtra("Link",mealData.get(selectedItem).getLink());
            dataIntent.putExtra("Ingredients",mealData.get(selectedItem).getIngredients());
            dataIntent.putExtra("Calories",mealData.get(selectedItem).getCalories());
            dataIntent.putExtra("Image",mealData.get(selectedItem).getImageId());
            context.startActivity(dataIntent);
            Log.d(TAG,"end of onClick for MealItemAdapater");
        }

        @Override
        public boolean onLongClick(View view) {
            Log.d(TAG,"inside onLongClick method");

            selectedItem = getLayoutPosition();

            AlertDialog.Builder confirmDialog = new AlertDialog.Builder(view.getContext());
            confirmDialog.setMessage("Are you sure you want to remove item?");
            confirmDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mealData.remove(selectedItem);
                    notifyItemRemoved(selectedItem);

                }
            });

            confirmDialog.create().show();
            Log.d(TAG,"end of  onLongClick method");
            return false;
        }
    }
}
