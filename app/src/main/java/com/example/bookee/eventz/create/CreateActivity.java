package com.example.bookee.eventz.create;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import com.example.bookee.eventz.data.pojos.Category;
import com.example.bookee.eventz.data.pojos.Event;
import com.example.bookee.eventz.details.DetailsActivity;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Retrofit;

public class CreateActivity extends AppCompatActivity implements MvpContract.View {
    private static final String TAG = "CreateActivity";
    private static final String EXTRA_CATEGORIES = "nameToIdHash";
    private static final String CURRENCY_EUR = "EUR";
    private static final CharSequence CHOOSER_TITTLE = " Choose App";
    private static final String TOAST_NO_IMAGE_APP = "No app for image viewing";
    private static final int PICK_IMAGE_REQUEST_CODE = 13;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 23;
    private Presenter presenter;
    private AppCompatButton buttonSetDate;
    private AppCompatButton buttonSetTime;
    private ImageButton buttonSelectImage;
    private Spinner spinnerChoseCategory;
    private ImageView buttonCreateEvent;
    private EditText eventName;
    private TextInputEditText eventDescription;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        buttonSetDate = findViewById(R.id.set_date_button);
        buttonSetTime = findViewById(R.id.set_time_button);
        buttonSelectImage = findViewById(R.id.button_set_image);
        spinnerChoseCategory = findViewById(R.id.spinner_category_chose);
        Toolbar toolbar = findViewById(R.id.toolbar);
        buttonCreateEvent = toolbar.findViewById(R.id.button_ok);
        eventName = findViewById(R.id.create_event_name);
        eventDescription = findViewById(R.id.create_event_info);

        Retrofit retrofit = RetrofitFactory.buildRetrofit();
        RetrofitEventsRepository repository = new RetrofitEventsRepository(retrofit.create(EventsWebApi.class));
        MvpContract.Model model = new Model(repository);

        presenter = new Presenter(model, this);
        ArrayList<Category> categories = (ArrayList<Category>) getIntent().getSerializableExtra(EXTRA_CATEGORIES);
        if (categories != null) {
            presenter.setHashMapWithShortNames(categories);
        }
        setListeners();
        setSpinnerAdapter();
    }

    private void setSpinnerAdapter() {
        Log.d(TAG, "setSpinnerAdapter: ");
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
                requestPermision();
            }
        });
        buttonCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.setName(eventName.getText().toString());
                presenter.setDescription(eventDescription.getText().toString());
                //Hardcoded currency because i am to lazy to extract currency code from location atm :D
                presenter.setCurrency(CURRENCY_EUR);
                presenter.postEvent();
            }
        });
        spinnerChoseCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.setCategory(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Nothing happens because List of categories  has static data and even if it does change it will not change here
            }
        });
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
        presenter.detachView();
    }

    @Override
    public void showCreatedEvent(Event event) {
        DetailsActivity.launch(event.getId(), this);
    }

    @Override
    public void showDateChooser() {
        DatePickerFragment datePicker = new DatePickerFragment();
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Log.d(TAG, "onDateSet: " + year + " " + month + " " + day);
                presenter.setDate(year, month, day);

            }
        };
        datePicker.setListener(listener);
        datePicker.show(getSupportFragmentManager(), "datePickerFragment");
    }

    @Override
    public void showTimeChooser() {
        TimePickerFragment timePicker = new TimePickerFragment();
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                presenter.setTime(hour, min);
            }
        };
        timePicker.setListener(listener);
        timePicker.show(getSupportFragmentManager(), "timePickerFragment");
    }

    @Override
    public void displayNewEvent(String eventId) {
        Log.d(TAG, "displayNewEvent: " + eventId);
        //15.07.2018.
        //this is implemented just for testing,atm event is created as draft and is not live on server,so we can not acquire event object via its id
        //Toast.makeText(this, "id is " + eventId, Toast.LENGTH_LONG).show();
        DetailsActivity.launch(eventId, this);
    }

    @Override
    public void displayError() {
        //TODO display some kind of error message
    }

    @Override
    public void pickImage() {
        Log.d(TAG, "pickImage: ");
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        Intent pickerIntent = Intent.createChooser(intent, CHOOSER_TITTLE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(pickerIntent, PICK_IMAGE_REQUEST_CODE);
        } else {
            Toast.makeText(this, TOAST_NO_IMAGE_APP, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                Uri imgUri = data.getData();
                try {
                    displayNewImage(imgUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Toast.makeText(this, uri.getPath(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error while choosing img", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void displayNewImage(Uri imgUri) throws IOException {
        Bitmap imgBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imgUri);
        buttonSelectImage.setImageBitmap(imgBitmap);
    }


    public static void launch(Context context, ArrayList<Category> categories) {
        Intent intent = new Intent(context, CreateActivity.class);
        intent.putExtra(EXTRA_CATEGORIES, categories);
        context.startActivity(intent);
    }

    //quick and dirty runtime permission request and procession
    public void requestPermision() {
        Log.d(TAG, "requestPermision: ");
        //Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        } else {
            // Permission has already been granted
            presenter.startImageChooser();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: ");
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    presenter.startImageChooser();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    finish();
                }

            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }
}
