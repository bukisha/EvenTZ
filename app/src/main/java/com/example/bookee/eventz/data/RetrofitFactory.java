package com.example.bookee.eventz.data;

import android.util.Log;

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

import static android.support.constraint.Constraints.TAG;

public class RetrofitFactory {
    private static final String TAG = "RetrofitFactory";
    private static final String BASE_URL = "https://www.eventbriteapi.com/v3/";
    private static final String API_OAUTH_TOKEN_ANONYMOUS = "53SXZPEGKDPUHUDW26DP";
    private static final String API_OAUTH_TOKEN_PERSONAL = "UHSWAYREEJUQBBIH3H3H";
    private static final String SLASH = "/";
    private static final String METHOD_TYPE_POST = "POST";

    public static Retrofit buildRetrofit() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        //logger interceptor for debugging
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        //interceptor that concatenates slash onto request url if we are doing POST request
//        Interceptor addPersonalAuthTokenInterceptor = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request oldRequest = chain.request();
//                HttpUrl outgoingUrl = oldRequest.url();
//                Request newRequest = oldRequest;
//
//                if (oldRequest.method().equals(METHOD_TYPE_POST)) {
//                    String nextUrl = outgoingUrl.toString();
//
//                    Log.d(TAG, "intercept: next url before adding slash is " + nextUrl);
//                    if (!(nextUrl.equals("https://s3.amazonaws.com/eventbrite-uploader-incoming-prod/"))) {
//                        nextUrl = nextUrl.concat(SLASH);
//                    }
//                    Log.d(TAG, "intercept: next url after adding slash is " + nextUrl);
//                    HttpUrl nextURL = HttpUrl.parse(nextUrl);
//                    if (nextURL != null && !nextUrl.equals("https://s3.amazonaws.com/eventbrite-uploader-incoming-prod/")) {
//                        HttpUrl.Builder urlBuilder = nextURL.newBuilder();
//                            HttpUrl newUrl = urlBuilder
//                                    .addQueryParameter("token", getAuthTokenPersonal())
//                                    .build();
//                            newRequest = oldRequest.newBuilder().url(newUrl).build();
//                    }
//                    return chain.proceed(newRequest);
//                } else {
//                    HttpUrl newUrl = outgoingUrl.newBuilder()
//                            // .addQueryParameter("token", getAuthTokenPersonal())
//                            .build();
//                    newRequest = oldRequest.newBuilder().url(newUrl).build();
//                    return chain.proceed(newRequest);
//                }
//            }
//        };

//        Interceptor addPersonalAuthTokenInterceptor = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request oldRequest = chain.request();
//                HttpUrl outgoingUrl = oldRequest.url();
//                Request newRequest;
//                if(oldRequest.method().equals(METHOD_TYPE_POST )) {
//                    HttpUrl newUrl = outgoingUrl.newBuilder()
//                            .addQueryParameter("token", getAuthTokenPersonal())
//                            .build();
//                    newRequest = oldRequest.newBuilder().url(newUrl).build();
//                    return chain.proceed(newRequest);
//                }
//                return chain.proceed(oldRequest);
//            }
//        };
        OkHttpClient client = okHttpBuilder
                .addInterceptor(logger)
                //.addInterceptor(addPersonalAuthTokenInterceptor)
                .build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(client);

        return builder.build();
    }

    public static String getAuthTokenAnonymous() {
        return API_OAUTH_TOKEN_ANONYMOUS;
    }

    public static String getAuthTokenPersonal() {
        return API_OAUTH_TOKEN_PERSONAL;
    }

}
