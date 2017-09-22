package com.aug22.avinashchintareddy.ecommerce.fragments;

import android.content.Context;
import android.net.Uri;
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
import com.aug22.avinashchintareddy.ecommerce.Pojo.ProductAcessor;
import com.aug22.avinashchintareddy.ecommerce.Pojo.SubCatAcessor;
import com.aug22.avinashchintareddy.ecommerce.R;
import com.aug22.avinashchintareddy.ecommerce.adapters.CategoryAdapt;
import com.aug22.avinashchintareddy.ecommerce.adapters.ProductAdapt;
import com.aug22.avinashchintareddy.ecommerce.interfaces.prodsender;
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
public class ProductF extends Fragment implements setClicker {
    List<ProductAcessor> productlist= new ArrayList<>();
    ListView listView;
    ProductAdapt productAdapt;
    ProductAcessor asc;
    prodsender psender;
    String mainid;
    String urlocal="http://rjtmobile.com/ansari/shopingcart/androidapp/cust_product.php?Id=";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_product, container, false);
        listView= (ListView) view.findViewById(R.id.lst_prod);
        productlist.clear();
        Bundle bundle= getArguments();
        if(bundle!=null){
            String rid=bundle.getString("subid");
            String [] arrid=rid.split(",");
            String s=arrid[1];
            mainid=rid;

            String locl=urlocal+s;
            Log.i("TestProduct",urlocal);
            callUrl(locl);

        }
        return view;
    }



    public void callUrl(String s){
        StringRequest strReq = new StringRequest(Request.Method.GET, s, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jobj=new JSONObject(response);
                    JSONArray jarray= jobj.getJSONArray("Product");
                    Log.i("TestCategory",""+jobj);
                    for (int i = 0; i <jarray.length() ; i++) {

                        JSONObject objj= jarray.getJSONObject(i);
                        String name=objj.getString("ProductName");
                        String id=objj.getString("Id");
                        String description=objj.getString("Discription");
                        String quantity=objj.getString("Quantity");
                        String prize=objj.getString("Prize");


                        String img= objj.getString("Image");
                         asc= new ProductAcessor(name,id,description,quantity,prize,img);

                        productlist.add(asc);
                        if(productlist.size()==jarray.length()){
                            productAdapt= new ProductAdapt(productlist,getContext());

                            listView.setAdapter(productAdapt);
                            productAdapt.getClicker(ProductF.this);
                        }
                    }
                    /*productAdapt= new ProductAdapt(productlist,getContext());

                    listView.setAdapter(productAdapt);
                    productAdapt.getClicker(ProductF.this)*/;

                    //calltoTransfer(myadapt);
                   // myadpt.adaptclick(CategoriesF.this);
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
    public void onAttach(Context context) {
        super.onAttach(context);
        psender= (prodsender) getActivity();
    }


    @Override
    public void gotClick(View v, int position) {
        String presentid=productlist.get(position).getId();
        mainid=mainid+","+presentid;
        Log.i("Testproductid",mainid);

        psender.detsent(productlist.get(position).getProduct(),productlist.get(position).getImage(),productlist.get(position).getDescrip(),productlist.get(position).getPrize(),productlist.get(position).getQuantity(),mainid);
    }
}
