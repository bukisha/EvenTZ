package com.example.bookee.eventz.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import com.example.bookee.eventz.home.HomeActivity;

public class SplashActivity extends AppCompatActivity implements MvpContract.View {
    private MvpContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new Presenter(this, ModelFactory.create());
        presenter.fetchInitialCategories(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void buildAndShowErrorFragment() {
        DialogFragment errorFragment=new DialogFragment();
    }

    @Override
    public void launchHomeActivity() {
        HomeActivity.launch(this);
        finish();
    }
  }
