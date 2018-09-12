package com.example.bookee.eventz.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.bookee.eventz.R;
import com.example.bookee.eventz.create.CreateActivity;
import com.example.bookee.eventz.data.pojos.Category;
import com.example.bookee.eventz.events.EventsListActivity;
import com.example.bookee.eventz.followed.FollowedEventsActivity;
import com.example.bookee.eventz.data.PreferencesCategoriesRepository;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements MvpContract.View {
    private static final String TAG = "HomeActivity";
    public static final String CATEGORY_ID_KEY = "categoryId";
    public static final String EXTRA_CATEGORY_LIST = "categoryList";
    private MvpContract.Presenter presenter;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;
    private static ArrayList<Category> initialCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        floatingActionButton = findViewById(R.id.floating_action_button);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.floatingActionButtonClick();
            }
        });
        floatingActionButton.setVisibility(View.VISIBLE);

        recyclerView = findViewById(R.id.category_recycler_list);
        setupRecyclerView(recyclerView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Log.d(TAG, "onCreate: creating home presenter");
        presenter = new Presenter(this, ModelFactory.create());
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewOnItemClickListener = new RecyclerViewOnItemClickListener() {
            @Override
            public void itemClicked(String categoryName) {
                presenter.itemClicked(categoryName);
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
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
    public void launchFollowedEventsActivity() {
        Intent intent = new Intent(this, FollowedEventsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
        presenter.attachView(this);
        //initialCategoryList= GlobalDataManipulator.getGlobalCategoryList(getSharedPreferences(getResources().getString(R.string.shared_preferences),MODE_PRIVATE));
        initialCategoryList = new PreferencesCategoriesRepository(this.getSharedPreferences(PreferencesCategoriesRepository.GLOBAL_CATEGORY_LIST, MODE_PRIVATE)).getGlobalCategoryList();
        presenter.populateNameList(initialCategoryList);
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
        presenter.detachView();
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void updateCategories(ArrayList<Category> categoryList) {
        CategoryCardsAdapter adapter = new CategoryCardsAdapter(categoryList, this);
        adapter.setOnClickListener(recyclerViewOnItemClickListener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void displayListOfEvents(String categoryId) {
        Intent intent = new Intent(this, EventsListActivity.class);
        Log.d(TAG, "displayListOfEvents: " + categoryId);
        intent.putExtra(CATEGORY_ID_KEY, categoryId);
        startActivity(intent);
    }

    @Override
    public void launchCreateEventActivity() {
        CreateActivity.launch(this);
    }

    @Override
    public void displayErrorMessage(String errorMessage) {
        Toast.makeText(this, "Some error has happened " + errorMessage, Toast.LENGTH_LONG).show();
    }
}
