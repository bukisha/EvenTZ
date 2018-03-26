package com.example.bookee.eventz.home;

import java.io.Serializable;
import java.util.ArrayList;

public class Presenter implements MvpContract.Presenter {
    private static final String TAG = "Presenter";
    private MvpContract.View view;
    private Model model;

    Presenter(MvpContract.View view) {
        this.view = view;
        model=new Model();
    }

    @Override
    public void populateNameList(Serializable serializableExtra) {
        ArrayList<String> categoryNames= (ArrayList<String>) serializableExtra;
        view.updateCategories(categoryNames);
    }


    private boolean getView() {
        return view != null;
    }
}
