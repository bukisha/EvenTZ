package com.example.bookee.eventz.home;

import android.util.Log;
import com.example.bookee.eventz.data.Category;
import java.util.List;

public class Presenter implements MvpContract.Presenter {
    private static final String TAG = "Presenter";
    private MvpContract.View view;
    private Model model;

    Presenter(MvpContract.View view) {
        this.view = view;
        model=new Model();
    }

    public void fetchCategories() {
        Log.d(TAG, "fetchCategories: fetch starting");
        model.fetchCategories(new MvpContract.fetchCategoriesCallback()  {
            @Override
            public void onSuccess(List<Category> categoryList) {
                if(!getView()) return;
                    view.updateCategories(categoryList);
            }

            @Override
            public void onFailure(Throwable t) {
                if(!getView()) return;
                view.displayErrorMessage(t.toString());
            }
        });
    }

    private boolean getView() {
        return view != null;
    }
}
