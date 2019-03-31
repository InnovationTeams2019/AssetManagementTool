package com.factory.ecommerce.assetmanagementtool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class AddAssetActivity extends MainActivity {

    ImageView imgView;
    TextView assetNameText,quantity,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_assetl);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*imgView = findViewById(R.id.assetImg);
        assetNameText = findViewById(R.id.assetName);
        quantity = findViewById(R.id.quantity);
        description = findViewById(R.id.desc);

        //RECEIVE OUR DATA
        Intent i=getIntent();

        final String name=i.getExtras().getString("assetName");
        final String id = i.getExtras().getString("desc");
        final String totquantity = i.getExtras().getString("quantity");
        final int imageId= Integer.parseInt(id);

        assetNameText.setText(name);
        imgView.setImageResource(imageId);
        quantity.setText("Total Quantity in Stock :" + totquantity + "\n \n " + "Availaible Quantity :" + totquantity);
*/
    }
}


