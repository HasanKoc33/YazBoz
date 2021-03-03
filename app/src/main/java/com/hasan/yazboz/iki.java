package com.hasan.yazboz;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
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
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import com.google.android.gms.ads.InterstitialAd;


public class iki extends AppCompatActivity {
    private Button ekle, sıfır;
    private EditText p0, p1, t0, t1;
    private TextView s0, s1, t0p, t1p, s, fark, masaa;
    private AdView mAdView;
    private int s0t, s1t, sc, el = 0;
    private String masa_adi;
    private String f, win;
    Context context = this;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iki);

        Random r = new Random();
        init();
        Reklam();


        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ekle();
            }
        });
        sıfır.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sıfırlamaUyarusı();

            }
        });


        if (savedInstanceState != null) {
            t0.setText(savedInstanceState.getString("takım1"));
            t1.setText(savedInstanceState.getString("takım2"));
            s0t = savedInstanceState.getInt("1");
            s1t = savedInstanceState.getInt("2");
            s0.setText(String.valueOf(s0t));
            s1.setText(String.valueOf(s1t));
            t0p.setText(savedInstanceState.getString("t0p"));
            t1p.setText(savedInstanceState.getString("t1p"));
            s.setText(savedInstanceState.getString("s"));
            fark.setText(savedInstanceState.getString("fark"));
            masaa.setText("\n" + savedInstanceState.getString("masa"));
            masa_adi =  savedInstanceState.getString("masa");

        } else {

            el_sayisi();
            masa_adi = String.valueOf(r.nextInt(9999));
            masaa.append("\n" + masa_adi);
            yaz(masa_adi, "", "", "", "", "", "");
        }


    }

    private void init() {

        masaa = findViewById(R.id.masaaa);
        fark = findViewById(R.id.fark);
        p0 = findViewById(R.id.p0);
        p1 = findViewById(R.id.p1);
        t0 = findViewById(R.id.t0);
        t1 = findViewById(R.id.t1);
        ekle = findViewById(R.id.ekle);
        s0 = findViewById(R.id.s0);
        s1 = findViewById(R.id.s1);
        t0p = findViewById(R.id.t0p);
        t1p = findViewById(R.id.t1p);
        sıfır = findViewById(R.id.sss);
        s = findViewById(R.id.s);
    }

    private void ekle() {
        if (p0.getText().toString().trim().equals("")) {
            p0.setHint("Hani bana.");

        } else if (p1.getText().toString().trim().equals("")) {

            p1.setHint("Hani bana.");

        } else {

            t0p.append("\n" + p0.getText().toString());
            t1p.append("\n" + p1.getText().toString());
            int x = Integer.valueOf(p0.getText().toString());
            int y = Integer.valueOf(p1.getText().toString());
            s0t += x;
            s1t += y;
            yaz(masa_adi, "" + t0p.getText().toString(), "" + t1p.getText().toString(), "" + t0.getText().toString(), "" + t1.getText().toString(), "" + String.valueOf(s0t), "" + String.valueOf(s1t));
            sc++;
            f = "\n(" + sc + ")";
            s.append(f);
            f = Integer.toString(Math.abs(s0t - s1t));
            fark.setText(f);
            s0.setText(String.valueOf(s0t));
            s1.setText(String.valueOf(s1t));
            p0.setText("");
            p1.setText("");
            p0.setHint("puan");
            p1.setHint("puan");

            if (s0t < s1t) {
                win = t1.getText().toString();
                t0.setTextColor(Color.RED);
                t1.setTextColor(Color.GREEN);
            } else if (s0t > s1t) {
                win = t0.getText().toString();
                t0.setTextColor(Color.GREEN);
                t1.setTextColor(Color.RED);
            } else {
                win = "DOSTLUK";
                t0.setTextColor(Color.BLUE);
                t1.setTextColor(Color.BLUE);
            }
            if (sc == el) {
                kazananUyaısı();

            }

        }
    }


    public void yaz(String ıd, String k1, String k2, String a1, String a2, String t1, String t2) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        masa masa = new masa(k1, k2, a1, a2, t1, t2);
        myRef.child(ıd).setValue(masa);
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


                el = Integer.valueOf(0 + ella.getText().toString());

                if (el != 0)
                    Toast.makeText(context, "oyun " + el + " el olarak ayarlandı.", Toast.LENGTH_SHORT).show();
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

    private void reklamgec() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.gecis_reklam));

        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
            }
        });


    }

    public void kazananUyaısı() {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog);

        Button btnKaydet = (Button) dialog.findViewById(R.id.Evet);
        Button btnIptal = (Button) dialog.findViewById(R.id.iptal);
        TextView tvBaslik = (TextView) dialog.findViewById(R.id.textview_baslik);


        tvBaslik.setText("Kazanan\n");
        tvBaslik.setTextColor(Color.GREEN);
        tvBaslik.append(win);
        btnKaydet.setText("Yeni el");
        btnIptal.setText("Devam");


        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                s.setText("");
                fark.setText("");
                p0.setText("");
                p1.setText("");
                s0.setText("");
                s1.setText("");
                t0p.setText("");
                t1p.setText("");
                s0t = 0;
                s1t = 0;
                sc = 0;
                DatabaseReference ad0 = FirebaseDatabase.getInstance().getReference(masa_adi);
                ad0.removeValue();
                Toast.makeText(context, "Yeni el açıldı.", Toast.LENGTH_SHORT).show();
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

    public void sıfırlamaUyarusı() {

        if (s0.getText().toString() != "") {
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog);

            Button btnKaydet = (Button) dialog.findViewById(R.id.Evet);
            Button btnIptal = (Button) dialog.findViewById(R.id.iptal);
            TextView tvBaslik = (TextView) dialog.findViewById(R.id.textview_baslik);


            tvBaslik.setText("Sıfırlamak istediginizden\nEmin misiniz?");


            btnKaydet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    s.setText("");
                    fark.setText("");
                    p0.setText("");
                    p1.setText("");
                    s0.setText("");
                    s1.setText("");
                    t0p.setText("");
                    t1p.setText("");
                    t0.setTextColor(Color.BLACK);
                    t1.setTextColor(Color.BLACK);
                    s0t = 0;
                    s1t = 0;
                    sc = 0;
                    DatabaseReference ad0 = FirebaseDatabase.getInstance().getReference(masa_adi);
                    ad0.removeValue();
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
        } else {

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
                DatabaseReference ad0 = FirebaseDatabase.getInstance().getReference().child(masa_adi);
                ad0.removeValue();
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (s0.getText().toString() != "")
                geriUyarusi();

            else {
                DatabaseReference ad0 = FirebaseDatabase.getInstance().getReference().child(masa_adi);
                ad0.removeValue();
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

    @Override
    protected void onDestroy() {
        if (masa_adi!=null) {
            DatabaseReference ad0 = FirebaseDatabase.getInstance().getReference().child(masa_adi);
            ad0.removeValue();
        }
        super.onDestroy();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        t0.setText(savedInstanceState.getString("takım1"));
        t1.setText(savedInstanceState.getString("takım2"));
        s0t = savedInstanceState.getInt("1");
        s1t = savedInstanceState.getInt("2");
        s0.setText(String.valueOf(s0t));
        s1.setText(String.valueOf(s1t));
        t0p.setText(savedInstanceState.getString("t0p"));
        t1p.setText(savedInstanceState.getString("t1p"));
        s.setText(savedInstanceState.getString("s"));
        fark.setText(savedInstanceState.getString("fark"));
        masaa.setText("\n" + savedInstanceState.getString("masa"));

    }

    // invoked when the activity may be temporarily destroyed, save the instance state here
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("1", s0t);
        outState.putInt("2", s1t);
        outState.putString("takım1", t0.getText().toString());
        outState.putString("takım2", t1.getText().toString());
        outState.putString("t0p", t0p.getText().toString());
        outState.putString("t1p", t1p.getText().toString());
        outState.putString("s", s.getText().toString());
        outState.putString("fark", fark.getText().toString());
        outState.putString("masa", masa_adi);

        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }


}
