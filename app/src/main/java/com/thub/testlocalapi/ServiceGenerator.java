package com.thub.testlocalapi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    //Development API
    private static final String BASE_URL = "http://192.168.2.146:90/ardaykaalapi/";

    //Tawakal Uk Live Url
//   private static final String BASE_URL =  "https://tremit.tawakal.company/api/api/";

//    //Live API
//   private static final String BASE_URL = "https://tremit.tawakal.company/api/api/";






    private static ServiceGenerator mInstance;
    public static Retrofit retrofit;

    final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(880, TimeUnit.SECONDS)
            .connectTimeout(880, TimeUnit.SECONDS)
            .build();


    private ServiceGenerator(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static synchronized ServiceGenerator getInstance(){

        if(mInstance == null){
            mInstance = new ServiceGenerator();
        }
        return mInstance;
    }

    public AppService getApi(){
        return retrofit.create(AppService.class);
    }
}
