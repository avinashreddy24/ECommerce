package com.aug22.avinashchintareddy.ecommerce.Displayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.aug22.avinashchintareddy.ecommerce.intro.SecondActivity;
import com.aug22.avinashchintareddy.ecommerce.fragments.CategoriesF;
import com.aug22.avinashchintareddy.ecommerce.fragments.CartF;
import com.aug22.avinashchintareddy.ecommerce.fragments.OrderHistoryF;
import com.aug22.avinashchintareddy.ecommerce.fragments.ProductDetailF;
import com.aug22.avinashchintareddy.ecommerce.fragments.ProductF;
import com.aug22.avinashchintareddy.ecommerce.R;
import com.aug22.avinashchintareddy.ecommerce.fragments.SubCatF;
import com.aug22.avinashchintareddy.ecommerce.interfaces.orderHistInter;
import com.aug22.avinashchintareddy.ecommerce.interfaces.proddetailsender;
import com.aug22.avinashchintareddy.ecommerce.interfaces.prodsender;
import com.aug22.avinashchintareddy.ecommerce.interfaces.senders;
import com.aug22.avinashchintareddy.ecommerce.interfaces.subCatsender;

public class ShopList extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,senders, subCatsender, prodsender, proddetailsender,orderHistInter {
    Fragment categories;
    Fragment subcategories;
    Fragment products;
    Fragment prodetail;
    Fragment orderdetails;
    Fragment orderhistroy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailer);
        categories= new CategoriesF();
        subcategories= new SubCatF();
        products= new ProductF();
        prodetail= new ProductDetailF();
        orderdetails= new CartF();
        orderhistroy= new OrderHistoryF();


        /*FragmentManager fg1= getSupportFragmentManager();
        FragmentTransaction ft1=fg1.beginTransaction();
        ft1.add(R.id.lyt_detail,categories);*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fg1= getSupportFragmentManager();
        FragmentTransaction ft1=fg1.beginTransaction();
        ft1.add(R.id.lyt_detail,categories);
        ft1.commit();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detailer, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {
            Intent intent= new Intent(ShopList.this,SecondActivity.class);
            startActivity(intent);

        }  else if (id == R.id.nav_manage) {
            FragmentManager fg1= getSupportFragmentManager();
            FragmentTransaction ft1=fg1.beginTransaction();
            ft1.replace(R.id.lyt_detail,orderdetails);
            ft1.commit();


        } else if (id == R.id.nav_share) {
            FragmentManager fg1= getSupportFragmentManager();
            FragmentTransaction ft1=fg1.beginTransaction();
            ft1.replace(R.id.lyt_detail,orderhistroy);
            ft1.commit();


        } else if (id == R.id.nav_send) {
            FragmentManager fg1= getSupportFragmentManager();
            FragmentTransaction ft1=fg1.beginTransaction();
            ft1.replace(R.id.lyt_detail,categories);
            ft1.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void sent(String s) {
        Bundle bundle= new Bundle();
        bundle.putString("value",s);
        subcategories.setArguments(bundle);
        FragmentManager fg1= getSupportFragmentManager();
        FragmentTransaction ft1=fg1.beginTransaction();
        ft1.replace(R.id.lyt_detail,subcategories);
        ft1.commit();
    }

    @Override
    public void subCatsent(String s) {
        Bundle bundle= new Bundle();
        bundle.putString("subid",s);
        products.setArguments(bundle);
        FragmentManager fg1= getSupportFragmentManager();
        FragmentTransaction ft1=fg1.beginTransaction();
        ft1.replace(R.id.lyt_detail,products);
        ft1.commit();

    }

    @Override
    public void detsent(String name, String Image, String desc, String prize, String quantity,String id) {
        Bundle bundle= new Bundle();
        bundle.putString("protitle",name);
        bundle.putString("prodesc",desc);
        bundle.putString("pprize",prize);
        bundle.putString("proqt",quantity);
        bundle.putString("proimage",Image);
        bundle.putString("proid",id);

        prodetail.setArguments(bundle);
        FragmentManager fg1= getSupportFragmentManager();
        FragmentTransaction ft1=fg1.beginTransaction();
        ft1.replace(R.id.lyt_detail,prodetail);
        ft1.commit();

    }

    @Override
    public void psent() {
        FragmentManager fg1= getSupportFragmentManager();
        FragmentTransaction ft1=fg1.beginTransaction();
        ft1.replace(R.id.lyt_detail,orderdetails);
        ft1.commit();

    }

    @Override
    public void osent() {
        FragmentManager fg1= getSupportFragmentManager();
        FragmentTransaction ft1=fg1.beginTransaction();
        ft1.replace(R.id.lyt_detail,orderhistroy);
        ft1.commit();

    }
}
