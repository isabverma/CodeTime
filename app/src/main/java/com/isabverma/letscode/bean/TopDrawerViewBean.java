package com.isabverma.letscode.bean;

import java.util.List;

/**
 * Created by isabverma on 2/11/2017.
 */

public class TopDrawerViewBean {

    private List<CategoryBean> categoryBeanList;

    public TopDrawerViewBean() {
    }

    public TopDrawerViewBean(List<CategoryBean> categoryBeanList) {
        this.categoryBeanList = categoryBeanList;
    }

    public List<CategoryBean> getCategoryBeanList() {
        return categoryBeanList;
    }

    public void setCategoryBeanList(List<CategoryBean> categoryBeanList) {
        this.categoryBeanList = categoryBeanList;
    }
}
