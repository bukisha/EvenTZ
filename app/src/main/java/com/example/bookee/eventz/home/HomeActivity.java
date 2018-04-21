package com.example.bookee.eventz.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bookee.eventz.EventApp;
import com.example.bookee.eventz.R;
import com.example.bookee.eventz.data.Category;
import com.example.bookee.eventz.eventlist.EventListActivity;
import com.example.bookee.eventz.utils.HashFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity implements MvpContract.View {
    private static final String TAG = "HomeActivity";
    public static final String CATEGORY_ID_KEY = "categoryId";
    private MvpContract.Presenter presenter;
    private ListView listView;

    private static ArrayList<Category> initialCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = findViewById(R.id.category_list);

        HashMap<String, String> hash = HashFactory.createStringToString();
        MvpContract.Model model = new Model(EventApp.getRetrofitCategoryRepository(), hash);
        if(savedInstanceState==null) {
            Log.d(TAG, "onCreate: creating home presenter");
            presenter = new Presenter(this, model);
        }
        initClickListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.populateNameList(initialCategoryList);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();
    }

    public static void launch(ArrayList<Category> list, Context context) {
        Intent intent=new Intent(context,HomeActivity.class);
         initialCategoryList=list;
        context.startActivity(intent);
    }

    public void initClickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick: item clicked is " + listView.getItemAtPosition(i).toString());
                presenter.itemClicked(listView.getItemAtPosition(i).toString());
            }
        });
    }

    @Override
    public void updateCategories(ArrayList<String> categoryNamesList) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categoryNamesList);
        listView.setAdapter(adapter);
    }

    @Override
    public void displayListOfEvents(String categoryId) {
        Intent intent = new Intent(this, EventListActivity.class);
        Log.d(TAG, "displayListOfEvents: " + categoryId);
        intent.putExtra(CATEGORY_ID_KEY, categoryId);
        startActivity(intent);
    }

    @Override
    public void displayErrorMessage(String errorMessage) {
        Toast.makeText(this, "Some error has happened " + errorMessage, Toast.LENGTH_LONG).show();
    }
}
