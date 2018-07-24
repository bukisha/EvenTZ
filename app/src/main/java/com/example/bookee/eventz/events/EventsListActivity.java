package com.example.bookee.eventz.events;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.bookee.eventz.R;
import com.example.bookee.eventz.data.pojos.Event;
import com.example.bookee.eventz.data.EventsWebApi;
import com.example.bookee.eventz.data.RetrofitEventsRepository;
import com.example.bookee.eventz.data.RetrofitFactory;
import com.example.bookee.eventz.details.DetailsActivity;
import com.example.bookee.eventz.followed.FollowedEventsActivity;
import com.example.bookee.eventz.home.HomeActivity;
import java.util.ArrayList;
import retrofit2.Retrofit;

public class EventsListActivity extends AppCompatActivity implements MvpContract.View {
    private static final String TAG = "EventsListActivity";
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MvpContract.Presenter presenter;
    private Context context;
    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;
    private SearchView searchView;

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

        presenter = new Presenter(this, model);

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
        getMenuInflater().inflate(R.menu.main_menu_events, menu);
        MenuItem search=menu.findItem(R.id.action_search);
        searchView= (SearchView) search.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.fetchEventsForQuery(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.followed_events:
                presenter.launchFollowedEvents();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void launchFollowedActivity() {
        Intent startFollowed = new Intent(this, FollowedEventsActivity.class);
        startActivity(startFollowed);
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
    public void displayError(String searchCriteria) {
        Toast.makeText(this, searchCriteria, Toast.LENGTH_LONG).show();
    }
}
