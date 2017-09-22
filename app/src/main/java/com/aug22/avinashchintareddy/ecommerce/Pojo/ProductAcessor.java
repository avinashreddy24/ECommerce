package com.aug22.avinashchintareddy.ecommerce.Pojo;

/**
 * Created by avinashchintareddy on 8/31/17.
 * Pojo class for product list
 */

public class ProductAcessor {
    String product;
            //ProductName
    String id;
    //Id
    String descrip;
    //Discription
    //Quantity
    String quantity;
    //Prize
    String prize;
    String image;

    public ProductAcessor(String product, String id, String descrip, String quantity, String prize,String image) {
        this.product = product;
        this.id = id;
        this.descrip = descrip;
        this.quantity = quantity;
        this.prize = prize;
        this.image=image;
    }

    public String getImage() {
        return image;
    }

    public String getProduct() {
        return product;
    }

    public String getId() {
        return id;
    }

    public String getDescrip() {
        return descrip;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrize() {
        return prize;
    }
}
