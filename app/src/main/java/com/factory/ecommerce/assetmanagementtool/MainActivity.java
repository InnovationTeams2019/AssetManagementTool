package com.factory.ecommerce.assetmanagementtool;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.factory.ecommerce.assetmanagementtool.adapter.AssetAdapter;
import com.factory.ecommerce.assetmanagementtool.model.Asset;
import com.factory.ecommerce.assetmanagementtool.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView mOutputTextView;
    ProgressBar progressBar;
    GridView view;

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setNavigationViewListner();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mOutputTextView = findViewById(R.id.tv_google_output);
        progressBar = findViewById(R.id.pb_bar);

        view = findViewById(R.id.gv);


        FirebaseApp.initializeApp(this);
        loadContentOnUI(view);
    }

   /* private void setNavigationViewListner() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    */

    private void loadContentOnUI(final GridView view) {
        // Access a Cloud Firestore instance from your Activity
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        System.out.println("11111111111111111111111111111111111111111111");
        db.collection("Assets")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<Asset> assets = new ArrayList<>();
                        System.out.println("22222222222222222222222222222");
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> assetData = document.getData();
                                System.out.println(document.getId() + " => " + document.getData());

                                Asset asset = new Asset();
                                asset.setId(document.getId());
                                for (Map.Entry<String, Object> entry : assetData.entrySet()) {
                                    if (entry.getKey().equalsIgnoreCase("QtyInStock")) {
                                        asset.setQtyInStock((long) entry.getValue());
                                    } else if (entry.getKey().equalsIgnoreCase("TotalQty")) {
                                        asset.setTotalQty((long) entry.getValue());
                                    } else if (entry.getKey().equalsIgnoreCase("Name")) {
                                        asset.setName((String) entry.getValue());
                                    } else if (entry.getKey().equalsIgnoreCase("Description")) {
                                        asset.setDescription((String) entry.getValue());
                                    } else if (entry.getKey().equalsIgnoreCase("AssignmentDetail")) {
                                        List<User> users = new ArrayList<>();
                                        List<Map<String, Object>> assignmentsData = (List<Map<String, Object>>) entry.getValue();
                                        for (Map<String, Object> assignmentData : assignmentsData) {
                                            User user = new User();
                                            for (Map.Entry<String, Object> userdata : assignmentData.entrySet()) {
                                                if (userdata.getKey().equalsIgnoreCase("Name")) {
                                                    user.setName((String) userdata.getValue());
                                                } else if (userdata.getKey().equalsIgnoreCase("EnterpriseId")) {
                                                    user.setEnterpriseId((String) userdata.getValue());
                                                } else if (userdata.getKey().equalsIgnoreCase("ReturnDate")) {
                                                    user.setReturnDate(((Timestamp) userdata.getValue()).toDate());
                                                } else if (userdata.getKey().equalsIgnoreCase("AssignedDate")) {
                                                    user.setAssignmentDate(((Timestamp) userdata.getValue()).toDate());
                                                }
                                            }
                                            users.add(user);
                                        }
                                        asset.setUsers(users);
                                    }
                                }
                                assets.add(asset);
                            }
                            for (Asset asset : assets) {
                                System.out.println("******************* : " + asset);
                            }
                            AssetAdapter adapter = new AssetAdapter(MainActivity.this, assets);
                            view.setAdapter(adapter);
                        } else {
                            System.out.println("Couldn't fetch data from firebase store");
                        }


                    }
                });
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            //public void addAssetButtonClick(View view){
            Intent mintent = new Intent(MainActivity.this, AddAssetActivity.class);
            startActivity(mintent);
            //}
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
