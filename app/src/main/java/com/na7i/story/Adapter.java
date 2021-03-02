package com.na7i.story;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.telecom.Call;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.PagerAdapter;

public class Adapter extends PagerAdapter {

    private List<Model> models;
    private LayoutInflater layoutInflater;
    private Context context;
    private String[] sTitle;
    private String[] sContent;
    WebView webView;
    private InterstitialAd mInterstitialAd;




    private String getUrl(String str) throws IOException{

        URL url=new URL(str);

        URLConnection urlc=url.openConnection();
        BufferedReader in =new BufferedReader(new InputStreamReader(urlc.getInputStream(),"UTF-8"));
        String inputLine;
        StringBuilder a = new StringBuilder();

        while ((inputLine=in.readLine()) !=null)
            a.append(inputLine);
        in.close();
        return a.toString();

    }
    public void getStory(String[] title,String[] content){
        sTitle=title;
        sContent =content;

    }


    public Adapter(List<Model> models, Context context) {
        this.models = models;
        this.context = context;
        this.layoutInflater=LayoutInflater.from(context);


    }


    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item,container,false);

        ImageView imageView;
        final TextView title,desc;
        imageView=view.findViewById(R.id.image);
        WebView webView=view.findViewById(R.id.contentOfStory);

        title =view.findViewById(R.id.title);
        desc = view.findViewById(R.id.desc);
        RelativeLayout relative=view.findViewById(R.id.relative);
        imageView.setImageResource(models.get(position).getImage());




        relative.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                for (int i=sTitle.length-1 ;i>=0;i--){
                if (position ==i){
                   Intent intent=new Intent(v.getContext(),Details.class);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("file:///android_asset/html/page");
                    stringBuilder.append(i);
                    stringBuilder.append(".html");



                    intent.putExtra("titleOfStory",sTitle[sTitle.length-1-i]);
                   intent.putExtra("contentOfStory",stringBuilder.toString() );
                   v.getContext().startActivity(intent);


                }


            }
        }});



        title.setText(models.get(position).getTitle());
        desc.setText(models.get(position).getDesc());

        container.addView(view,0);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }


}
