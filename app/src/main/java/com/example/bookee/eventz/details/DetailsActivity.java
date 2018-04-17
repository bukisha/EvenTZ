package com.example.bookee.eventz.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.bookee.eventz.EventApp;
import com.example.bookee.eventz.R;
import com.example.bookee.eventz.data.Event;
import com.example.bookee.eventz.data.RetrofitEventsRepository;

public class DetailsActivity extends AppCompatActivity implements MvpContract.View {
    private static final String TAG = "DetailsActivity";
    private Presenter presenter;
    private static String idOfEvent;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        initGui();
        RetrofitEventsRepository repository = EventApp.getRetrofitEventsRepository();
        Model model = new Model(repository);
        presenter = new Presenter(model, this);
    }

    private void initGui() {
        collapsingToolbarLayout=findViewById(R.id.collapsing_toolbar_layout);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(toolbar!=null) {
           getSupportActionBar().setDisplayHomeAsUpEnabled(true);
           }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.fetchEventForId(idOfEvent);
    }

    public static void launch(String eventId, Context context) {
        Intent intent = new Intent(context, DetailsActivity.class);
        idOfEvent = eventId;
        context.startActivity(intent);
    }

    @Override
    public void displayEvent(Event event) {
        Log.d(TAG, "displayEvent: "+event.getName().getText());
        String s= (String) getSupportActionBar().getTitle();
        Log.d(TAG, "displayEvent: tittle is"+s);
        collapsingToolbarLayout.setTitle(event.getName().getText());
        }

    @Override
    public void displayError() {
        //TODO make error displaying fragment
    }
}
