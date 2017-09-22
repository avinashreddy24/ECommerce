package com.aug22.avinashchintareddy.ecommerce.interfaces;

import com.aug22.avinashchintareddy.ecommerce.Pojo.Historyresponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by avinashchintareddy on 9/1/17.
 */

public interface apiInterfaces {
    //http://api.themoviedb.org/3/movie/top_rated?api_key=05390fa31523df1e0050baecad4d516f
    //http://rjtmobile.com/ansari/shopingcart/androidapp/order_history.php?&mobile=5555555 



    @GET("order_history.php")
    Call<Historyresponse> getHistory(@Query("mobile") String number);



}
