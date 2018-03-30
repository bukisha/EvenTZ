package com.example.bookee.eventz.eventlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bookee.eventz.R;
import com.example.bookee.eventz.home.HomeActivity;

import java.util.ArrayList;

public class EventListActivity extends AppCompatActivity implements MvpContract.View {
    private static final String TAG = "EventListActivity";
    private ListView listView;
    private MvpContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        listView= findViewById(R.id.event_list);
        presenter=new Presenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.fetchEventsForCategory(getIntent().getStringExtra(HomeActivity.CATEGORY_ID_KEY));
    }

    @Override
    public void displayEvents(ArrayList<String> eventNames) {
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,eventNames);
        listView.setAdapter(adapter);
    }

    @Override
    public void displayError(Throwable t) {
        Toast.makeText(this, "error happened", Toast.LENGTH_LONG).show();
    }
}
