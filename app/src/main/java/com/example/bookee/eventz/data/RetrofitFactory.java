package com.example.bookee.eventz.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitFactory {

    private static final String BASE_URL="https://www.eventbriteapi.com/v3/";
    private static final String API_OAUTH_TOKEN="53SXZPEGKDPUHUDW26DP";

    static Retrofit buildRetrofit() {
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }

    static String getAuthToken() {
        return API_OAUTH_TOKEN;
    }
}
