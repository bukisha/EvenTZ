package com.example.bookee.eventz.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.bookee.eventz.EventApp;
import com.example.bookee.eventz.data.Category;
import com.example.bookee.eventz.home.HomeActivity;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity implements MvpContract.View{
    private static final String TAG = "SplashActivity";
    public static final String LIST_OF_CATEGORIES_KEY ="list of categories";
    private MvpContract.Presenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Model model=new Model(EventApp.getRetrofitCategoryRepository());
        presenter = new Presenter(this,model);
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
