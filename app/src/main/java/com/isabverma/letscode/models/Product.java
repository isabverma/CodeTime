package com.isabverma.letscode.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by isabverma on 2/13/2017.
 */
@IgnoreExtraProperties
public class Product {
    private String productName;
    private String drawableName;

    public Product() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDrawableName() {
        return drawableName;
    }

    public void setDrawableName(String drawableName) {
        this.drawableName = drawableName;
    }

    public Product(String drawableName, String productName) {
        this.drawableName = drawableName;
        this.productName = productName;
    }
}