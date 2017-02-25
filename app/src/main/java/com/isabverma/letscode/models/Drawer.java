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
public class Drawer implements Serializable {

    private Map<String, Category> categoryMap = new HashMap();

    public Drawer() {
    }

    public Map<String, Category> getCategoryMap() {
        return categoryMap;
    }

    public void setCategoryMap(Map<String, Category> categoryMap) {
        this.categoryMap = categoryMap;
    }

    public Drawer(Map<String, Category> categoryMap) {
        this.categoryMap = categoryMap;
    }
}
