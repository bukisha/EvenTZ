package com.example.bookee.eventz.home;

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
import com.example.bookee.eventz.eventlist.EventListActivity;
import com.example.bookee.eventz.splash.SplashActivity;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements MvpContract.View {
    private static final String TAG = "HomeActivity";
    public static final String CATEGORY_ID_KEY ="categoryId";
    private MvpContract.Presenter  presenter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView=findViewById(R.id.category_list);
        MvpContract.Model model=new Model(EventApp.getRetrofitCategoryRepository());
        presenter=new Presenter(this,model);

        initClickListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.populateNameList(getIntent().getSerializableExtra(SplashActivity.LIST_OF_CATEGORIES_KEY));
        }

    public void initClickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick: item clicked is "+listView.getItemAtPosition(i).toString());
                presenter.itemClicked(listView.getItemAtPosition(i).toString());
            }
        });
    }

    @Override
    public void updateCategories(ArrayList<String> categoryNamesList) {
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,categoryNamesList);
        listView.setAdapter(adapter);
    }

    @Override
    public void displayListOfEvents(String categoryId) {
        Intent intent=new Intent(this,EventListActivity.class);
        Log.d(TAG, "displayListOfEvents: "+categoryId);
        intent.putExtra(CATEGORY_ID_KEY,categoryId);
        startActivity(intent);
    }

    @Override
    public void displayErrorMessage(String errorMessage) {
        Toast.makeText(this, "Some error has happened "+errorMessage, Toast.LENGTH_LONG).show();
    }
}
