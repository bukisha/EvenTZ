package com.example.bookee.eventz.followed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bookee.eventz.R;
import com.example.bookee.eventz.details.DetailsActivity;

import java.util.List;

public class FollowedEventsActivity extends AppCompatActivity implements MvpContract.View {
    private ListView listView;
    private ProgressBar progressBar;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followed_events);
        listView = findViewById(R.id.followed_events);
        listView.setVisibility(View.INVISIBLE);
        progressBar = findViewById(R.id.progress_bar);
        presenter = new Presenter(new Model(this), this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
        progressBar.setVisibility(View.VISIBLE);
        presenter.getFollowedEventNames();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.closeDatabase();
    }

    @Override
    public void displayFollowedEvents(final List<String> eventNames) {
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eventNames);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                presenter.launchDetailsActivityForEvent(eventNames.get(index));
            }
        });
        listView.setAdapter(adapter);
        listView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void displayError() {
        Toast.makeText(this, "Error while getting data ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void launchDisplayActivity(String id) {
        DetailsActivity.launch(id,this);
    }
}
