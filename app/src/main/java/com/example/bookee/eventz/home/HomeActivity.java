package com.example.bookee.eventz.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.bookee.eventz.EventApp;
import com.example.bookee.eventz.R;
import com.example.bookee.eventz.data.Category;
import com.example.bookee.eventz.eventlist.EventListActivity;
import com.example.bookee.eventz.utils.HashFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity implements MvpContract.View {
    private static final String TAG = "HomeActivity";
    public static final String CATEGORY_ID_KEY = "categoryId";
    private MvpContract.Presenter presenter;
    private RecyclerView recyclerView;
    private RecyclerViewItemOnClickListener recyclerViewItemOnClickListener;
    private static ArrayList<Category> initialCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.category_recycler_list);
        setupRecyclerView(recyclerView);
        HashMap<String, String> hash = HashFactory.createStringToString();
        MvpContract.Model model = new Model(EventApp.getRetrofitCategoryRepository(), hash);
        if (savedInstanceState == null) {
            Log.d(TAG, "onCreate: creating home presenter");
            presenter = new Presenter(this, model);
        }
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewItemOnClickListener = new RecyclerViewItemOnClickListener() {
            @Override
            public void itemClicked(String categoryName) {
                presenter.itemClicked(categoryName);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.populateNameList(initialCategoryList);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();
    }

    public static void launch(ArrayList<Category> list, Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        initialCategoryList = list;
        context.startActivity(intent);
    }

    @Override
    public void updateCategories(ArrayList<Category> categoryList) {
        CategoryCardsAdapter adapter = new CategoryCardsAdapter(categoryList, this, recyclerViewItemOnClickListener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void displayListOfEvents(String categoryId) {
        Intent intent = new Intent(this, EventListActivity.class);
        Log.d(TAG, "displayListOfEvents: " + categoryId);
        intent.putExtra(CATEGORY_ID_KEY, categoryId);
        startActivity(intent);
    }

    @Override
    public void displayErrorMessage(String errorMessage) {
        Toast.makeText(this, "Some error has happened " + errorMessage, Toast.LENGTH_LONG).show();
    }
}
