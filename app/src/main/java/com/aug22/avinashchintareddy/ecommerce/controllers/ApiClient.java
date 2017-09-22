package com.aug22.avinashchintareddy.ecommerce.controllers;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by avinashchintareddy on 9/1/17.
 */

public class ApiClient {
    //http://api.themoviedb.org/3/movie/top_rated?api_key=05390fa31523df1e0050baecad4d516f

    private static final String BASE_URL_DEV = "http://rjtmobile.com/ansari/shopingcart/androidapp/";
    private static Retrofit retrofit = null;

    // retrofit instantiation
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL_DEV)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
