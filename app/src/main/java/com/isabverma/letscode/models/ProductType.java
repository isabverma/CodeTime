package com.isabverma.letscode.models;


/**
 * Created by isabverma on 2/13/2017.
 */

public class ProductType {

    private String ProductTypeName;
    private String jsonKey;

    public ProductType() {
    }

    public String getProductTypeName() {
        return ProductTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        ProductTypeName = productTypeName;
    }

    public String getJsonKey() {
        return jsonKey;
    }

    public void setJsonKey(String jsonKey) {
        this.jsonKey = jsonKey;
    }

    public ProductType(String productTypeName, String jsonKey) {
        ProductTypeName = productTypeName;
        this.jsonKey = jsonKey;
    }
}
