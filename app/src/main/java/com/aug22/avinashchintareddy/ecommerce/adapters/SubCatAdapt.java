package com.aug22.avinashchintareddy.ecommerce.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aug22.avinashchintareddy.ecommerce.R;
import com.aug22.avinashchintareddy.ecommerce.Pojo.SubCatAcessor;
import com.aug22.avinashchintareddy.ecommerce.interfaces.setClicker;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by avinashchintareddy on 8/30/17.
 */

public class SubCatAdapt extends BaseAdapter {
    List<SubCatAcessor> subCatAcessors;
    Context context;
    LayoutInflater layoutInflater;
    setClicker clicker;

    public SubCatAdapt(List<SubCatAcessor> subCatAcessors, Context context) {
        this.subCatAcessors=subCatAcessors;
        this.context=context;
        layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return subCatAcessors.size();
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
    public View getView(final int i, View myview, ViewGroup viewGroup) {
        Handler handler= new Handler();
        if(myview == null){
            myview=layoutInflater.inflate(R.layout.list_items,viewGroup,false);
            handler.tx_subcateg=(TextView) myview.findViewById(R.id.txt_categid);
            handler.tx_subdesc=(TextView) myview.findViewById(R.id.txt_descid);
            handler.tx_idsub=(TextView) myview.findViewById(R.id.txt_idi);
            handler.img_subcat=myview.findViewById(R.id.img_view);
            myview.setTag(handler);
        }
        else
        {
            myview.getTag();

        }
        Log.i("find fault",""+subCatAcessors.get(i).getSubCatagoryName());
        handler.tx_subcateg.setText(subCatAcessors.get(i).getSubCatagoryName());
        handler.tx_subdesc.setText(subCatAcessors.get(i).getSubCatagoryDiscription());
        handler.tx_idsub.setText(subCatAcessors.get(i).getId());
        Picasso.with(context).load(subCatAcessors.get(i).getCatagoryImage()).into(handler.img_subcat);

        myview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(clicker!=null){
                    clicker.gotClick(view,i);

                }
                /*Intent intent = new Intent(ctx,Second.class);
                intent.putExtra("Position1",position);
                String resSet=txResult[position];
                intent.putExtra("result",resSet);
                ctx.startActivity(intent);*/
            }
        });
        return myview;


    }
    public void getClicker(setClicker clicker){
        this.clicker=clicker;

    }

    public class Handler{
        TextView tx_subcateg,tx_subdesc,tx_idsub;
        ImageView img_subcat;


    }
}
