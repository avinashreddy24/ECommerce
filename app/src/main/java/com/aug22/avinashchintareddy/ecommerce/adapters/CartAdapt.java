package com.aug22.avinashchintareddy.ecommerce.adapters;

import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.aug22.avinashchintareddy.ecommerce.Displayer.ShopList;
import com.aug22.avinashchintareddy.ecommerce.R;
import com.aug22.avinashchintareddy.ecommerce.interfaces.proddetailsender;
import com.aug22.avinashchintareddy.ecommerce.interfaces.setClicker;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by avinashchintareddy on 9/4/17.
 */

public class CartAdapt extends BaseAdapter  {
    private Context context;
    private   List<String> current;
    private   List<String> templist=new ArrayList<>();
    private LayoutInflater layoutInflater;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private setClicker setclick;
    private Gson gson;


    public CartAdapt(List<String> current, Context context){
        this.context =context;
        this.current=current;
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return current.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
       Handler handler= new Handler();
        if(view==null){
            Log.i("List of cart",""+i);
            //view to layout
            view=layoutInflater.inflate(R.layout.list_cart,viewGroup,false);
            //layout to image
            //hndle.img=(ImageView) myview.findViewById(R.id.imgid);
            //layout to text
            handler.tx_prodname= (TextView) view.findViewById(R.id.txt_nameid);
            handler.btn_dele=(Button) view.findViewById(R.id.btn_delete);


            //set tag for binding object layout
            view.setTag(handler);
        }
        else{
            //declare new handle object
            handler = (Handler) view.getTag();

        }
        handler.tx_prodname.setText(current.get(i));

        //hndle.img.setImageResource(imageView[position]);
        handler.btn_dele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("position",""+i);

                sharededit(i);
                current.remove(i);
                notifyDataSetChanged();
                if(setclick!=null){
                    setclick.gotClick(view,i);

                }
                //psender.psent();
            }


        });

        return view;
    }

    //Editing the shared preference
    private void sharededit(int position){
        templist.clear();
        pref = context.getSharedPreferences("MyPref", 2);
        editor=pref.edit();


            String st=pref.getString("Key_value",null);
            if(st!=null){
                try {
                    JSONArray jsonArray= new JSONArray(st);
                    for (int i = 0; i <jsonArray.length() ; i++) {
                        String temp=jsonArray.getString(i);

                        if(i != position){
                        templist.add(temp);
                        }
                        else {
                            Log.i("Removed",""+temp);
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                       editor.remove("Key_value");
                        editor.commit();
                Log.i("In editor",""+pref.getString("Key_value",null));
            }

            //mProductsArrayList.add(detailed);
            gson= new Gson();
            String  jsonProducts = gson.toJson(templist);
            Log.i("changedshared",""+jsonProducts);
            editor.putString("Key_value", jsonProducts);
            editor.commit();


    }

    public void getClicker(setClicker setclick){
        this.setclick=setclick;

    }



    public  class Handler{
        TextView tx_prodname;
        Button btn_dele;


    }
}
