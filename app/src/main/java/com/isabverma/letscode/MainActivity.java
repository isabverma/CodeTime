package com.isabverma.letscode;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.isabverma.letscode.auth.FirebaseUIActivity;
import com.isabverma.letscode.bean.CategoryBean;
import com.isabverma.letscode.bean.ProductBean;
import com.isabverma.letscode.bean.TopDrawerViewBean;
import com.isabverma.letscode.intro.MainIntroActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context context;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_container);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.content_main, null);
        mainLayout.removeAllViews();
        mainLayout.addView(layout);


        ProductBean productBean1 = new ProductBean("Java", Arrays.asList("Basic", "Proficient", "Expert", "Test Yourself Q & A", "Interview Q & A"));
        ProductBean productBean2 = new ProductBean("HTML", Arrays.asList("Basic", "Proficient", "Expert", "Test Yourself Q & A", "Interview Q & A"));
        ProductBean productBean3 = new ProductBean("CSS", Arrays.asList("Basic", "Proficient", "Expert", "Test Yourself Q & A", "Interview Q & A"));
        ProductBean productBean4 = new ProductBean("JavaScript", Arrays.asList("Basic", "Proficient", "Expert", "Test Yourself Q & A", "Interview Q & A"));
        ProductBean productBean5 = new ProductBean("Spring", Arrays.asList("Basic", "Proficient", "Expert", "Test Yourself Q & A", "Interview Q & A"));
        ProductBean productBean6 = new ProductBean("Hibernate", Arrays.asList("Basic", "Proficient", "Expert", "Test Yourself Q & A", "Interview Q & A"));

        CategoryBean categoryBean1 = new CategoryBean("Languages",Arrays.asList(productBean1,productBean2,productBean3,productBean4));
        CategoryBean categoryBean2 = new CategoryBean("Frameworks",Arrays.asList(productBean5,productBean6));
        TopDrawerViewBean topDrawerViewBean = new TopDrawerViewBean(Arrays.asList(categoryBean1,categoryBean2));

        //database = FirebaseDatabase.getInstance();
        //myRef = database.getReference("DrawerSetup");
        //myRef.setValue(topDrawerViewBean);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Let's Code, a very cool and informative android application available in play store to download for free.\nCheck it out now.\n\nDownload link : https://play.google.com/store/apps/details?id=com.isabverma.letscode";
                String shareSub = "Let's Code - An Android Application";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(exit){
                finish();
            }else{
                Toast.makeText(this, "Press Back Again To Exit", Toast.LENGTH_SHORT).show();
                exit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exit = false;
                    }
                },2*1000);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Settings Selected..!!!", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.action_about_developers){
            Toast.makeText(this, "About Developers Selected..!!!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,MainIntroActivity.class);
            startActivity(intent);
        }else if(id == R.id.action_feedback){
            Toast.makeText(this, "Feedback Selected..!!!!!!!!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,FirebaseUIActivity.class);
            startActivity(intent);
        }else if(id == R.id.action_rate_us){ //for rate us
            Uri uri = Uri.parse("market://details?id=com.isabverma.letscode");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName()))); }
        }else if(id == R.id.action_share){
            Toast.makeText(this, "Share Selected..!!!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,WelcomeActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_container);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.content_main, null);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
