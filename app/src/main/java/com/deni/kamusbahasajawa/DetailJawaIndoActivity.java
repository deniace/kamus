package com.deni.kamusbahasajawa;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.deni.kamusbahasajawa.model.IndoJawa;

public class DetailJawaIndoActivity extends AppCompatActivity {

    private TextInputEditText textTambahIndo,textTambahJawa;
    private long idBhs;
    private boolean isNew = false;
    private IndoJawa indoJawa = null;
    private Button buttonDelete;

    private RecyclerView recyclerView;
    private int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jawa_indo);

        textTambahIndo = findViewById(R.id.text_tambah_indo);
        textTambahJawa = findViewById(R.id.text_tambah_jawa);
        buttonDelete = findViewById(R.id.button_delete);

        Intent intent = getIntent();
        idBhs = intent.getLongExtra("ID_INDOJAWA", -1);
        if(idBhs> -1){
            isNew = false;
            populateControls();
            buttonDelete.setVisibility(View.VISIBLE);

        }else{
            isNew = true;
            indoJawa = new IndoJawa();
            buttonDelete.setVisibility(View.GONE);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home){
            Intent parent = new Intent();
            setResult(1, parent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void populateControls() {
        indoJawa = new Select().from(IndoJawa.class).where("id = ?", idBhs).executeSingle();
        if (indoJawa != null){
            textTambahIndo.setText(indoJawa.getIndo());
            textTambahJawa.setText(indoJawa.getJawa());
        }
    }

    public void save(View view){
        // validatiion
        String bhsIndo = "", bhsJawa = "";
        try {
            bhsIndo = textTambahIndo.getText().toString();
            bhsJawa = textTambahJawa.getText().toString();
        }catch (Exception e){
            e.printStackTrace();
        }

        if(bhsIndo == null || bhsIndo.equals("") || bhsIndo.length() == 0){
            Toast.makeText(this, "Bahasa Indonesia Harus diisi", Toast.LENGTH_LONG).show();
        }else if(bhsJawa == null || bhsJawa.equals("") || bhsJawa.length() == 0){
            Toast.makeText(this, "Bahasa Jawa Harus diisi", Toast.LENGTH_LONG).show();
        }else{
            indoJawa.setIndo(bhsIndo);
            indoJawa.setJawa(bhsJawa);

            indoJawa = new IndoJawa("asu", "asu");
            indoJawa.save();


            long id = indoJawa.save();
            if(isNew){
                //update
                indoJawa.setIndo(bhsIndo);
                indoJawa.setJawa(bhsJawa);
                indoJawa.save();

            }

            Intent intent = new Intent();
            intent.putExtra("ID_INDOJAWA", id);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

    public void delete(View view){
        String bhsIndo;
        if (indoJawa != null){
            bhsIndo = textTambahIndo.getText().toString();
            new Delete().from(IndoJawa.class).where("indo = ?", bhsIndo).execute();
            Intent intent = new Intent();
            intent.putExtra("ID_INDOJAWA", indoJawa.getId());
            intent.putExtra("INDO", indoJawa.getIndo());
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

}
