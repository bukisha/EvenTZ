package com.example.bookee.eventz.home;

import com.example.bookee.eventz.data.RetrofitCategoryRepository;
import com.example.bookee.eventz.data.RetrofitFactory;
import com.example.bookee.eventz.splash.CategoryWebApi;
import retrofit2.Retrofit;

class ModelFactory {
    public static MvpContract.Model create() {
        Retrofit retrofit = RetrofitFactory.buildRetrofit();
        RetrofitCategoryRepository repository = new RetrofitCategoryRepository(retrofit.create(CategoryWebApi.class));
        return new Model(repository);
    }
}
