package com.example.bookee.eventz.create;

import android.util.Log;
import com.example.bookee.eventz.data.callbacks.PostEventCallback;
import com.example.bookee.eventz.data.pojos.Category;
import java.io.File;
import java.util.ArrayList;

class Presenter implements MvpContract.Presenter {
    private static final String TAG = "Presenter";
    private MvpContract.Model model;
    private MvpContract.View view;

    public Presenter(MvpContract.Model model, MvpContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void setName(String name) {
        model.setName(name);
    }

    @Override
    public void setDescription(String description) {
        model.setDescription(description);
    }

    @Override
    public void setCategory(String categoryName) {
        model.setCategoryId(categoryName);
    }

    @Override
    public void setCurrency(String currency) {
        model.setCurrency(currency);
    }

    @Override
    public void setLogo(File imageFile) {
        model.setLogo(imageFile);
    }

    @Override
    public void postEvent() {
        prepareEventDateAndTime();
        setAdditionalEventProperties();
        PostEventCallback callback = new PostEventCallback() {
            @Override
            public void onSuccess(String eventId) {
                Log.d(TAG, "onSuccess: before creating tickets");
                view.displayNewEvent(eventId);
            }

            @Override
            public void onFailure(Throwable t) {
                if (notViewExists()) return;
                view.displayError();
            }
        };
        model.postEvent(callback);
    }

//    private void uploadEventLogo() {
//        model.uploadLogo(currentImageFile, new EndUploadImageCallback() {
//            @Override
//            public void onSuccess(Logo logo) {
//                currentEvent.setLogoId(logo.getId());
//                currentWrapper.setEvent(currentEvent);
//                PostEventCallback callback = new PostEventCallback() {
//                    @Override
//                    public void onSuccess(String eventId) {
//                        Log.d(TAG, "onSuccess: before creating tickets");
//                        view.displayNewEvent(eventId);
//                    }
//
//                    @Override
//                    public void onFailure(Throwable t) {
//                        if (notViewExists()) return;
//                        view.displayError();
//                    }
//                };
//                model.postEvent(currentWrapper, callback);
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                //TODO inform view(user) that image did not upload
//            }
//        });
//    }

    private void setAdditionalEventProperties() {
        model.setAdditionalEventProperties();
    }

    private void prepareEventDateAndTime() {
        model.prepareEventDateAndTime();
    }

    @Override
    public void attachView(MvpContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void startDateChooser() {
        if (notViewExists()) return;
        view.showDateChooser();
    }

    @Override
    public void startTimeChooser() {
        if (notViewExists()) return;
        view.showTimeChooser();
    }

    @Override
    public void startImageChooser() {
        if (notViewExists()) return;
        view.pickImage();
    }

    @Override
    public void setTime(int hour, int min) {
        model.setTime(hour, min);
    }

    @Override
    public void setDate(int year, int month, int day) {
        model.setDate(year, month, day);
    }

    private boolean notViewExists() {
        return this.view == null;
    }

    public void setHashMapWithShortNames(ArrayList<Category> categories) {
        model.setHashMapWithShortNames(categories);
    }
}
