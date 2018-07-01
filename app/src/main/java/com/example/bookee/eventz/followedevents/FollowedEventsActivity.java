package com.example.bookee.eventz.followedevents;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.bookee.eventz.R;
import com.example.bookee.eventz.details.DetailsActivity;
import com.example.bookee.eventz.details.EventsDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class FollowedEventsActivity extends AppCompatActivity {
    private ListView listView;
    private ProgressBar progressBar;
    private EventsDatabaseHelper databaseHelper;
    private GetEventsTask getEventsTask;

    private class GetEventsTask extends AsyncTask<EventsDatabaseHelper,Void,List<String>> {

        @Override
        protected List<String> doInBackground(EventsDatabaseHelper... eventsDatabaseHelpers) {
            List<String> retList = null;
            for (EventsDatabaseHelper e:eventsDatabaseHelpers) {
                retList=prepareListOfNames(e.getAllFollowedEventsIds());
                }
            return retList;
        }

        @Override
        protected void onPostExecute(List<String> eventNames) {
            super.onPostExecute(eventNames);
            showFollowedEvents(eventNames);
        }
    }

    private void showFollowedEvents(final List<String> eventNames) {
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eventNames);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                String targetEventId=databaseHelper.getEventIdForName(eventNames.get(index));
                DetailsActivity.launch(targetEventId,adapterView.getContext());
            }
        });
        listView.setAdapter(adapter);
        listView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followed_events);
        listView = findViewById(R.id.followed_events);
        listView.setVisibility(View.INVISIBLE);
        progressBar = findViewById(R.id.progress_bar);
        databaseHelper = EventsDatabaseHelper.getInstance(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.VISIBLE);
        getEventsTask=new GetEventsTask();
        getEventsTask.execute(databaseHelper);
        }

    private List<String> prepareListOfNames(List<String> eventIds) {
        List<String> eventNames = new ArrayList<>();
        for (int i = 0; i < eventIds.size(); i++) {
            String name = databaseHelper.getEventNameForId(eventIds.get(i));
            eventNames.add(name);
        }
        return eventNames;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHelper.close();
    }
}
