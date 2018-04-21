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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CategoryNameViewHolder> {
    private ArrayList<Category> categoryList;
    private Context context;

    public MyAdapter(ArrayList<Category> categoryNames,Context currentContext) {
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
            Glide.with(context).load(R.drawable.music_home_logo).centerCrop().into(holder.categoryLogo);
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
}
