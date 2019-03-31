package com.factory.ecommerce.assetmanagementtool.helper;

import com.factory.ecommerce.assetmanagementtool.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AssetHelper {

    static private  Map<String,Integer> imageMap = null;

    public static int fetchImageId(String assetName){
        if(null == imageMap){
            fillImageData();
        }

        return imageMap.get(assetName) == null? R.drawable.ic_menu_camera : imageMap.get(assetName);
    }

    final static private void fillImageData(){
        imageMap = new HashMap<>();
        imageMap.put("iotbutton", R.drawable.iotbutton);
        imageMap.put("lego",R.drawable.lego);
        imageMap.put("iphone",R.drawable.iphone);
        imageMap.put("ipad",R.drawable.ipad);
        imageMap.put("applewatch",R.drawable.applewatch);
        imageMap.put("beacon",R.drawable.beacon);
        imageMap.put("googlehome",R.drawable.googlehome);
        imageMap.put("ipadc",R.drawable.ipadc);
        imageMap.put("laser",R.drawable.laser);
        imageMap.put("alexa",R.drawable.smallalexa);
        imageMap.put("surfacetabs",R.drawable.surfacetabs);
        imageMap.put("gopro",R.drawable.gopro);
        imageMap.put("holo",R.drawable.holo);
        imageMap.put("bigalex",R.drawable.bigalex);
        imageMap.put("chrome",R.drawable.chrome);
        imageMap.put("dellvr",R.drawable.dellvr);
        imageMap.put("dura",R.drawable.dura);
        imageMap.put("gds",R.drawable.gds);
        imageMap.put("googledd",R.drawable.googledd);
        imageMap.put("hdmi",R.drawable.hdmi);
        imageMap.put("hdmic",R.drawable.hdmic);
        imageMap.put("hdmiswitch",R.drawable.hdmiswitch);
        imageMap.put("projectorpen",R.drawable.projectorpen);
        imageMap.put("rb",R.drawable.rb);
        imageMap.put("rpkit",R.drawable.rpkit);
        imageMap.put("sn",R.drawable.sn);
        imageMap.put("sp",R.drawable.sp);
        imageMap.put("tvremote",R.drawable.tvremote);
        imageMap.put("wirelesskb",R.drawable.wirelesskb);
    }

    public static String convertCalendarToString(Calendar cal){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return sdf.format(cal.getTime());
    }

}
