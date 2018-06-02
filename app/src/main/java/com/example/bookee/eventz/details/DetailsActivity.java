package com.example.bookee.eventz.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.bookee.eventz.R;
import com.example.bookee.eventz.data.Event;
import com.example.bookee.eventz.data.EventsWebApi;
import com.example.bookee.eventz.data.RetrofitEventsRepository;
import com.example.bookee.eventz.data.RetrofitFactory;

import retrofit2.Retrofit;

public class DetailsActivity extends AppCompatActivity implements MvpContract.View {
    private static final String TAG = "DetailsActivity";
    public static final String EXTRA_EVENT_ID = "eventId";
    public static final String CHECKED_EVENT_EXTRA = "checkedExtraEvent";
    public static final String EXTRA_EVENT_NAME ="eventName" ;
    private Presenter presenter;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private NestedScrollView nestedScrollView;
    private TextView eventDate;
    private TextView eventName;
    private TextView eventDescription;
    private ImageView eventLogo;
    private ProgressBar progressBar;
    private ImageButton buttonFollow;

    private FollowEventBroadcastReceiver dateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: starting");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        initUI();
        Retrofit retrofit = RetrofitFactory.buildRetrofit();
        RetrofitEventsRepository repository = new RetrofitEventsRepository(retrofit.create(EventsWebApi.class));
        MvpContract.Model model = new Model(repository);
        if (savedInstanceState == null) {
            presenter = new Presenter(model, this);
        }

       dateReceiver = new FollowEventBroadcastReceiver();


        //registerReceiver(dateReceiver, new IntentFilter("com.example.bookee.eventz.details"));
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
       //unregisterReceiver(dateReceiver);
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

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        presenter.attachView(this);
        String idOfEvent = getIntent().getStringExtra(EXTRA_EVENT_ID);
        Log.d(TAG, "onResume: fetching Event with id " + idOfEvent);
        presenter.fetchEventForId(idOfEvent);

        //registerReceiver(dateReceiver,new IntentFilter("com.example.bookee.eventz.details"));
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
        presenter.detachView();
        // unregisterReceiver(dateReceiver);
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
    public void setFollowUncheck() {
        buttonFollow.setImageResource(R.drawable.ic_follow);
    }

    @Override
    public void setFollowChecked(Event checkedEvent) {
        buttonFollow.setImageResource(R.drawable.ic_follow_checked);
        Intent serviceIntent = new Intent(this, FollowEventService.class);
        serviceIntent.putExtra(CHECKED_EVENT_EXTRA,checkedEvent);
        startService(serviceIntent);
    }

    @Override
    public void displayError() {
        //TODO make error displaying fragment
    }
}
