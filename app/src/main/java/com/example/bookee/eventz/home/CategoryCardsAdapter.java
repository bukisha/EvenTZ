package com.example.bookee.eventz.home;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bookee.eventz.R;
import com.example.bookee.eventz.data.Category;

import java.util.ArrayList;

public class CategoryCardsAdapter extends RecyclerView.Adapter<CategoryCardsAdapter.CategoryNameViewHolder> {
    private ArrayList<Category> categoryList;
    private Context context;

    public CategoryCardsAdapter(ArrayList<Category> categoryNames, Context currentContext) {
        this.categoryList = categoryNames;
        this.context=currentContext;
    }

    @Override
    public CategoryNameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv= (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_list_item,parent,false);
        CategoryNameViewHolder itemToReturn=new CategoryNameViewHolder(cv);
        return itemToReturn;
    }

    @Override
    public void onBindViewHolder(CategoryNameViewHolder holder, int position) {
            Glide.with(context).load(getHomeItemLogo(categoryList.get(position).getName())).centerCrop().into(holder.categoryLogo);
            holder.categoryName.setText(categoryList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class CategoryNameViewHolder extends RecyclerView.ViewHolder{
         CardView categoryCard;
         ImageView categoryLogo;
         TextView categoryName;


        public CategoryNameViewHolder(View itemView) {
            super(itemView);
            categoryCard=itemView.findViewById(R.id.card_view);
            categoryLogo=itemView.findViewById(R.id.category_item_image);
            categoryName=itemView.findViewById(R.id.category_item_name);
        }
    }
    public int getHomeItemLogo(String itemName) {
             switch(itemName) {
                 case "Music": return R.drawable.ic_music_cat;
                 case "Business & Professional": return R.drawable.ic_business_cat;
                 case "Food & Drink": return R.drawable.ic_food_cat;
                 case "Community & Culture": return R.drawable.ic_culture_cat;
                 case "Performing & Visual Arts": return R.drawable.ic_art_cat;
                 case "Film, Media & Entertainment": return R.drawable.ic_film_cat;
                 case "Sports & Fitness": return R.drawable.ic_sport_cat;
                 case "Health & Wellness": return R.drawable.ic_health_cat;
                 case "Science & Technology": return R.drawable.ic_science_cat;
                 case "Travel & Outdoor": return R.drawable.ic_travel_cat;
                 case "Charity & Causes": return R.drawable.ic_charity_cat;
                 case "Religion & Spirituality": return R.drawable.ic_religion_cat;
                 case "Family & Education": return R.drawable.ic_education_cat;
                 case "Seasonal & Holiday": return R.drawable.ic_holiday_cat;
                 case "Government & Politics": return R.drawable.ic_politics_cat;
                 case "Fashion & Beauty": return R.drawable.ic_fashion_cat;
                 case "Home & Lifestyle": return R.drawable.ic_lifestyle_cat;
                 case "Auto, Boat & Air": return R.drawable.ic_auto_cat;
                 case "Hobbies & Special Interest": return R.drawable.ic_hobby_cat;
                 case "Other": return R.drawable.ic_otherl_cat;
                 case "School Activities": return R.drawable.ic_school_cat;
                 }
        return 0;
    }
}
