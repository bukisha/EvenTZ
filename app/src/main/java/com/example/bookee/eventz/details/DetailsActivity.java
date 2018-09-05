package com.example.bookee.eventz.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.bookee.eventz.R;
import com.example.bookee.eventz.data.pojos.Event;
import com.example.bookee.eventz.followed.FollowedEventsActivity;


public class DetailsActivity extends AppCompatActivity implements MvpContract.View {
    private static final String TAG = "DetailsActivity";
    public static final String EXTRA_EVENT_ID = "eventId";
    private Presenter presenter;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private NestedScrollView nestedScrollView;
    private TextView eventDate;
    private TextView eventName;
    private TextView eventDescription;
    private ImageView eventLogo;
    private ProgressBar progressBar;
    private ImageButton buttonFollow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: starting");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        initUI();

            presenter = new Presenter(ModelFactory.create(this), this);

    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        presenter.closeDataSource();
        super.onDestroy();
    }

    private void initUI() {
        Log.d(TAG, "initUI: creating UI");
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        nestedScrollView = findViewById(R.id.nested_scroll_view);
        eventName = findViewById(R.id.event_name);
        eventDate = findViewById(R.id.event_date);
        eventDescription = findViewById(R.id.event_description);
        eventLogo = findViewById(R.id.event_logo);
        progressBar = findViewById(R.id.progress_bar);
        buttonFollow = findViewById(R.id.follow_button);
        collapsingToolbarLayout.setVisibility(View.INVISIBLE);
        nestedScrollView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        buttonFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.followClicked();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void setupFollowButton(boolean isFollowed) {
        Log.d(TAG, "setupFollowButton: " + isFollowed);
        if (isFollowed) buttonFollow.setImageResource(R.drawable.ic_follow_checked);
        presenter.setFollowed(isFollowed);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.followed_events:
                presenter.launchFollowedEventsActivity();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        presenter.attachView(this);
        String idOfEvent = getIntent().getStringExtra(EXTRA_EVENT_ID);
        Log.d(TAG, "onResume: fetching Event with id " + idOfEvent);
        presenter.fetchEventForId(idOfEvent);
        presenter.checkFollowButton(idOfEvent);
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
        presenter.detachView();
    }

    public static void launch(String eventId, Context context) {
        Log.d(TAG, "launch: called");
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(EXTRA_EVENT_ID, eventId);
        context.startActivity(intent);
    }

    @Override
    public void displayEvent(String title, String name, String date, String description, String logoUrl) {
        Log.d(TAG, "displayEvent: called");
        collapsingToolbarLayout.setTitle(title);
        eventDate.setText(date);
        eventName.setText(name);
        eventDescription.setText(description);

        Glide.with(this).load(logoUrl).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {

                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                collapsingToolbarLayout.setVisibility(View.VISIBLE);
                nestedScrollView.setVisibility(View.VISIBLE);
                return false;
            }
        }).fitCenter().into(eventLogo);
    }

    @Override
    public void displayEventWithoutLogo(String title, String name, String date, String description) {
        collapsingToolbarLayout.setTitle(title);
        eventDate.setText(date);
        eventName.setText(name);
        eventDescription.setText(description);

        Glide.with(this).load(R.drawable.party).listener(new RequestListener<Integer, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, Integer model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, Integer model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                collapsingToolbarLayout.setVisibility(View.VISIBLE);
                nestedScrollView.setVisibility(View.VISIBLE);

                return false;
            }
        }).fitCenter().into(eventLogo);
    }

    @Override
    public void setFollowUncheck(Event uncheckedEvent) {
        buttonFollow.setImageResource(R.drawable.ic_follow_unchecked);
       // presenter.removeRowWithId(uncheckedEvent.getId());
        presenter.stopFollowingEvent(uncheckedEvent,this.getApplicationContext());
    }

    @Override
    public void setFollowChecked(Event checkedEvent) {
        buttonFollow.setImageResource(R.drawable.ic_follow_checked);
        presenter.startFollowingEvent(checkedEvent,this.getApplicationContext());
    }

    @Override
    public void launchFollowedEventActivity() {
        Intent startFollowedEvents = new Intent(this, FollowedEventsActivity.class);
        startActivity(startFollowedEvents);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayError() {
        //TODO make error displaying fragment
    }
}
