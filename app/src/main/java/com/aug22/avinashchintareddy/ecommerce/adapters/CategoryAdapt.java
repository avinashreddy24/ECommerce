package com.aug22.avinashchintareddy.ecommerce.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aug22.avinashchintareddy.ecommerce.Pojo.CatAcessor;
import com.aug22.avinashchintareddy.ecommerce.R;
import com.aug22.avinashchintareddy.ecommerce.interfaces.setClicker;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by avinashchintareddy on 8/30/17.
 *
 * for the adapter view
 */

public class CategoryAdapt extends BaseAdapter {
    setClicker setclick;
    LayoutInflater layoutInflater;
    public List<CatAcessor> acessors;
    Context context;
    public CategoryAdapt(List<CatAcessor> acessors, Context context) {
        this.acessors = acessors;
        this.context = context;
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        Log.i("List size",""+acessors.size());
        return acessors.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public  static class Handle{
        TextView tx_categ,tx_id,tx_desc;
        ImageView imageView;

    }


    @Override
    public View getView(final int i, View myview, ViewGroup viewGroup) {
        Handle hndle = new Handle();
        if(myview==null){
            //view to layout
            myview=layoutInflater.inflate(R.layout.list_items,viewGroup,false);

            //layout to text
            hndle.tx_categ=(TextView) myview.findViewById(R.id.txt_categid);
            hndle.tx_desc=(TextView) myview.findViewById(R.id.txt_descid);
            hndle.tx_id=(TextView) myview.findViewById(R.id.txt_idi);
            hndle.imageView=myview.findViewById(R.id.img_view);


            //set tag for binding object layout
            myview.setTag(hndle);
        }
        else{
            //declare new handle object
            hndle = (Handle) myview.getTag();

        }
        Log.i("find fault",""+acessors.get(i).getTitle());
        hndle.tx_categ.setText(acessors.get(i).getTitle());
        hndle.tx_desc.setText(acessors.get(i).getDescription());
        hndle.tx_id.setText(acessors.get(i).getId());
        Picasso.with(context).load(acessors.get(i).getImg_url()).into(hndle.imageView);
        myview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(setclick!=null){
                    setclick.gotClick(view,i);

                }

            }
        });
        return myview;

    }

    public  void adaptclick(setClicker setclick){
        this.setclick=setclick;

    }



}

