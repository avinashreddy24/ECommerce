package com.aug22.avinashchintareddy.ecommerce.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aug22.avinashchintareddy.ecommerce.Pojo.SubCatAcessor;
import com.aug22.avinashchintareddy.ecommerce.R;
import com.aug22.avinashchintareddy.ecommerce.adapters.SubCatAdapt;
import com.aug22.avinashchintareddy.ecommerce.interfaces.setClicker;
import com.aug22.avinashchintareddy.ecommerce.interfaces.subCatsender;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class SubCatF extends Fragment implements setClicker {
    List<SubCatAcessor> subAccessorlist = new ArrayList<>();
    String urlocal="http://rjtmobile.com/ansari/shopingcart/androidapp/cust_sub_category.php?Id=";
    SubCatAdapt subCatAdapt;
    ListView listView;
    SubCatAcessor subcat;
    subCatsender subcsender;
    String mainid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        subAccessorlist.clear();
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_sub_cat, container, false);

        Bundle bundle= getArguments();

        if(bundle!=null){
            String s=bundle.getString("value");
            mainid=s;
            Log.i("SubCatF",s);
            String urllocl=urlocal+s;
            checkURL(urllocl);
            Log.i("SubCatFurl",urllocl);



        }
        listView= (ListView) view.findViewById(R.id.lst_sublst);
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        subcsender= (subCatsender) getActivity();
    }
    public void checkURL(String urlocal){
        StringRequest strReq = new StringRequest(Request.Method.GET, urlocal, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object= new JSONObject(response);
                    Log.i("SubCatF",object.toString());
                    JSONArray jsonArray= object.getJSONArray("SubCategory");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        //Id
                        //SubCatagoryName
                        //SubCatagoryDiscription
                        JSONObject objecateg=jsonArray.getJSONObject(i);
                        String id= objecateg.getString("Id");
                        String title=objecateg.getString("SubCatagoryName");
                        String description=objecateg.getString("SubCatagoryDiscription");
                        String image= objecateg.getString("CatagoryImage");

                        subcat= new SubCatAcessor(id,title,description,image);
                        subAccessorlist.add(subcat);
                    }

                    subCatAdapt= new SubCatAdapt(subAccessorlist,getContext());

                    listView.setAdapter(subCatAdapt);
                    subCatAdapt.getClicker(SubCatF.this);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(strReq);

    }


    @Override
    public void gotClick(View v, int position) {
        String presentid=subAccessorlist.get(position).getId();
        mainid=mainid+","+presentid;
        Log.i("Testproductid",mainid);

        subcsender.subCatsent(mainid);
    }
}
