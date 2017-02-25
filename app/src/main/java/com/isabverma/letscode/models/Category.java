package com.isabverma.letscode.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by isabverma on 2/13/2017.
 */
@IgnoreExtraProperties
public class Category implements Serializable{
    private String CategoryName;
    private Map<String, Product> productMap = new HashMap<>();

    public Category() {
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public Map<String, Product> getProductMap() {
        return productMap;
    }

    public void setProductMap(Map<String, Product> productMap) {
        this.productMap = productMap;
    }

    public Category(String categoryName, Map<String, Product> productMap) {
        CategoryName = categoryName;
        this.productMap = productMap;
    }
}
