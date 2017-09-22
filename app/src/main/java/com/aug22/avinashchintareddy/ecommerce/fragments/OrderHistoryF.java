package com.aug22.avinashchintareddy.ecommerce.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.aug22.avinashchintareddy.ecommerce.Pojo.Historyresponse;
import com.aug22.avinashchintareddy.ecommerce.Pojo.OrderHistoryItem;
import com.aug22.avinashchintareddy.ecommerce.R;
import com.aug22.avinashchintareddy.ecommerce.adapters.OrderHistoryAdapter;
import com.aug22.avinashchintareddy.ecommerce.controllers.ApiClient;
import com.aug22.avinashchintareddy.ecommerce.interfaces.apiInterfaces;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

/**
 *
 */
public class OrderHistoryF extends Fragment {
    ListView lv;
    OrderHistoryAdapter adapter;
    List<OrderHistoryItem> movieResponseList=new ArrayList<>();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_order_history, container, false);
        lv= (ListView) view.findViewById(R.id.lst_history);
        getNetworkcalls();
        return view;
    }

    public void getNetworkcalls(){
        SharedPreferences contact=getContext().getSharedPreferences("MyContact", 1);
        String number= contact.getString("M_Number",null);
        Log.i("number",number);
        apiInterfaces apiInterfaces= ApiClient.getClient().create(apiInterfaces.class);
        Call<Historyresponse> call=apiInterfaces.getHistory(number);

        call.enqueue(new Callback<Historyresponse>() {
            @Override
            public void onResponse(Call<Historyresponse> call, Response<Historyresponse> response) {
                Log.i("response",""+response.body().getOrderHistory());
                 movieResponseList= response.body().getOrderHistory();
                Log.i("response",""+movieResponseList.get(0).getItemName());
                adapter=new OrderHistoryAdapter(movieResponseList,getContext()) ;
                lv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Historyresponse> call, Throwable t) {

            }
        });
    }

}
