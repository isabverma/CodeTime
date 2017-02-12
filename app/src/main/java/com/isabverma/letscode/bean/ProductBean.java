package com.isabverma.letscode.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by isabverma on 2/11/2017.
 */

public class ProductBean {
    private String productName;
    private List<String> flavours;

    public ProductBean() {
    }

    public ProductBean(String productName, List<String> flavours) {
        this.productName = productName;
        this.flavours = flavours;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<String> getFlavours() {
        return flavours;
    }

    public void setFlavours(List<String> flavours) {
        this.flavours = flavours;
    }
}
