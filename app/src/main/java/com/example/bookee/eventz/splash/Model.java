package com.example.bookee.eventz.splash;

import com.example.bookee.eventz.callbacks.FetchCategoriesCallback;
import com.example.bookee.eventz.callbacks.FetchCategoryNamesCallback;
import com.example.bookee.eventz.data.Category;
import com.example.bookee.eventz.data.EventsRepository;
import java.util.ArrayList;
import java.util.List;


public class Model implements MvpContract.Model {
    private EventsRepository repository;

    Model() {
        repository=new EventsRepository();
    }

    @Override
    public void fetchInitialCategories(final FetchCategoryNamesCallback callback) {
           repository.fetchCategories(new FetchCategoriesCallback() {
               @Override
               public void onSuccess(List<Category> list) {
                   callback.onSuccess(extractCategoryNames(list));
               }

               @Override
               public void onFailure(Throwable t) {
                    callback.onFailure(t);
               }
           });
    }

    private ArrayList<String> extractCategoryNames(List<Category> list) {
        ArrayList<String> listOfNames = new ArrayList<>();
        for (Category c: list) {
            listOfNames.add( c.getName());
        }
        return listOfNames;
    }


}
