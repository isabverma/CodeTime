package com.isabverma.letscode.startLearn;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.isabverma.letscode.R;
import com.isabverma.letscode.modal.Category;
import com.isabverma.letscode.modal.Product;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener{

    private DatabaseReference databaseReference;

    private SearchView search;
    private ProductExpandableListAdapter productExpandableListAdapter;
    private ExpandableListView expandableListView;
    private ArrayList<Category> categoryList = new ArrayList<Category>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        //writeData(); //Required for data load

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        search = (SearchView) findViewById(R.id.activity_product_search_view);
        search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setOnCloseListener(this);
        //expandableListView = (ExpandableListView) findViewById(R.id.activity_product_expendable_listview);
        //productExpandableListAdapter = new ProductExpandableListAdapter(ProductActivity.this, categoryList);
        //expandableListView.setAdapter(productExpandableListAdapter);
        //displayList();


        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("availableProducts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(ProductActivity.this, "Snapshot contains : " + dataSnapshot.toString(), Toast.LENGTH_SHORT).show();
                //categoryList = new ArrayList<Category>();
                for(DataSnapshot categoryDataSnapshot : dataSnapshot.getChildren()){
                    Category category = categoryDataSnapshot.getValue(Category.class);
                    Toast.makeText(ProductActivity.this, "Category Name : " + category.getCategoryName(), Toast.LENGTH_SHORT).show();
                    categoryList.add(category);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ProductActivity.this, "OOPS..!!! Connection to server cannot be established.", Toast.LENGTH_LONG).show();
            }
        });



        expandAll();
    }

    @Override
    public boolean onClose() {
        productExpandableListAdapter.filterData("");
        expandAll();
        return false;
    }

    private void expandAll(){
        int count = productExpandableListAdapter.getGroupCount();
        for(int i=0;i<count;i++){
            expandableListView.expandGroup(i);
        }
    }

    private void displayList(){
        //loadSomeData();
        //expandableListView = (ExpandableListView) findViewById(R.id.activity_product_expendable_listview);
        //productExpandableListAdapter = new ProductExpandableListAdapter(ProductActivity.this, categoryList);
        //expandableListView.setAdapter(productExpandableListAdapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        productExpandableListAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        productExpandableListAdapter.filterData(newText);
        expandAll();
        return false;
    }

    private void writeData(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        loadSomeDataForOnce();
        databaseReference.child("availableProducts").setValue(categoryList);
    }

    private void loadSomeDataForOnce(){
        ArrayList<Product> productList = new ArrayList<Product>();
        Product product = new Product("Java");
        productList.add(product);
        product = new Product("HTML");
        productList.add(product);
        product = new Product("CSS");
        productList.add(product);

        Category category = new Category("Languages" , productList);
        categoryList.add(category);

        productList = new ArrayList<Product>();
        product = new Product("Spring");
        productList.add(product);
        product = new Product("Hibernate");
        productList.add(product);

        category = new Category("Frameworks" , productList);
        categoryList.add(category);

        productList = new ArrayList<Product>();
        product = new Product("SVN");
        productList.add(product);
        product = new Product("GIT");
        productList.add(product);

        category = new Category("Versioning Tools" , productList);
        categoryList.add(category);
    }
    private void loadSomeData(){

    }
}
