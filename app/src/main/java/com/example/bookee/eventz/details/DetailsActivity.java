package com.example.bookee.eventz.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.bookee.eventz.EventApp;
import com.example.bookee.eventz.R;
import com.example.bookee.eventz.data.RetrofitEventsRepository;

public class DetailsActivity extends AppCompatActivity implements MvpContract.View {
    private static final String TAG = "DetailsActivity";
    private static final String EXTRA_EVENT_ID = "eventId";
    private Presenter presenter;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private NestedScrollView nestedScrollView;
    private TextView eventDate;
    private TextView eventName;
    private TextView eventDescription;
    private ImageView eventLogo;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        initUI();
        RetrofitEventsRepository repository = EventApp.getRetrofitEventsRepository();
        Model model = new Model(repository);
        if (savedInstanceState == null) {
            presenter = new Presenter(model, this);
        }
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

        collapsingToolbarLayout.setVisibility(View.INVISIBLE);
        nestedScrollView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String idOfEvent = getIntent().getStringExtra(EXTRA_EVENT_ID);
        presenter.attachView(this);
        presenter.fetchEventForId(idOfEvent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();
    }

    public static void launch(String eventId, Context context) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(EXTRA_EVENT_ID, eventId);
        context.startActivity(intent);
    }

    @Override
    public void displayEvent(String title, String name, String date, String description, String logoUrl) {
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
    public void displayError() {
        //TODO make error displaying fragment
    }
}
