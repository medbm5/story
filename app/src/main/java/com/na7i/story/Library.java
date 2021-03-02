package com.na7i.story;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class Library extends AppCompatActivity {

    ImageView library,read,share,rate,support,privacy;
    Intent mIntent;
    private AdView mAdView;
    String url1="https://play.google.com/store/apps/details?id=com.instagram.android";
    String url2="https://play.google.com/store/apps/details?id=com.linkedin.android";
    String url3="https://play.google.com/store/apps/details?id=deezer.android.app";
    String url4="https://play.google.com/store/apps/details?id=com.soundcloud.android";
    String email="medbaamrani0@gmail.com";
    String url6="https://play.google.com/store/apps/details?id=org.wikipedia";
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onPostResume() {

        mAdView = findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        super.onPostResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);


        //interstisial ad that shows when I click on start menu
        MobileAds.initialize(this,
                getString(R.string.app_id));
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        //banner in start menu
        MobileAds.initialize(this,
                "ca-app-pub-3940256099942544~3347511713");
        mAdView = findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        library=(ImageView) findViewById(R.id.library);
        read=(ImageView) findViewById(R.id.read);
        share=(ImageView) findViewById(R.id.share);
        rate=(ImageView) findViewById(R.id.rate);
        support=(ImageView) findViewById(R.id.support);
        privacy=(ImageView) findViewById(R.id.privacy);



        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Uri uri1 = Uri.parse(url1);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri1);
                    startActivity(intent);
               }
            });



        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mInterstitialAd.isLoaded()){
                    mInterstitialAd.show();
                    mInterstitialAd.setAdListener(new AdListener(){

                        public void onAdClosed(){
                            Intent toread = new Intent(Library.this,MainActivity.class);
                            startActivity(toread);
                        }

                    });

                }else{
                    Intent toread = new Intent(Library.this,MainActivity.class);
                    startActivity(toread);



                }


            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(Intent.ACTION_SEND);
                mIntent.setType("text/plain");
                String shareBody = "Your body";
                String shareSub = "Your Subject";
                mIntent.putExtra(Intent.EXTRA_SUBJECT, shareBody);
                mIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(mIntent, "Share using"));
            }
        });
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri4=Uri.parse(url4);
                Intent intent =new Intent(Intent.ACTION_VIEW,uri4);
                startActivity(intent);
            }
        });
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("android.intent.action.SENDTO");
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra("android.intent.extra.EMAIL", new String[]{"medbaamrani0@gmail.com"});
                intent.putExtra("android.intent.extra.SUBJECT", getString(R.string.app_name));
                startActivity(Intent.createChooser(intent, "Email via..."));
                }
            }
        );
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mInterstitialAd.isLoaded()){
                    mInterstitialAd.show();
                    mInterstitialAd.setAdListener(new AdListener(){

                        public void onAdClosed(){
                            Intent toprivacy = new Intent(Library.this,privacy.class);
                            startActivity(toprivacy);
                        }

                    });

                }else{
                    Intent toprivacy = new Intent(Library.this,privacy.class);
                    startActivity(toprivacy);
                }
            }
        });


























    }
}
