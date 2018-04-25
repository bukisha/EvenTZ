package com.example.bookee.eventz.create;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.bookee.eventz.EventApp;
import com.example.bookee.eventz.R;
import com.example.bookee.eventz.data.Event;
import com.example.bookee.eventz.data.RetrofitEventsRepository;


public class CreateActivity extends AppCompatActivity implements MvpContract.View{
    private Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        RetrofitEventsRepository repository= EventApp.getRetrofitEventsRepository();
        MvpContract.Model model=new Model(repository);

        if(savedInstanceState==null) {
            presenter=new Presenter(model,this);
        }
    }

    @Override
    public void showCreatedEvent(Event event) {

    }

    public static void launch(Context context) {
        Intent intent=new Intent(context,CreateActivity.class);
       context.startActivity(intent);
    }
}
