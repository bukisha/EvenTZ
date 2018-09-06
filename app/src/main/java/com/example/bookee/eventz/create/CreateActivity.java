package com.example.bookee.eventz.create;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.bookee.eventz.R;
import com.example.bookee.eventz.data.pojos.Category;
import com.example.bookee.eventz.data.pojos.Event;
import com.example.bookee.eventz.details.DetailsActivity;
import com.example.bookee.eventz.data.PreferencesCategoriesRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CreateActivity extends AppCompatActivity implements MvpContract.View {
    private static final String TAG = "CreateActivity";
    //private static final String EXTRA_CATEGORIES = "nameToIdHash";
    private static final int PICK_IMAGE_REQUEST_CODE = 13;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 23;

    private MvpContract.Presenter presenter;
    private Button buttonSetDate;
    private Button buttonSetTime;
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

        presenter = new Presenter(ModelFactory.create(), this);
        PreferencesCategoriesRepository sharedPreferencesRepository=new PreferencesCategoriesRepository(this.getSharedPreferences(PreferencesCategoriesRepository.GLOBAL_CATEGORY_LIST,MODE_PRIVATE));
        ArrayList<Category> categories = sharedPreferencesRepository.getGlobalCategoryList();

        if (categories != null) {
            presenter.setHashMapWithShortNames(categories);
        } else {
            //todo a sta je sa else kaluzulom? Sta ako greskom ne prosledis kategorije? Sta ce se prikazati?
            //TODO show some kind of dialog that informs user that categories are not fetched,aldo this error might have been catched somewhere upstream in code so u do not have too
        }
        setListeners();
        setSpinnerAdapter(sharedPreferencesRepository);
    }

    private void setSpinnerAdapter(PreferencesCategoriesRepository preferencesCategoriesRepository) {
        Log.d(TAG, "setSpinnerAdapter: ");
//
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                preferencesCategoriesRepository.getCategoriesShortNames()
                );

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
                requestPermission();
            }
        });
        buttonCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.setName(eventName.getText().toString());
                presenter.setDescription(eventDescription.getText().toString());
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
    public void showCreatedEvent(Event event) {//todo a di ga koristis, rodjo?
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
        Intent pickerIntent = Intent.createChooser(intent, getResources().getString(R.string.create_image_chooser_tittle));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(pickerIntent, PICK_IMAGE_REQUEST_CODE);
        } else {
            Toast.makeText(this, R.string.toast_no_image_picking_app, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                Uri imgUri = data.getData();
                try {
                    if (imgUri != null) {
                        File imageFile = createFileFromUri(imgUri);
                        presenter.setLogo(imageFile);
                    }
                    displayNewImage(imgUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "Error while choosing img", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private File createFileFromUri(Uri imgUri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(this, imgUri, projection, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return new File(cursor.getString(column_index));
    }

    private void displayNewImage(Uri imgUri) throws IOException {
        Bitmap imgBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imgUri);
        buttonSelectImage.setImageBitmap(imgBitmap);
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, CreateActivity.class);
        context.startActivity(intent);
    }

    //quick and dirty runtime permission request and procession
    public void requestPermission() {
        Log.d(TAG, "requestPermission: ");
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
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    presenter.startImageChooser();
                } else {
                    // permission denied,Disable the functionality that depends on this permission.
                    finish();
                }
            }
        }
    }
}
