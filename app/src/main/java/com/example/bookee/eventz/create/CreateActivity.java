package com.example.bookee.eventz.create;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.bookee.eventz.EventApp;
import com.example.bookee.eventz.R;
import com.example.bookee.eventz.data.Event;
import com.example.bookee.eventz.data.EventsWebApi;
import com.example.bookee.eventz.data.RetrofitEventsRepository;
import com.example.bookee.eventz.data.RetrofitFactory;
import com.example.bookee.eventz.details.DetailsActivity;

import retrofit2.Retrofit;


public class CreateActivity extends AppCompatActivity implements MvpContract.View {
    private static final String TAG = "CreateActivity";
    private Presenter presenter;
    private AppCompatButton buttonSetDate;
    private AppCompatButton buttonSetTime;
    private ImageButton buttonSelectImage;
    private Spinner spinnerChoseCategory;
    private ImageView buttonCreateEvent;
    private Toolbar toolbar;
    private EditText eventName;
    private TextInputEditText eventDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        buttonSetDate = findViewById(R.id.set_date_button);
        buttonSetTime = findViewById(R.id.set_time_button);
        buttonSelectImage = findViewById(R.id.button_set_image);
        spinnerChoseCategory = findViewById(R.id.spinner_category_chose);
        toolbar = findViewById(R.id.toolbar);
        buttonCreateEvent = toolbar.findViewById(R.id.button_ok);
        eventName=findViewById(R.id.create_event_name);
        eventDescription=findViewById(R.id.create_event_info);
        setButtonListeners();
        setSpinnerAdapter();

        Retrofit retrofit = RetrofitFactory.buildRetrofit();
        RetrofitEventsRepository repository = new RetrofitEventsRepository(retrofit.create(EventsWebApi.class));
        MvpContract.Model model = new Model(repository);

        if (savedInstanceState == null) {
            presenter = new Presenter(model, this);
        }
    }

    private void setSpinnerAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, EventApp.getGlobalCategoryIds());
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
        buttonCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //samo presenteru javis da treba da se postuje Event
                presenter.postEvent(eventName.getText().toString(),"Pancevo",eventDescription.getText().toString(),"http://memoryboundscrapbookstore.com/wordpress/wp-content/uploads/2016/02/lets-party.png");
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
        DatePickerFragment datePicker = new DatePickerFragment();
        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Log.d(TAG, "onDateSet: "+ year+" "+month+" "+day);
                presenter.setDate(year,month,day);

            }
        };
        datePicker.setListener(listener);
        datePicker.show(getSupportFragmentManager(), "datePickerFragment");
    }

    @Override
    public void showTimeChooser() {
        TimePickerFragment timePicker = new TimePickerFragment();
        TimePickerDialog.OnTimeSetListener listener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                //presenter.setTime(hour,min);
            }
        };
        timePicker.setListener(listener);
        timePicker.show(getSupportFragmentManager(), "timePickerFragment");
    }

    @Override
    public void displayNewEvent(Event e) {
        DetailsActivity.launch(e.getId(),this);
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, CreateActivity.class);
        context.startActivity(intent);
    }
}
