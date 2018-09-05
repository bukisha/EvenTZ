package com.example.bookee.eventz.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;

import com.example.bookee.eventz.R;
import com.example.bookee.eventz.data.RetrofitCategoryRepository;
import com.example.bookee.eventz.data.RetrofitFactory;
import com.example.bookee.eventz.data.pojos.Category;
import com.example.bookee.eventz.home.HomeActivity;
import com.example.bookee.eventz.util.GlobalDataOperator;

import java.util.ArrayList;

import retrofit2.Retrofit;

public class SplashActivity extends AppCompatActivity implements MvpContract.View {
    private MvpContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new Presenter(this, ModelFactory.create());
        presenter.fetchInitialCategories();
    }

    @Override
    public void buildAndShowErrorFragment() {
        DialogFragment errorFragment=new DialogFragment();
    }

    @Override
    public void passInitialCategories(ArrayList<Category> categoryList) {
        GlobalDataOperator.storeGlobalCategoryList(categoryList,getSharedPreferences(getResources().getString(R.string.shared_preferences),MODE_PRIVATE));
        GlobalDataOperator.storeCategoriesShortIds(getCategoryIdShortNames(categoryList),getSharedPreferences(getResources().getString(R.string.shared_preferences),MODE_PRIVATE));
        HomeActivity.launch(this);
        finish();
    }

    private ArrayList<String> getCategoryIdShortNames(ArrayList<Category> list) {
        ArrayList<String> CategoryIds=new ArrayList<>();
        for(Category c : list) {
            CategoryIds.add(c.getShortName());
        }
        return CategoryIds;
    }
  }
