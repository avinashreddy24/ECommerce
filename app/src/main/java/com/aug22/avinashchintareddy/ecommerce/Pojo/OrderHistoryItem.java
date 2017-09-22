package com.aug22.avinashchintareddy.ecommerce.Pojo;

import com.google.gson.annotations.SerializedName;

public class OrderHistoryItem{

	@SerializedName("OrderStatus")
	private String orderStatus;

	@SerializedName("ItemQuantity")
	private String itemQuantity;

	@SerializedName("ItemName")
	private String itemName;

	@SerializedName("OrderID")
	private String orderID;

	@SerializedName("FinalPrice")
	private String finalPrice;

	public void setOrderStatus(String orderStatus){
		this.orderStatus = orderStatus;
	}

	public String getOrderStatus(){
		return orderStatus;
	}

	public void setItemQuantity(String itemQuantity){
		this.itemQuantity = itemQuantity;
	}

	public String getItemQuantity(){
		return itemQuantity;
	}

	public void setItemName(String itemName){
		this.itemName = itemName;
	}

	public String getItemName(){
		return itemName;
	}

	public void setOrderID(String orderID){
		this.orderID = orderID;
	}

	public String getOrderID(){
		return orderID;
	}

	public void setFinalPrice(String finalPrice){
		this.finalPrice = finalPrice;
	}

	public String getFinalPrice(){
		return finalPrice;
	}

	@Override
 	public String toString(){
		return 
			"OrderHistoryItem{" + 
			"orderStatus = '" + orderStatus + '\'' + 
			",itemQuantity = '" + itemQuantity + '\'' + 
			",itemName = '" + itemName + '\'' + 
			",orderID = '" + orderID + '\'' + 
			",finalPrice = '" + finalPrice + '\'' + 
			"}";
		}
}