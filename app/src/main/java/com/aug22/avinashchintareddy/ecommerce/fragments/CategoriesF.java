package com.aug22.avinashchintareddy.ecommerce.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aug22.avinashchintareddy.ecommerce.Pojo.CatAcessor;
import com.aug22.avinashchintareddy.ecommerce.R;
import com.aug22.avinashchintareddy.ecommerce.adapters.CategoryAdapt;
import com.aug22.avinashchintareddy.ecommerce.controllers.AppController;
import com.aug22.avinashchintareddy.ecommerce.interfaces.senders;
import com.aug22.avinashchintareddy.ecommerce.interfaces.setClicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * To display fragement for categries
 */
public class CategoriesF extends Fragment implements setClicker {
    List<CatAcessor> current=new ArrayList<>();
    TextView tx;
    ListView listView;
    String s_url="http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php\n";
    senders sender;
    CategoryAdapt myadpt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_categeries, container, false);
        tx=view.findViewById(R.id.txt_categid);
        listView= (ListView) view.findViewById(R.id.lst_view);
        //Myadapter Adpt= new Myadapter(countries,imagearray,this);
        callUrl(s_url);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sender= (senders) getActivity();
    }

    public void callUrl(String s){
        StringRequest strReq = new StringRequest(Request.Method.GET, s, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jobj=new JSONObject(response);
                    JSONArray jarray= jobj.getJSONArray("Category");
                    Log.i("TestCategory",jarray.get(0).toString());
                    for (int i = 0; i <jarray.length() ; i++) {
                        JSONObject objj= jarray.getJSONObject(i);
                        String name=objj.getString("CatagoryName");
                        String id=objj.getString("Id");
                        String description=objj.getString("CatagoryDiscription");
                        String img= objj.getString("CatagoryImage");
                        CatAcessor asc= new CatAcessor(name,description,id,img);

                        current.add(asc);
                    }
                     myadpt= new CategoryAdapt(current,getContext());

                    listView.setAdapter(myadpt);

                    //calltoTransfer(myadapt);
                    myadpt.adaptclick(CategoriesF.this);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        AppController.getInstance().addToRequestQueue(strReq);

    }




    @Override
    public void gotClick(View v, int position) {

         sender.sent(current.get(position).getId());

    }
}
