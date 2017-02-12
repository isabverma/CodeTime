package com.isabverma.letscode.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by isabverma on 2/11/2017.
 */

public class CategoryBean {
    private String categoryName;
    private List<ProductBean> productBean = new ArrayList<>();

    public CategoryBean() {
    }

    public CategoryBean(String categoryName, List<ProductBean> productBean) {
        this.categoryName = categoryName;
        this.productBean = productBean;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        categoryName = categoryName;
    }

    public List<ProductBean> getProductBean() {
        return productBean;
    }

    public void setProductBean(List<ProductBean> productBean) {
        this.productBean = productBean;
    }
}
