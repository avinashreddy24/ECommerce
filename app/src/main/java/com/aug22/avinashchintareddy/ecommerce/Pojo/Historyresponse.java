package com.aug22.avinashchintareddy.ecommerce.Pojo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Historyresponse{

	@SerializedName("Order History")
	private List<OrderHistoryItem> orderHistory;

	public void setOrderHistory(List<OrderHistoryItem> orderHistory){
		this.orderHistory = orderHistory;
	}

	public List<OrderHistoryItem> getOrderHistory(){
		return orderHistory;
	}

	@Override
 	public String toString(){
		return 
			"Historyresponse{" + 
			"order History = '" + orderHistory + '\'' + 
			"}";
		}
}