package com.example.bookee.eventz.create;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.bookee.eventz.EventApp;
import com.example.bookee.eventz.R;
import com.example.bookee.eventz.data.Event;
import com.example.bookee.eventz.data.EventsWebApi;
import com.example.bookee.eventz.data.RetrofitEventsRepository;
import com.example.bookee.eventz.data.RetrofitFactory;

import retrofit2.Retrofit;


public class CreateActivity extends AppCompatActivity implements MvpContract.View {
    private Presenter presenter;
    private AppCompatButton buttonSetDate;
    private AppCompatButton buttonSetTime;
    private ImageButton buttonSelectImage;
    private Spinner spinnerChoseCategory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        buttonSetDate= findViewById(R.id.set_date_button);
        buttonSetTime = findViewById(R.id.set_time_button);
        buttonSelectImage = findViewById(R.id.button_set_image);
        spinnerChoseCategory = findViewById(R.id.spinner_category_chose);
        setButtonListeners();
        setSpinnerAdapter();
        Retrofit retrofit= RetrofitFactory.buildRetrofit();

        RetrofitEventsRepository repository = new RetrofitEventsRepository(retrofit.create(EventsWebApi.class));
        MvpContract.Model model = new Model(repository);

        if (savedInstanceState == null) {
            presenter = new Presenter(model, this);
        }
    }

    private void setSpinnerAdapter() {
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,EventApp.getGlobalCategoryIds());
        spinnerChoseCategory.setAdapter(adapter);
    }

    private void setButtonListeners() {
        buttonSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.startDateChooser();
            }
        });
        buttonSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.startTimeChooser();
            }
        });
        buttonSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.startImageChooser();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();
    }

    @Override
    public void showCreatedEvent(Event event) {
    }

    @Override
    public void showDateChooser() {
        DatePickerFragment datePicker=new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(),"datePickerFragment");
    }

    @Override
    public void showTimeChooser() {
        TimePickerFragment timePicker=new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(),"timePickerFragment");
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, CreateActivity.class);
        context.startActivity(intent);
    }
}
