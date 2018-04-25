package com.example.bookee.eventz.home;

import android.util.Log;

import com.example.bookee.eventz.data.Category;

import java.util.ArrayList;

class Presenter implements MvpContract.Presenter {
    private static final String TAG = "Presenter";
    private MvpContract.View view;
    private MvpContract.Model model;

    Presenter(MvpContract.View view, MvpContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void populateNameList(ArrayList<Category> serializableExtra) {
        Log.d(TAG, "populateNameList: starting");
        model.populateHash(serializableExtra);
        if(notViewExists()) return;
        view.updateCategories(serializableExtra);
    }

    private boolean notViewExists() {
        return this.view==null;
    }

    @Override
    public void itemClicked(String categoryName) {
        view.displayListOfEvents(model.getClickedCategoryId(categoryName));
    }

    @Override
    public void attachView(MvpContract.View view) {
        this.view=view;
    }

    @Override
    public void detachView() {
        this.view=null;
    }

    @Override
    public void floatingActionButtonClick() {
        view.launchCreateEventActivity();
    }

    private ArrayList<String> extractCategoryNames(ArrayList<Category> list) {
        ArrayList<String> listOfNames = new ArrayList<>();
        for (Category c : list) {
            listOfNames.add(c.getName());
        }
        return listOfNames;
    }
}
