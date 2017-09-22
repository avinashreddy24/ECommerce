package com.aug22.avinashchintareddy.ecommerce.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aug22.avinashchintareddy.ecommerce.Pojo.CatAcessor;
import com.aug22.avinashchintareddy.ecommerce.Pojo.ProductAcessor;
import com.aug22.avinashchintareddy.ecommerce.R;
import com.aug22.avinashchintareddy.ecommerce.adapters.CartAdapt;
import com.aug22.avinashchintareddy.ecommerce.adapters.CategoryAdapt;
import com.aug22.avinashchintareddy.ecommerce.adapters.ProductAdapt;
import com.aug22.avinashchintareddy.ecommerce.controllers.AppController;
import com.aug22.avinashchintareddy.ecommerce.interfaces.orderHistInter;
import com.aug22.avinashchintareddy.ecommerce.interfaces.setClicker;
import com.aug22.avinashchintareddy.ecommerce.interfaces.subCatsender;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CartF extends Fragment implements setClicker, View.OnClickListener {
    private TextView tx_price;
    private Button btn_order;
    private List<String> current =new ArrayList<>();
    private List<String> current1= new ArrayList<>();
    private ListView listView;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private CartAdapt myadapt;
    private SharedPreferences contact;
    private float price=0;
    private orderHistInter orderhist;
    private int i;

    private static final String PAYPAL_CLIENT_ID="AVcRbc5OWujK_A94GmiLoFf9SxEKCGHLbOA0UwjNchuBQ2ROUzsoeP9uu3AEv4h-j5mytN12aZKdcdZk";

    private static final int PAYPAL_REQUEST_CODE=123;
    private  static PayPalConfiguration payPalConfiguration= new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PAYPAL_CLIENT_ID);
    private String mPaymentAmount,number;

    //String proid;

    private String urlocal="http://rjtmobile.com/ansari/shopingcart/androidapp/cust_product.php?Id=";


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        orderhist= (orderHistInter) getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        sharedPreferences= getContext().getSharedPreferences("MyPref", 2);
        contact=getContext().getSharedPreferences("MyContact", 2);

        editor=sharedPreferences.edit();
        View view=  inflater.inflate(R.layout.fragment_orderdetails, container, false);
        tx_price= view.findViewById(R.id.txt_priceid);
        btn_order= (Button) view.findViewById(R.id.btn_orderid);
        listView= (ListView) view.findViewById(R.id.lst_order);
        btn_order.setOnClickListener(this);
        callurl();

        //

        Intent intent= new Intent(getContext(), PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,payPalConfiguration);
        getContext().startService(intent);




        return view ;
    }

    private void callurl(){
            current.clear();
            price=0;
            myadapt=null;
            String found=sharedPreferences.getString("Key_value",null);
        Log.i("orderdetailer",""+found);
            if(found!=null) {
                try {
                    JSONArray jsonArray = new JSONArray(found);
                    Log.i("cart",""+jsonArray.length());

                    for (int i = 0; i <jsonArray.length() ; i++) {
                        String temp=jsonArray.getString(i);
                        String[] arr=temp.split(",");
                        String  subcat= arr[1];
                        String pid= arr[2];
                        Log.i("cart1",""+jsonArray.length());
                        callProduct(urlocal,subcat,pid,jsonArray.length());
                    }

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                       /* editor.remove("Key_value");
                        editor.commit();*/
            }

    }

    private void callProduct(String urlocal, String id, final String pid, final int value){

        final StringRequest strReq = new StringRequest(Request.Method.GET, urlocal+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    Log.i("cartid",""+i++);
                    JSONObject jobj=new JSONObject(response);

                    JSONArray jarray= jobj.getJSONArray("Product");
                    for (int i = 0; i <jarray.length() ; i++) {
                        JSONObject objj= jarray.getJSONObject(i);
                        int id=objj.getInt("Id");
                        if(Integer.parseInt(pid) == id){
                            Log.i("cartid match",""+id);
                            String name=objj.getString("ProductName");
                            String description=objj.getString("Discription");
                            String quantity=objj.getString("Quantity");
                            float prize= (float) objj.getDouble("Prize");
                            price=prize+price;
                            current.add(name);
                            Log.i("Value ,size",""+value+","+current.size());
                            if(value == current.size()){
                                Log.i("order in loop",""+current.size());
                                tx_price.setText(String.valueOf(price));
                                if(myadapt==null){
                                myadapt= new CartAdapt(current,getContext());
                                listView.setAdapter(myadapt);
                                myadapt.getClicker(CartF.this);
                                    break;
                                }
                           }
                        }
                    }
                    /*tx_price.setText(String.valueOf(price));
                    myadapt= new CartAdapt(current,getContext());
                    listView.setAdapter(myadapt);
                    myadapt.getClicker(CartF.this);*/

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
    public void onResume() {
        super.onResume();
        Log.i("Resume test","Resumed");

    }



    @Override
    public void gotClick(View v, int position) {
        price=0;
        current1.clear();
        callPrize(position);

    }
    private   void callPrize(int iposition){

        Log.i("TestMain",""+price);
        String found=sharedPreferences.getString("Key_value",null);
        Log.i("TestMain",""+found);

        if(found!=null) {
            try {
                JSONArray jsonArray = new JSONArray(found);
                if(jsonArray.length()==0){
                    tx_price.setText(price+"");
                }


                for (int i = 0; i <jsonArray.length() ; i++) {
                    String temp=jsonArray.getString(i);
                    String[] arr=temp.split(",");
                    String  subcat= arr[1];
                    String pid= arr[2];
                    Log.i("interface",""+jsonArray.length());


                    callforProduct(urlocal,subcat,pid,jsonArray.length());

                    Log.i("Inerface",""+current.size());
                }

            }
            catch (JSONException e) {
                e.printStackTrace();
            }

                       /* editor.remove("Key_value");
                        editor.commit();*/
        }


    }

    private void callforProduct(String urlocal, String id, final String pid, final int value){

        StringRequest strReq = new StringRequest(Request.Method.GET, urlocal+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jobj=new JSONObject(response);
                    JSONArray jarray= jobj.getJSONArray("Product");
                    Log.i("order category",jarray.get(0).toString());
                    for (int i = 0; i <jarray.length() ; i++) {
                        JSONObject objj= jarray.getJSONObject(i);
                        Log.i("in found last",""+objj);
                        int id=objj.getInt("Id");
                        if(Integer.parseInt(pid) == id){
                            String name=objj.getString("ProductName");
                            String description=objj.getString("Discription");
                            String quantity=objj.getString("Quantity");
                            float prize= (float) objj.getDouble("Prize");
                            price=prize+price;
                            Log.i("found last",""+price);
                            current1.add(name);
                            Log.i("value ,size",""+value+" "+current1.size());
                            if(value == current1.size()){
                             Log.i("order size",""+current.size());
                                tx_price.setText(price+"");
                                Log.i("Order price",""+price);
                                break;
                            }

                        }
                    }
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
    public void onClick(View view) {
        number=contact.getString("M_Number",null);
        Log.i("The number is ",""+number);

        getPayment();


    }

    private void getPayment(){

        mPaymentAmount=String.valueOf(price);
        PayPalPayment payPalPayment=new PayPalPayment(new BigDecimal(String.valueOf(mPaymentAmount)),"USD","remarks",PayPalPayment.PAYMENT_INTENT_SALE);
        Intent paymentint= new Intent(getContext(), PaymentActivity.class);
        paymentint.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,payPalConfiguration);
        paymentint.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);
        startActivityForResult(paymentint,PAYPAL_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PAYPAL_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                PaymentConfirmation paymentConfirmation= data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if(paymentConfirmation !=null){
                    try{
                        String paymentDetails=paymentConfirmation.toJSONObject().toString();

                        //call url with name and id
                        toOrder();
                        Toast.makeText(getContext(),"Order Placed",Toast.LENGTH_SHORT).show();
                        orderhist.osent();

                    }
                    catch (Exception e){
                        Toast.makeText(getContext(),"Sucessful",Toast.LENGTH_SHORT).show();
                        e.printStackTrace();

                    }

                }

            }

        }
    }


    private void callfororder(String urlocal, String id, final String pid, final int value){

        StringRequest strReq = new StringRequest(Request.Method.GET, urlocal+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jobj=new JSONObject(response);
                    JSONArray jarray= jobj.getJSONArray("Product");
                    for (int i = 0; i <jarray.length() ; i++) {
                        JSONObject objj= jarray.getJSONObject(i);

                        int id=objj.getInt("Id");
                        if(Integer.parseInt(pid) == id){
                            String name=objj.getString("ProductName");
                            float prize= (float) objj.getDouble("Prize");
                            String url="http://rjtmobile.com/ansari/shopingcart/androidapp/orders.php?&item_id="+id+"&item_names="+name+"&item_quantity=1&final_price="+prize+"&mobile="+number;

                            getConfirmOrder( url);

                            Log.i("found orders",url);
                        }

                    }
                    editor.clear();
                    editor.commit();
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

    //method to push to orders.
    private void toOrder(){
        String found=sharedPreferences.getString("Key_value",null);
        if(found!=null) {
            try {
                JSONArray jsonArray = new JSONArray(found);

                for (int i = 0; i <jsonArray.length() ; i++) {
                    String temp=jsonArray.getString(i);
                    String[] arr=temp.split(",");
                    String  subcat= arr[1];
                    String pid= arr[2];
                    callfororder(urlocal,subcat,pid,jsonArray.length());
                    Log.i("orderdetailer size",""+current.size());
                }

            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("In loop","");
                       /* editor.remove("Key_value");
                        editor.commit();*/
        }



    }




    //to get the order cofirmation
    private void getConfirmOrder(String url){
        StringRequest strReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    Log.i("Orderresponse",response);

                } catch (Exception e) {
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



}
