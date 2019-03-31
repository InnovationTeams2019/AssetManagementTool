package com.factory.ecommerce.assetmanagementtool.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.factory.ecommerce.assetmanagementtool.AssetDetailActivity;
import com.factory.ecommerce.assetmanagementtool.R;
import com.factory.ecommerce.assetmanagementtool.helper.AssetHelper;
import com.factory.ecommerce.assetmanagementtool.model.Asset;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssetAdapter extends ArrayAdapter<Asset> {
    Context context;
    private List<Asset> assets;
    private int lastPosition = -1;


    public AssetAdapter(Context context, List<Asset> items) {
        super(context, R.layout.activity_listview, items);
        this.context = context;
        this.assets = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        {
            View v = convertView;
            if (v == null) {
                v = LayoutInflater.from(context).inflate(R.layout.activity_listview,parent,false);
            }
            final Asset asset = assets.get(position);
            if (asset != null) {
                TextView pos = (TextView) v.findViewById(R.id.assetName);
                ImageView imgview = v.findViewById(R.id.assetImg);

                pos.setText(String.valueOf(asset.getName()));
                imgview.setImageResource(AssetHelper.fetchImageId(asset.getId().toLowerCase()));
                System.out.println("###################### "+asset.getUsers().toString());

            }

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), AssetDetailActivity.class);
                    intent.putExtra("asset", asset);
                    view.getContext().startActivity(intent);
                }
            });

            return v;
        }
    }
}
