package com.factory.ecommerce.assetmanagementtool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.factory.ecommerce.assetmanagementtool.helper.AssetHelper;
import com.factory.ecommerce.assetmanagementtool.model.Asset;

public class AssetDetailActivity extends AppCompatActivity {

    ImageView imgView;
    TextView assetNameText,quantity;
    Button assignBtn;
    Button returnBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgView = findViewById(R.id.assetImg);
        assetNameText = findViewById(R.id.assetName);
        quantity = findViewById(R.id.quantity);
        assignBtn = (Button) findViewById(R.id.assign_btn);
        returnBtn = (Button) findViewById(R.id.return_btn);

        //RECEIVE OUR DATA
        Bundle bundle = getIntent().getExtras();
        final Asset asset = bundle.getParcelable("asset");
        System.out.println("*********** asset detail activity ::: "+asset.toString());
        assetNameText.setText(asset.getName());
        imgView.setImageResource(AssetHelper.fetchImageId(asset.getId().toLowerCase()));
        quantity.setText("Total Quantity :" + asset.getTotalQty() + "\n \n " + "Availaible Quantity :" + asset.getQtyInStock());

        assignBtn.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                long totalQty = asset.getTotalQty();
                long qtyInStock = asset.getQtyInStock();

                if(totalQty >= qtyInStock+1){
                    Intent intent = new Intent(v.getContext(), AssignAssetActivity.class);
                    intent.putExtra("asset", asset);
                    v.getContext().startActivity(intent);
                } else {
                    Toast.makeText(AssetDetailActivity.this, "Device is not available in stock", Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}
