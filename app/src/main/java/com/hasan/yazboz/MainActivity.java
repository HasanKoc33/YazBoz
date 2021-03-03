package com.hasan.yazboz;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.nio.file.Files;

public class MainActivity extends AppCompatActivity {

    private Button b,got,dort,bag,uzanti;
    private AdView mAdView;
    public  String isim;
    Context context= this;
    boolean logo = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Reklam();
        b = findViewById(R.id.iki);
        got = findViewById(R.id.uc);
        dort = findViewById(R.id.dort);
        bag = findViewById(R.id.bag);
        uzanti =findViewById(R.id.uzanti);



        uzanti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url(getString(R.string.tanitim));
            }
        });





        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecisYap = new Intent(MainActivity.this, iki.class);
                startActivity(gecisYap);
            }
        });

        got.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent gecisYap = new Intent(MainActivity.this, uc.class);
            startActivity(gecisYap);
        }
        });

        dort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecisYap = new Intent(MainActivity.this, dort.class);
                startActivity(gecisYap);
            }
                               });

        bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                masaKayit();
            }
        });
    }

    public void url(String url){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);

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

    public void masaKayit() {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.masagir);

        Button btnKaydet = (Button) dialog.findViewById(R.id.Evet);
        Button btnIptal = (Button) dialog.findViewById(R.id.iptal);
        final EditText ella = (EditText) dialog.findViewById(R.id.el);

        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                isim =""+ ella.getText().toString();
                if(isim==""){
                    Toast.makeText(context, "Yorma beni", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else {
                    SharedPreferences ayarlar = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = ayarlar.edit();


                    editor.putString("masa", isim);
                    editor.commit();

                    Toast.makeText(context, "Baglanılıyor", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    Intent gecisYap = new Intent(MainActivity.this, baglan.class);
                    startActivity(gecisYap);
                }
            }
        });

        btnIptal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK ){
            moveTaskToBack(true);
            finish();
        }
        return false;

    }

}

