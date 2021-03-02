package com.na7i.story;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
    private InterstitialAd mInterAd;

    private AdView mAdView;
    ViewPager viewPager;
    int indice;
    Adapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    String[] titles, contents;
    Intent in;
    ImageView share, Library, rate,right,left;
    Intent mIntent, lib;
    EditText mEditText;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";

    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        titles = getResources().getStringArray(R.array.stories_title);
        contents = getResources().getStringArray(R.array.story_content);

        left = (ImageView) findViewById(R.id.left);
        right = (ImageView) findViewById(R.id.right);






        ///ad intert
            MobileAds.initialize(this,
                    getString(R.string.app_id));
            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId(getString(R.string.interstitial_id));
            mInterstitialAd.loadAd(new AdRequest.Builder().build());

        ///ad intert
        MobileAds.initialize(this,
                getString(R.string.app_id));
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        MobileAds.initialize(this,
                getString(R.string.app_id));
        mInterAd = new InterstitialAd(this);
        mInterAd.setAdUnitId(getString(R.string.interstitial_id));
        mInterAd.loadAd(new AdRequest.Builder().build());
        //ad banner
        MobileAds.initialize(this,
                "ca-app-pub-3940256099942544~3347511713");
        mAdView = findViewById(R.id.adView4);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        models = new ArrayList<>();
        for (int i = titles.length - 1; i >= 0; i--) {
            models.add(new Model(R.drawable.brochure, "Part" + (i + 1), ""));
        }
        SharedPreferences sharedPreferences =getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        index =sharedPreferences.getInt(TEXT,titles.length - 1);

        adapter = new com.na7i.story.Adapter(models,this);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);
        adapter.getStory(titles, contents);
        viewPager.setCurrentItem(index);


        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //indice=Integer.valueOf(mEditText.getText().toString());
                int i = viewPager.getCurrentItem();
                if (i - 10 >= 0) {
                    viewPager.setCurrentItem(i - 10);
                } else if (i == 0) {

                    viewPager.setCurrentItem(contents.length - 1);
                } else {
                    viewPager.setCurrentItem(0);
                }

            }
        });
        MobileAds.initialize(this,
                "ca-app-pub-3940256099942544~3347511713");
        mAdView = findViewById(R.id.adView4);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest2);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i = viewPager.getCurrentItem();
                if (i + 10 <= titles.length - 1) {
                    viewPager.setCurrentItem(i + 10);
                } else if (i == titles.length - 1) {

                    viewPager.setCurrentItem(0);
                } else {
                    viewPager.setCurrentItem(titles.length - 1);
                }

            }
        });

        Integer[] colors_temp = {
                getResources().getColor(R.color.purple),


        };
        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adapter.getCount() - 1) && position < (colors.length - 1)) {

                    viewPager.setBackgroundColor(
                            (Integer) argbEvaluator.evaluate(

                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );

                } else {

                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }





            @Override
            public void onPageSelected(int position) {
                SharedPreferences sharedPreferences =getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putInt(TEXT,position);
                editor.apply();
                Toast.makeText(getApplicationContext(),"data saved",Toast.LENGTH_SHORT).show();
                if(position%5==0){
                    if (mInterAd.isLoaded()){
                        mInterAd.show();
                        mInterAd = new InterstitialAd(getApplicationContext());
                        mInterAd.setAdUnitId(getString(R.string.interstitial_id));
                        mInterAd.loadAd(new AdRequest.Builder().build());
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });


    }
    @Override
    public void onBackPressed() {

        mInterstitialAd.show();
        super.onBackPressed();

    }


    }
