package com.example.bookee.eventz.eventlist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bookee.eventz.EventApp;
import com.example.bookee.eventz.R;
import com.example.bookee.eventz.details.DetailsActivity;
import com.example.bookee.eventz.home.HomeActivity;
import com.example.bookee.eventz.utils.HashFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class EventListActivity extends AppCompatActivity implements MvpContract.View {
    private static final String TAG = "EventListActivity";
    private ListView listView;
    private MvpContract.Presenter presenter;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_event_list);
        listView = findViewById(R.id.event_list);
        context=this;
        HashMap<String,String> hashMap= HashFactory.createStringToString();
        MvpContract.Model model = new Model(EventApp.getRetrofitEventsRepository(),hashMap);
        presenter = new Presenter(this, model);

        initOnClickListener();
    }

    private void initOnClickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String eventName= (String) adapterView.getItemAtPosition(i);
                DetailsActivity.launch(presenter.getIdOfClickedItem(eventName),context);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.fetchEventsForCategory(getIntent().getStringExtra(HomeActivity.CATEGORY_ID_KEY));
    }

    @Override
    public void populateEventListActivity(ArrayList<String> eventNames) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eventNames);
        listView.setAdapter(adapter);
    }

    @Override
    public void displayError() {
        Toast.makeText(this, "error happened", Toast.LENGTH_LONG).show();
    }
}
