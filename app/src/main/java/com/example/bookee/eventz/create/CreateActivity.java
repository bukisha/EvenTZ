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
import android.widget.Toast;

import com.example.bookee.eventz.EventApp;
import com.example.bookee.eventz.R;
import com.example.bookee.eventz.data.EventsWebApi;
import com.example.bookee.eventz.data.RetrofitEventsRepository;
import com.example.bookee.eventz.data.RetrofitFactory;
import com.example.bookee.eventz.data.pojos.Event;
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
        setListeners();
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

    private void setListeners() {
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
                presenter.setName(eventName.getText().toString());
                presenter.setDescription(eventDescription.getText().toString());
                presenter.setCurrency("EUR");
                presenter.setCategory("104");
                presenter.postEvent();
            }
        });
//        spinnerChoseCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                //TODO check this it might be error prone
//                presenter.setCategory(adapterView.getItemAtPosition(i).toString());
//            }
//        });
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
        DetailsActivity.launch(event.getId(),this);
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
                presenter.setTime(hour,min);
            }
        };
        timePicker.setListener(listener);
        timePicker.show(getSupportFragmentManager(), "timePickerFragment");
    }

    @Override
    public void displayNewEvent(Event e) {
        Toast.makeText(this, "id is "+e.getId(), Toast.LENGTH_LONG).show();
        //DetailsActivity.launch(e.getId(),this);
    }

    @Override
    public void displayError() {
        //TODO display some kind of error message
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, CreateActivity.class);
        context.startActivity(intent);
    }
}
