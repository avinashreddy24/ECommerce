package com.aug22.avinashchintareddy.ecommerce.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aug22.avinashchintareddy.ecommerce.R;
import com.aug22.avinashchintareddy.ecommerce.interfaces.proddetailsender;
import com.aug22.avinashchintareddy.ecommerce.interfaces.prodsender;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 */
public class ProductDetailF extends Fragment {
    private View.OnClickListener addoncart,oncheckout;
    private Snackbar snackbar;
    private View view;
    private String named,detailed,proid,img,price;
    //ArrayList<String> mProductsArrayList = new ArrayList<>();
    private HashSet<String> mProductsArrayList=new HashSet<String>();
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Gson gson;
    private proddetailsender psender;
    private ImageView img_viewr;
    private TextView txt_price;







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mProductsArrayList.clear();
       pref = getContext().getSharedPreferences("MyPref", 2);
               //PreferenceManager.getDefaultSharedPreferences(getContext()); // 0 - for private mode
          editor = pref.edit();
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_product_detail, container, false);
        img_viewr=view.findViewById(R.id.img_proview);
        txt_price=view.findViewById(R.id.tx_priceiid);
        //snacker(view);
        Bundle bundle= getArguments();
        if(bundle!=null){
            String name=bundle.getString("protitle");
            String details=bundle.getString("prodesc");
            String quantity=bundle.getString("proqt");
            String image= bundle.getString("proimage");
            String pre=bundle.getString("pprize");
            String id= bundle.getString("proid");
            img= image;
            named=name;
            detailed=details;
            proid=id;
            price=pre;

            Log.i("TestProddetail",""+pre);

        }
        Picasso.with(getContext()).load(img).into(img_viewr);
        txt_price.setText(""+price);

        return view;
    }
    public  void snacker(View view){
        //view.findViewById(R.id.lyt_detailer)

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

                //=view.setOnClickListener(this);
        snackbar = Snackbar.make(view,"zscfvghbj",snackbar.LENGTH_INDEFINITE);
        View sbView = snackbar.getView();
        //sbView.OnClickListener onclickcheck ;
        snackbar.setAction("Addtocart",cart());
        //snackbar.setAction("Checkout",oncheckout);
        View snackbarView = snackbar.getView();
        int snackbarTextId = android.support.design.R.id.snackbar_text;
        TextView textView = (TextView) snackbarView.findViewById(snackbarTextId);
        textView.setText("Checkout");

        //textView.setBackgroundColor(Color.rgb(113, 0, 0));
         //textView.setTextColor(Color.WHITE);
         textView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("Testsnack","clicked text");
            psender.psent();


            // undo mark as unread code
        }
    });

        sbView.setBackgroundColor(Color.rgb(113, 156, 14));
        snackbar.show();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        psender= (proddetailsender) getActivity();
    }

    public View.OnClickListener cart(){
        addoncart= new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(proid!=null){
                    String st=pref.getString("Key_value",null);
                    if(st!=null){
                        try {
                            JSONArray jsonArray= new JSONArray(st);
                            for (int i = 0; i <jsonArray.length() ; i++) {
                                String temp=jsonArray.getString(i);
                                mProductsArrayList.add(temp);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.i("In loop",""+st);

                    }

                mProductsArrayList.add(proid);
                    gson= new Gson();
                    String  jsonProducts = gson.toJson(mProductsArrayList);
                    Log.i("orderdetails",""+jsonProducts);
                    editor.putString("Key_value", jsonProducts);
                    editor.commit();
                }
                psender.psent();


            }
        };

    return  addoncart;}


}
