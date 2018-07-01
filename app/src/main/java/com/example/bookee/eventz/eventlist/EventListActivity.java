package com.example.bookee.eventz.eventlist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bookee.eventz.R;
import com.example.bookee.eventz.data.Event;
import com.example.bookee.eventz.data.EventsWebApi;
import com.example.bookee.eventz.data.RetrofitEventsRepository;
import com.example.bookee.eventz.data.RetrofitFactory;
import com.example.bookee.eventz.details.DetailsActivity;
import com.example.bookee.eventz.home.HomeActivity;

import java.util.ArrayList;

import retrofit2.Retrofit;

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Retrofit retrofit = RetrofitFactory.buildRetrofit();
        RetrofitEventsRepository repository = new RetrofitEventsRepository(retrofit.create(EventsWebApi.class));
        MvpContract.Model model = new Model(repository);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
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
        EventListCardAdapter eventListCardAdapter = new EventListCardAdapter(eventNames, context, progressBar, recyclerView);
        eventListCardAdapter.setOnClickLister(recyclerViewOnItemClickListener);
        recyclerView.setAdapter(eventListCardAdapter);
    }

    @Override
    public void displayError() {
        Toast.makeText(this, "error happened", Toast.LENGTH_LONG).show();
    }
}
