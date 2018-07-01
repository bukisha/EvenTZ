package com.example.bookee.eventz.data;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    private static final String BASE_URL = "https://www.eventbriteapi.com/v3/";
    private static final String API_OAUTH_TOKEN_ANONYMOUS = "53SXZPEGKDPUHUDW26DP";
    private static final String API_OAUTH_TOKEN_PERSONAL = "UHSWAYREEJUQBBIH3H3H";

    public static Retrofit buildRetrofit() {
        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = okhttpBuilder.addInterceptor(logger).build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(client);
        return builder.build();
    }

    static String getAuthTokenAnonymous() {
        return API_OAUTH_TOKEN_ANONYMOUS;
    }

    static String getAuthTokenPersonal() {
        return API_OAUTH_TOKEN_PERSONAL;
    }

}
