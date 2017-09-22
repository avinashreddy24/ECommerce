package com.aug22.avinashchintareddy.ecommerce.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aug22.avinashchintareddy.ecommerce.Pojo.OrderHistoryItem;
import com.aug22.avinashchintareddy.ecommerce.R;

import java.util.List;

/**
 * Created by avinashchintareddy on 9/6/17.
 */

public class OrderHistoryAdapter extends BaseAdapter {
    private List<OrderHistoryItem> ResponseList;
    private Context context;
    private LayoutInflater layoutInflater;

    public OrderHistoryAdapter(List<OrderHistoryItem> ResponseList,Context context){
        this.ResponseList=ResponseList;
        this.context=context;
        layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return ResponseList.size();
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
    public View getView(int i, View myview, ViewGroup viewGroup) {
        Handler handler= new Handler();
        if(myview == null){
            Log.i("Adapters","in");
            myview=layoutInflater.inflate(R.layout.list_history,viewGroup,false);
            handler.tx_name=(TextView) myview.findViewById(R.id.txt_namedid);
            handler.tx_id=(TextView) myview.findViewById(R.id.txt_idi);
            handler.tx_price=(TextView) myview.findViewById(R.id.txt_priceidi);
            handler.tx_status=myview.findViewById(R.id.txt_status);

            myview.setTag(handler);
        }
        else {
            handler = (Handler) myview.getTag();
        }
        handler.tx_status.setText(ResponseList.get(i).getOrderStatus());
        handler.tx_price.setText(ResponseList.get(i).getFinalPrice());
        handler.tx_name.setText(ResponseList.get(i).getItemName());
        handler.tx_id.setText(ResponseList.get(i).getOrderID());



        return myview;
    }
    public class Handler{
        TextView tx_id,tx_name,tx_price,tx_status;

    }
}
