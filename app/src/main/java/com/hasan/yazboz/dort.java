package com.hasan.yazboz;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
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

public class dort extends AppCompatActivity {
    Button ekle,sıfır;
    EditText p0,p1,p2,p3,t0,t1,t2,t3;
    TextView s0,s1,s2,s3,t0p,t1p,t2p,t3p,els4,els4top;
    int s0t,s1t,s2t,s3t,sc,el=0;
    String win;
    private AdView mAdView;
    Context context = this;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dort);

        Reklam();
        el_sayisi();



        p2=findViewById(R.id.p2);
        p0=findViewById(R.id.p0);
        p1=findViewById(R.id.p1);
        p3=findViewById(R.id.p3);
        t0=findViewById(R.id.t0);
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        s0= findViewById(R.id.s0);
        s1=findViewById(R.id.s1);
        s2=findViewById(R.id.s2);
        s3=findViewById(R.id.s3);
        t0p=findViewById(R.id.t0p);
        t1p=findViewById(R.id.t1p);
        t2p=findViewById(R.id.t2p);
        t3p=findViewById(R.id.t3p);
        els4top=findViewById(R.id.els4top);
        els4=findViewById(R.id.els4);


        ekle=findViewById(R.id.ekle);
        sıfır=findViewById(R.id.sss);





        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(p0.getText().toString().trim().equals("") ){
                    p0.setHint("Hani bana.");

                }
                else if (p1.getText().toString().trim().equals("")){

                    p1.setHint("Hani bana.");

                }
                else if (p2.getText().toString().trim().equals("")){

                    p2.setHint("Hani bana.");

                }
                else if (p3.getText().toString().trim().equals("")){

                    p3.setHint("Hani bana.");

                }
                else {
                    int x = Integer.valueOf (p0.getText().toString());
                    int y = Integer.valueOf (p1.getText().toString());
                    int z = Integer.valueOf (p2.getText().toString());
                    int w = Integer.valueOf (p3.getText().toString());
                    sc++;
                    String f ="\n"+ sc+")";
                    els4.append(f);
                    els4top.setText(String.valueOf(sc)+")");
                    s0t += x;
                    s1t += y;
                    s2t += z;
                    s3t += w;
                    s0.setText(String.valueOf(s0t));
                    s1.setText(String.valueOf(s1t));
                    s2.setText(String.valueOf(s2t));
                    s3.setText(String.valueOf(s3t));
                    t0p.append("\n"+p0.getText().toString());
                    t1p.append("\n"+p1.getText().toString());
                    t2p.append("\n"+p2.getText().toString());
                    t3p.append("\n"+p3.getText().toString());
                    p0.setText("");
                    p1.setText("");
                    p2.setText("");
                    p3.setText("");
                    p0.setHint("puan");
                    p1.setHint("puan");
                    p2.setHint("puan");
                    p3.setHint("puan");

                    if (s0t<s3t&& s1t<s3t&& s2t<s3t) {
                                win=t3.getText().toString();
                                t3.setTextColor(Color.GREEN);
                                t2.setTextColor(Color.RED);
                                t0.setTextColor(Color.RED);
                                t1.setTextColor(Color.RED);
                    }
                    else if (s0t<s2t&& s1t<s2t&& s3t<s2t) {
                        win=t2.getText().toString();
                            t2.setTextColor(Color.GREEN);
                            t3.setTextColor(Color.RED);
                            t0.setTextColor(Color.RED);
                            t1.setTextColor(Color.RED);
                    }
                     else if (s0t<s1t&& s2t<s1t&& s3t<s1t) {
                        win=t1.getText().toString();
                            t1.setTextColor(Color.GREEN);
                            t2.setTextColor(Color.RED);
                            t0.setTextColor(Color.RED);
                            t3.setTextColor(Color.RED);
                    }
                    else if (s1t<s0t&& s2t<s0t&& s3t<s0t) {
                        win=t0.getText().toString();
                            t0.setTextColor(Color.GREEN);
                            t2.setTextColor(Color.RED);
                            t3.setTextColor(Color.RED);
                            t1.setTextColor(Color.RED);
                    }
                    else {
                        win="DOSTLUK";
                        t0.setTextColor(Color.BLUE);
                        t2.setTextColor(Color.BLUE);
                        t3.setTextColor(Color.BLUE);
                        t1.setTextColor(Color.BLUE);
                    }
                    if (sc==el){
                        kazananUyaısı();

                    }
                }
            }
        });
        sıfır.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sıfırlamaUyarusı();



            }
        });






    }


    public void kazananUyaısı() {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog);

        Button btnKaydet = (Button) dialog.findViewById(R.id.Evet);
        Button btnIptal = (Button) dialog.findViewById(R.id.iptal);
        TextView tvBaslik = (TextView) dialog.findViewById(R.id.textview_baslik);


        tvBaslik.setText("Kazanan\n"+win);
        btnKaydet.setText("Yeni el");
        tvBaslik.setTextColor(Color.GREEN);
        btnIptal.setText("Devam");



        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                p0.setText("");
                p1.setText("");
                p2.setText("");
                p3.setText("");
                s0.setText("");
                s1.setText("");
                s2.setText("");
                s3.setText("");
                t0p.setText("");
                t1p.setText("");
                t2p.setText("");
                t3p.setText("");
                els4.setText("");
                els4top.setText("");
                t0.setTextColor(Color.BLACK);
                t1.setTextColor(Color.BLACK);
                t2.setTextColor(Color.BLACK);
                t3.setTextColor(Color.BLACK);
                s0t =0;
                s1t=0;
                s2t=0;
                s3t=0;
                sc=0;


                Toast.makeText(context, "Yeni Oyun açıldı.", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                reklamgec();
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

    public void el_sayisi() {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.eldialogu);


        Button btnKaydet = (Button) dialog.findViewById(R.id.Tamam);
        Button iptal = (Button) dialog.findViewById(R.id.iptal);
        final EditText ella = (EditText) dialog.findViewById(R.id.el);

        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                el =Integer.valueOf(0+ella.getText().toString());

                if(el!=0)
                    Toast.makeText(context, "oyun "+el+" el olarak ayarlandı.", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        iptal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void sıfırlamaUyarusı() {
        if (s0.getText().toString()!=""){
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog);

            Button btnKaydet = (Button) dialog.findViewById(R.id.Evet);
            Button btnIptal = (Button) dialog.findViewById(R.id.iptal);
            TextView tvBaslik = (TextView) dialog.findViewById(R.id.textview_baslik);


            tvBaslik.setText("Sıfırlamak istediginizden\nEmin misiniz?");



            btnKaydet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    p0.setText("");
                    p1.setText("");
                    p2.setText("");
                    p3.setText("");
                    s0.setText("");
                    s1.setText("");
                    s2.setText("");
                    s3.setText("");
                    t0p.setText("");
                    t1p.setText("");
                    t2p.setText("");
                    t3p.setText("");
                    els4.setText("");
                    els4top.setText("");
                    t0.setTextColor(Color.BLACK);
                    t1.setTextColor(Color.BLACK);
                    t2.setTextColor(Color.BLACK);
                    t3.setTextColor(Color.BLACK);
                    s0t =0;
                    s1t=0;
                    s2t=0;
                    s3t=0;
                    sc=0;

                    Toast.makeText(context, "Skorlarınız Sıfırlandı.", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    reklamgec();
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


        else{

            Toast.makeText(context, "Yorma beni.", Toast.LENGTH_SHORT).show();

        }

    }

    public void geriUyarusi() {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog);

        Button btnKaydet = (Button) dialog.findViewById(R.id.Evet);
        Button btnIptal = (Button) dialog.findViewById(R.id.iptal);
        TextView tvBaslik = (TextView) dialog.findViewById(R.id.textview_baslik);


        tvBaslik.setText("Çıkarsanız puanlarınız silinecek\nEmin misiniz?");



        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Görüşmek üzere.", Toast.LENGTH_SHORT).show();
                finish();
                dialog.dismiss();
                reklamgec();
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


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK ){
            if (s0.getText().toString()!="")
                geriUyarusi();

            else{

                Toast.makeText(context, "Görüşmek üzere.", Toast.LENGTH_SHORT).show();
                finish();
                reklamgec();
            }
        }
        return false;


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

}
