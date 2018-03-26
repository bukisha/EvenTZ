package com.example.bookee.eventz.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.bookee.eventz.data.Category;
import com.example.bookee.eventz.home.HomeActivity;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity implements MvpContract.View{
    private static final String TAG = "SplashActivity";
    public static final String LIST_OF_CATEGORIES_KEY ="list of categories";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: starting");
        MvpContract.Presenter presenter=new Presenter(this);
        presenter.fetchInitialCategories();
    }

    @Override
    public void passInitialCategories(ArrayList<Category> categoryList) {
    Intent intent=new Intent(this, HomeActivity.class);
        intent.putExtra(LIST_OF_CATEGORIES_KEY,categoryList);
        startActivity(intent);
    }

    @Override
    public void showErrorFragment() {
        //TODO create and show error fragment
    }
}
