package com.aug22.avinashchintareddy.ecommerce.adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aug22.avinashchintareddy.ecommerce.Pojo.ProductAcessor;
import com.aug22.avinashchintareddy.ecommerce.R;
import com.aug22.avinashchintareddy.ecommerce.interfaces.setClicker;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by avinashchintareddy on 8/30/17.
 */

public class ProductAdapt extends BaseAdapter {
    List<ProductAcessor> productAcessorList ;
    Context context;
    LayoutInflater layoutInflater;
    setClicker clicker;

    public ProductAdapt(List<ProductAcessor> productAcessorList, Context context) {
        this.productAcessorList = productAcessorList;
        this.context = context;
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        Log.i("List size",""+productAcessorList.size());
        return productAcessorList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public static class Handler{
        TextView tx_prodname,tx_proddesc,tx_proid;
        ImageView img_prd;

    }

    @Override
    public View getView(final int i, View myview, ViewGroup viewGroup) {
        Handler handler = new Handler();
        if(myview == null){
            myview=layoutInflater.inflate(R.layout.list_prod,viewGroup,false);
            handler.tx_prodname=(TextView) myview.findViewById(R.id.txt_pcategid);
            handler.tx_proddesc=(TextView) myview.findViewById(R.id.txt_pdescid);
            handler.img_prd=myview.findViewById(R.id.img_pview);
            handler.tx_proid=(TextView) myview.findViewById(R.id.txt_pidi);


            myview.setTag(handler);
        }
        else
        {
            handler = (Handler) myview.getTag();

        }

        Log.i("find fault",""+productAcessorList.get(i).getProduct());

        handler.tx_prodname.setText(""+productAcessorList.get(i).getProduct());
        handler.tx_proddesc.setText(""+productAcessorList.get(i).getDescrip());
        handler.tx_proid.setText(""+productAcessorList.get(i).getId());
        Picasso.with(context).load(""+productAcessorList.get(i).getImage()).into(handler.img_prd);
        myview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(clicker!=null){
                    clicker.gotClick(view,i);

                }

            }
        });

        return myview;
    }


    public void getClicker(setClicker clicker){
        this.clicker=clicker;

    }

}
