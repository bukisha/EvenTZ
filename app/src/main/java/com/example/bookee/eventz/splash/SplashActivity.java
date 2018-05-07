package com.example.bookee.eventz.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.bookee.eventz.data.Category;
import com.example.bookee.eventz.data.CategoryWebApi;
import com.example.bookee.eventz.data.RetrofitCategoryRepository;
import com.example.bookee.eventz.data.RetrofitFactory;
import com.example.bookee.eventz.home.HomeActivity;

import java.util.ArrayList;

import retrofit2.Retrofit;

public class SplashActivity extends AppCompatActivity implements MvpContract.View {
    private static final String TAG = "SplashActivity";
    private MvpContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit retrofit= RetrofitFactory.buildRetrofit();
        RetrofitCategoryRepository repository=new RetrofitCategoryRepository(retrofit.create(CategoryWebApi.class));
        Model model = new Model(repository);
        presenter = new Presenter(this, model);
        presenter.fetchInitialCategories();
    }

    @Override
    public void passInitialCategories(ArrayList<Category> categoryList) {
        HomeActivity.launch(categoryList,this);
        finish();
        }

    @Override
    public void showErrorFragment() {
        //TODO createStringToString and show error fragment
    }
}
