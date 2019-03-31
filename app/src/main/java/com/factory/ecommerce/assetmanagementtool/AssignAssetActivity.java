package com.factory.ecommerce.assetmanagementtool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.factory.ecommerce.assetmanagementtool.helper.AssetHelper;
import com.factory.ecommerce.assetmanagementtool.model.Asset;
import com.factory.ecommerce.assetmanagementtool.model.User;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

//import androidlabs.gsheets2.R;

public class AssignAssetActivity extends AppCompatActivity {

    EditText nameEditText, eIdEditText;
    TextView errorNameText, errorEidText, retunDateText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignasset);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nameEditText = findViewById(R.id.name);
        eIdEditText = findViewById(R.id.eid);
        errorNameText = findViewById(R.id.error_name);
        errorEidText = findViewById(R.id.error_eid);
        retunDateText = findViewById(R.id.return_date);
        button = findViewById(R.id.button1);

        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 14);
        final String returnDate = AssetHelper.convertCalendarToString(calendar);
        retunDateText.setText(returnDate);

        Bundle bundle = getIntent().getExtras();
        final Asset asset = bundle.getParcelable("asset");
        System.out.println("*********** asset assign activity ::: "+asset.toString());

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String name = nameEditText.getText().toString();
                String eid = eIdEditText.getText().toString();


                if(null == name || name.isEmpty()){
                    errorNameText.setText("Please enter the employee Name");

                }else if(null == eid || eid.isEmpty()){
                    errorEidText.setText("Please enter the enterprise Id");
                }else {
                    asset.setQtyInStock(asset.getQtyInStock() - 1);
                    List<User> users = asset.getUsers();
                    User user = new User();
                    user.setName(name);
                    user.setEnterpriseId(eid);
                    user.setAssignmentDate(Calendar.getInstance().getTime());
                    user.setReturnDate(calendar.getTime());

                    users.add(user);

                    assignAsset(asset);

                }


            }


        });


    }

    private void assignAsset (Asset asset){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference ref = db.collection("Assets").document(asset.getId());

        Map<String, Object> newUser = new HashMap<>();
        newUser.put("AssignmentDetail", asset.getUsers());

        Map<String, Object> updatedQtyInStock = new HashMap<>();
        updatedQtyInStock.put("QtyInStock", asset.getQtyInStock());

        ref.set(newUser, SetOptions.merge());
        ref.set(updatedQtyInStock, SetOptions.merge());

        Toast.makeText(AssignAssetActivity.this,"Asset is assigned successfully", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }
}


