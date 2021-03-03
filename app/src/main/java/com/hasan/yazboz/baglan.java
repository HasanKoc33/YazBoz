package com.hasan.yazboz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class baglan extends AppCompatActivity {

    Editable masa;

    TextView tb,tb2,a1,a2,top0,top1,fark,masaaa;
    int sayac=0;
    String isim;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baglan);



        tb = findViewById(R.id.tb);
        tb2 = findViewById(R.id.tb2);
        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        top0=findViewById(R.id.top0);
        top1=findViewById(R.id.top1);
        fark=findViewById(R.id.fark);
        masaaa=findViewById(R.id.masaaa);
        Reklam();
        SharedPreferences ayarlar = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        isim =ayarlar.getString("masa","");
        masaaa.append("\n"+isim);

            DatabaseReference ad = FirebaseDatabase.getInstance().getReference().child(isim);

            ValueEventListener dinle = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    try {
                        masa m = new masa();
                        m = dataSnapshot.getValue(masa.class);
                        if (m != null) {
                            sayac++;
                            tb.setText(m.k1 + "\n");
                            tb2.setText(m.k2 + "\n");
                            a1.setText(m.a1);
                            a2.setText(m.a2);
                            top0.setText(m.t1);
                            top1.setText(m.t2);
                            int f = Math.abs(0+Integer.valueOf(top0.getText().toString())-Integer.valueOf(top1.getText().toString()));
                            fark.setText(String.valueOf(f));
                            masaaa.setText("MASA\n"+isim);
                        }
                        if (sayac == 0 && m == null) {
                            masaaa.setText("bu isimde masa bulunamadı.");
                        }

                    }catch (Exception e ){

                    }



                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            ad.addValueEventListener(dinle);




    }
    private void Reklam() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(getString(R.string.banner_ad_unit_id));

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


    }
    private void reklamgec(){
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.gecis_reklam));

        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
            }
        });


    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK ){
          reklamgec();
                finish();

        }
        return false;

    }
}
