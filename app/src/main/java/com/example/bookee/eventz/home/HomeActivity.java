package com.example.bookee.eventz.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.bookee.eventz.EventApp;
import com.example.bookee.eventz.R;
import com.example.bookee.eventz.create.CreateActivity;
import com.example.bookee.eventz.data.Category;
import com.example.bookee.eventz.eventlist.EventListActivity;
import com.example.bookee.eventz.utils.HashFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity implements MvpContract.View {
    private static final String TAG = "HomeActivity";
    public static final String CATEGORY_ID_KEY = "categoryId";
    private static final String EXTRA_CATEGORY_LIST ="categoryList" ;
    private MvpContract.Presenter presenter;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;
    //private static ArrayList<Category> initialCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        floatingActionButton=findViewById(R.id.floating_action_button);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.floatingActionButtonClick();
            }
        });

        recyclerView = findViewById(R.id.category_recycler_list);
        setupRecyclerView(recyclerView);
        HashMap<String, String> hash = HashFactory.createStringToString();
        MvpContract.Model model = new Model(EventApp.getRetrofitCategoryRepository(), hash);
        if (savedInstanceState == null) {
            Log.d(TAG, "onCreate: creating home presenter");
            presenter = new Presenter(this, model);
        }
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
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
        //Postoji ovaj nacin da prebacim listu kategorija iz splasha u homeActivity i nacin kod kojeg imam staticko polje(gore je zakomentarisano)
        // u kojoj sacuvam listu cim je dobijem kroz launch(List l,context c) metodu
        //ovde je problem sto imam uncheckedCast warning a ako bih koristio staticko polje to bi bila losa praksa bar sam tako procitao na netu,da li postoji 3 nacin? :D
        ArrayList<Category> initialCategoryList= (ArrayList<Category>) getIntent().getSerializableExtra(EXTRA_CATEGORY_LIST);
        presenter.populateNameList(initialCategoryList);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();
    }

    public static void launch(ArrayList<Category> list, Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra(EXTRA_CATEGORY_LIST,list);
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
        Intent intent = new Intent(this, EventListActivity.class);
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
