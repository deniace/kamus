package com.deni.kamusbahasajawa;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.deni.kamusbahasajawa.model.IndoJawa;

public class TerjemahanActivity extends AppCompatActivity {

    TextInputEditText textBhsIndo, textBhsJawa;
    String bhsIndo, bhsJawa, reverse = "";
    Button btnTransate, btnClear, btnTambah;
    LinearLayout linearLayout;
    private IndoJawa indoJawa = null;
    TextView textErrorTerjemahan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terjemahan);
        linearLayout =  findViewById(R.id.linear_main);
        textBhsIndo = findViewById(R.id.text_bhs_indo);
        textBhsJawa = findViewById(R.id.text_bhs_jawa);
        btnTransate = findViewById(R.id.btn_translate);
        btnClear = findViewById(R.id.btn_clear);
        btnClear.setVisibility(View.GONE);
        btnTambah = findViewById(R.id.btn_tambah_terjemahan);
        btnTambah.setVisibility(View.GONE);
        textErrorTerjemahan = findViewById(R.id.text_error_terjemahan);
        textErrorTerjemahan.setVisibility(View.GONE);

        textBhsIndo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    textBhsJawa.setText("");
                }
                return false;
            }
        });

        textBhsJawa.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    textBhsIndo.setText("");
                }
                return false;
            }
        });

        btnTransate.setOnClickListener(transate);
        btnClear.setOnClickListener(clear);
    }

    View.OnClickListener transate = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(textBhsIndo.getText().toString().equals("*#0000#*")){
                Intent intent = new Intent(TerjemahanActivity.this, ListActivity.class);
                startActivity(intent);
            }else {
                bhsIndo = textBhsIndo.getText().toString();
                indoJawa = new Select().from(IndoJawa.class).where("indo = ?", bhsIndo).executeSingle();
                if(indoJawa != null){
                    textBhsJawa.setText(indoJawa.getJawa());
                    textErrorTerjemahan.setVisibility(View.GONE);
                }else {
                    Toast.makeText(TerjemahanActivity.this,"Bahasa yang anda cari belum ada di database kami",Toast.LENGTH_SHORT).show();

                    textErrorTerjemahan.setVisibility(View.VISIBLE);
                }


            }
        }
    };


//    View.OnClickListener transate = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//            if (!textBhsIndo.getText().toString().equals("")) {
//                bhsIndo = textBhsIndo.getText().toString();
//                for (int i = bhsIndo.length() - 1; i >= 0; i--) {
//                    reverse = reverse + bhsIndo.charAt(i);
//                }
//                textBhsJawa.setText(reverse);
//            } else {
//                bhsJawa = textBhsJawa.getText().toString();
//                for (int i = bhsJawa.length() - 1; i >= 0; i--) {
//                    reverse = reverse + bhsJawa.charAt(i);
//                }
//                textBhsIndo.setText(reverse);
//            }
//            reverse = "";
//        }
//    };



    View.OnClickListener clear = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            textBhsIndo.setText("");
            textBhsIndo.onEditorAction(EditorInfo.IME_ACTION_DONE);
            textBhsJawa.setText("");
            textBhsJawa.onEditorAction(EditorInfo.IME_ACTION_DONE);
            Snackbar.make(linearLayout,"Yang manis gula merah, yang pahit kopi, yang putih susu, kopi + gula merah + susu = enak", Snackbar.LENGTH_LONG)
                    .show();
        }
    };
}
