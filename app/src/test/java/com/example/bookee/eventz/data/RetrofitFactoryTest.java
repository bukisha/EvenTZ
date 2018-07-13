package com.example.bookee.eventz.data;

import junit.framework.Assert;

import org.junit.Test;

import retrofit2.Retrofit;

public class RetrofitFactoryTest {

    @Test
    public void buildRetrofit() {
        //When
        Retrofit retrofit= RetrofitFactory.buildRetrofit();
        //Then
        Assert.assertNotNull(retrofit);
    }
}