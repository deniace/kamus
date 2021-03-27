package com.deni.kamusbahasajawa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.deni.kamusbahasajawa.model.IndoJawa;
import com.deni.kamusbahasajawa.model.SharedPref;

public class MenuUtamaActivity extends AppCompatActivity {

    Button btnTerjemahkan, btnProfile, btnInfoBahasa, btnPetunjuk, btnKeluar;
    private SharedPref sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);

        // find button dengan id
        btnTerjemahkan =  findViewById(R.id.btn_terjemahan);
        btnProfile = findViewById(R.id.btn_profile);
        btnInfoBahasa = findViewById(R.id.btn_info_bahasa);
        btnPetunjuk = findViewById(R.id.btn_petunjuk);
        btnKeluar = findViewById(R.id.btn_keluar);

        // mengeset click listener button
        btnTerjemahkan.setOnClickListener(terjemahan);
        btnProfile.setOnClickListener(profile);
        btnInfoBahasa.setOnClickListener(infoBahasa);
        btnPetunjuk.setOnClickListener(petunjuk);
        btnKeluar.setOnClickListener(keluar);

        //menginisisasi sharef preference
        sharedPref = new SharedPref(MenuUtamaActivity.this);
    }

    View.OnClickListener terjemahan = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!sharedPref.isInit()){
                initiaalizeDatabase();
            }
            Intent i = new Intent(MenuUtamaActivity.this, TerjemahanActivity.class);
            startActivity(i);
        }
    } ;

    View.OnClickListener profile = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(MenuUtamaActivity.this, ProfileActivity.class);
            startActivity(i);
        }
    } ;

    View.OnClickListener infoBahasa = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(MenuUtamaActivity.this, InfoBahasaActivity.class);
            startActivity(i);
        }
    } ;

    View.OnClickListener petunjuk = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(MenuUtamaActivity.this, PetunjukActivity.class);
            startActivity(i);
        }
    } ;

    View.OnClickListener keluar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    } ;

    private void initiaalizeDatabase(){
        IndoJawa indoJawa = new IndoJawa("asu", "asu");
        indoJawa.save();
        indoJawa = new IndoJawa("test", "text");
        indoJawa.save();

        sharedPref.setIsInit(true);
    }

}
