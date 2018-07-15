package com.example.bookee.eventz.data;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    private static final String BASE_URL = "https://www.eventbriteapi.com/v3/";
    private static final String API_OAUTH_TOKEN_ANONYMOUS = "53SXZPEGKDPUHUDW26DP";
    private static final String API_OAUTH_TOKEN_PERSONAL = "UHSWAYREEJUQBBIH3H3H";

    public static Retrofit buildRetrofit() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        //logger interceptor for debugging
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        //interceptor that concatenates slash onto request url if we are doing POST request
        Interceptor modifyRequestInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request outgoingRequest = chain.request();
                HttpUrl outgoingUrl = outgoingRequest.url();
                // System.out.println("My request url" + outgoingRequest.url());

                if (outgoingRequest.method().equals("POST")) {
                    // System.out.println("++++++++++++++++++++++++++++");
                    // System.out.println("My request url " + outgoingRequest.url());
                    String nextUrl = outgoingUrl.toString();
                    nextUrl = nextUrl.concat("/");
                    HttpUrl nextURL = HttpUrl.parse(nextUrl);
                    // System.out.println("My request url NEXT " + nextURL);
                    HttpUrl.Builder urlBuilder = nextURL.newBuilder();
                    HttpUrl newUrl = urlBuilder
                            .addQueryParameter("token", getAuthTokenPersonal())
                            .build();
                    Request newRequest = outgoingRequest.newBuilder().url(newUrl).build();
                    //  System.out.println("My modified request url is " + outgoingRequest.url());
                    //  System.out.println("++++++++++++++++++++++++++++");
                    return chain.proceed(newRequest);
                } else {
                    HttpUrl newUrl = outgoingUrl.newBuilder()
                            .addQueryParameter("token", getAuthTokenPersonal())
                            .build();
                    Request newRequest = outgoingRequest.newBuilder().url(newUrl).build();
                    return chain.proceed(newRequest);
                }
            }
        };
        OkHttpClient client = okHttpBuilder
                .addInterceptor(logger)
                .addInterceptor(modifyRequestInterceptor)
                .build();

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
