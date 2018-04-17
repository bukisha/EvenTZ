package com.example.bookee.eventz.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.example.bookee.eventz.EventApp;
import com.example.bookee.eventz.R;
import com.example.bookee.eventz.data.Event;
import com.example.bookee.eventz.data.RetrofitEventsRepository;

public class DetailsActivity extends AppCompatActivity implements MvpContract.View {

    private Presenter presenter;
    private static String idOfEvent;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        textView = findViewById(R.id.eventName);
        RetrofitEventsRepository repository = EventApp.getRetrofitEventsRepository();
        Model model = new Model(repository);
        presenter = new Presenter(model, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        textView.setText(idOfEvent);
        presenter.fetchEventForId(idOfEvent);
    }

    public static void launch(String eventId, Context context) {
        Intent intent = new Intent(context, DetailsActivity.class);
        idOfEvent = eventId;
        context.startActivity(intent);
    }

    @Override
    public void displayEvent(Event event) {
        textView.setText(event.getStart().getTimezone());
        }

    @Override
    public void displayError() {
        //TODO make errorDisplaying fragment
    }
}
