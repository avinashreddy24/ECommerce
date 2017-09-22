package com.aug22.avinashchintareddy.ecommerce.Pojo;

import android.content.Context;

/**
 * Created by avinashchintareddy on 8/30/17.
 *
 * Pojo class for the categories
 */

public class CatAcessor {
    String title;
    String description;
    String id;
    String img_url;


    public CatAcessor(String title, String description, String id,String img_url){
        this.title=title;
        this.description=description;
        this.id=id;
        this.img_url=img_url;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }




    public String getTitle(){
        return title;
    }
}
