package com.aug22.avinashchintareddy.ecommerce.Pojo;

/**
 * Created by avinashchintareddy on 8/30/17.
 * pojo class for sub categories
 */

public class SubCatAcessor {
    String id;
    String subCatagoryName;
    String subCatagoryDiscription;
    String catagoryImage;


    public SubCatAcessor(String id, String subCatagoryName, String subCatagoryDiscription, String catagoryImage) {
        this.id = id;
        this.subCatagoryName= subCatagoryName;
        this.subCatagoryDiscription = subCatagoryDiscription;
        this.catagoryImage = catagoryImage;
    }

    public String getId() {
        return id;
    }

    public String getSubCatagoryName() {
        return subCatagoryName;
    }

    public String getSubCatagoryDiscription() {
        return subCatagoryDiscription;
    }

    public String getCatagoryImage() {
        return catagoryImage;
    }
}
