package com.example.bookee.eventz.eventlist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bookee.eventz.EventApp;
import com.example.bookee.eventz.R;
import com.example.bookee.eventz.data.Event;
import com.example.bookee.eventz.details.DetailsActivity;
import com.example.bookee.eventz.home.HomeActivity;
import com.example.bookee.eventz.utils.HashFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class EventListActivity extends AppCompatActivity implements MvpContract.View {
    private static final String TAG = "EventListActivity";
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MvpContract.Presenter presenter;
    private Context context;
    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_event_list);
        context = this;
        recyclerView = findViewById(R.id.events_recycler_list);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        setupRecyclerView(recyclerView);

        HashMap<String, String> hashMap = HashFactory.createStringToString();
        MvpContract.Model model = new Model(EventApp.getRetrofitEventsRepository(), hashMap);

        if (savedInstanceState == null) {
            presenter = new Presenter(this, model);
        }
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewOnItemClickListener = new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(String eventName) {
                DetailsActivity.launch(presenter.getIdOfClickedItem(eventName), context);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.fetchEventsForCategory(getIntent().getStringExtra(HomeActivity.CATEGORY_ID_KEY));
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();
    }

    @Override
    public void populateEventListActivity(ArrayList<Event> eventNames) {
        EventListCardAdapter eventListCardAdapter = new EventListCardAdapter(eventNames, context, recyclerViewOnItemClickListener);
        recyclerView.setAdapter(eventListCardAdapter);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void displayError() {
        Toast.makeText(this, "error happened", Toast.LENGTH_LONG).show();
    }
}
