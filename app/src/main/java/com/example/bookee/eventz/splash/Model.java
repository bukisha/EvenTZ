package com.example.bookee.eventz.splash;

import android.content.Context;

import com.example.bookee.eventz.data.pojos.Category;
import com.example.bookee.eventz.data.RetrofitCategoryRepository;
import com.example.bookee.eventz.data.callbacks.FetchCategoriesCallback;
import com.example.bookee.eventz.util.PreferencesCategoriesRepository;

import java.util.ArrayList;

class Model implements MvpContract.Model {
    private RetrofitCategoryRepository repository;
    private MvpContract.FetchCategoriesCallback callbackFromPresenter;

    Model(RetrofitCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void fetchInitialCategories(MvpContract.FetchCategoriesCallback callback, final Context context) {
        callbackFromPresenter = callback;

        FetchCategoriesCallback callbackForRepository = new FetchCategoriesCallback() {
            @Override
            public void onSuccess(ArrayList<Category> list) {
                PreferencesCategoriesRepository preferencesCategoriesRepository =
                        new PreferencesCategoriesRepository(context.getSharedPreferences(PreferencesCategoriesRepository.GLOBAL_CATEGORY_LIST, Context.MODE_PRIVATE));
                preferencesCategoriesRepository.storeGlobalCategoryList(list);
                preferencesCategoriesRepository.storeCategoriesShortIds(getCategoryIdShortNames(list));

                callbackFromPresenter.onSuccess(list);
            }

            @Override
            public void onFailure(Throwable t) {
                callbackFromPresenter.onFailure(t);
            }
        };
        repository.fetchCategories(callbackForRepository);
    }

    private ArrayList<String> getCategoryIdShortNames(ArrayList<Category> list) {
        ArrayList<String> CategoryIds=new ArrayList<>();
        for(Category c : list) {
            CategoryIds.add(c.getShortName());
        }
        return CategoryIds;
    }
}
