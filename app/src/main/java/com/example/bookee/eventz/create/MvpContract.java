package com.example.bookee.eventz.create;

import com.example.bookee.eventz.data.callbacks.EndUploadImageCallback;
import com.example.bookee.eventz.data.callbacks.PostEventCallback;
import com.example.bookee.eventz.data.pojos.Category;
import java.io.File;
import java.util.ArrayList;

interface MvpContract {
    interface Model {
        /**
         * Posts event with current parameters to server
         * @param callback callback from model so that result can be passed back to it
         */
        void postEvent(PostEventCallback callback);

        /**
         * uplaods logo of the event to image upload service
         * @param currentImageFile current image file that will serve as logo
         * @param callback callback from model so that result of image upload can be handled
         */
        void uploadLogo(File currentImageFile,EndUploadImageCallback callback);

        /**
         * sets name of current event
         * @param name name to set as current event name
         */
        void setName(String name);

        /**
         * sets current event description
         * @param description current event description
         */
        void setDescription(String description);

        /**
         * set current image that will be logo of the current event
         * @param imageFile image file that will serve as logo
         */
        void setLogo(File imageFile);

        /**
         *  gets string representation of number which serves as event category id
         * @param categoryShortName short name of category
         * @return string representation of integer which represents event category id
         */
        String getCategoryId(String categoryShortName);

        /**
         * sets additional event properties before posting
         */
        void setAdditionalEventProperties();

        /**
         * formats start date and time for current event that will be created
         */
        void prepareEventDateAndTime();

        /**
         * sets current event start time in hours and minutes
         * @param hour start hour
         * @param min start minute
         */
        void setTime(int hour, int min);

        /**
         * sets current event Data
         * @param year year of event
         * @param month month of event
         * @param day day of event
         */
        void setDate(int year, int month, int day);

        /**
         * initializes hashMap with pairs of (categoryId,category short name)
         * @param categories list of all categories that api recognizes atm
         */
        void setHashMapWithShortNames(ArrayList<Category> categories);

        /**
         * sets current event category id
         * @param categoryId string representation of integer value which is category id
         */
        void setCategoryId(String categoryId);
    }

    interface Presenter {
        /**
         * signals to model that it should post current event to api
         */
        void postEvent();

        /**
         * attaches parent view to instance of presenter
         * @param view parent view
         */
        void attachView(View view);

        /**
         * detaches parent view from presenter when view is destroyed to prevent leakage of memory(leak
         * of activity since it serves as view)
         */
        void detachView();

        /**
         * starts DateChooserFragment
         */
        void startDateChooser();

        /**
         * starts TimeChooserFragment
         */
        void startTimeChooser();

        /**
         * starts chooser dialog with  list of apps that can select images from local file system
         */
        void startImageChooser();

        /**
         * pass selected time to model so that it can initialize current event with it
         * @param hour selected start hour of event
         * @param min selected start minute of event
         */
        void setTime(int hour, int min);

        /**
         * passes selected start date to model
         * @param year year of event
         * @param month month of event
         * @param day day of event
         */
        void setDate(int year, int month, int day);

        /**
         * passes name of event to model
         * @param name name of event
         */
        void setName(String name);

        /**
         * passes description of event to model
         * @param description description of event
         */
        void setDescription(String description);

        /**
         * passes category of event to model
         * @param category category of event
         */
        void setCategory(String category);

        /**
         * passes image file which will serve as event logo to model
         * @param imageFile image file
         */
        void setLogo(File imageFile);

        /**
         *  passes list of all categories that api recognizes so that model can initialize hash map with (categoryId,category short name)
         * @param categories list of all api categories
         */
        void setHashMapWithShortNames(ArrayList<Category> categories);
    }

    interface View {
        /**
         * show DatePickerFragment
         */
        void showDateChooser();

        /**
         * show TimePickerFragment
         */
        void showTimeChooser();

        /**
         * displays newly created event
         * @param eventId id of newly created event
         */
        void displayNewEvent(String eventId);

        /**
         * displays all sorts of errors atm
         */
        void displayError();

        /**
         * launches dialog for picking app for image selection
         */
        void pickImage();
    }
}
