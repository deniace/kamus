package com.deni.kamusbahasajawa;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.deni.kamusbahasajawa.adapter.IndoJawaAdapter;
import com.deni.kamusbahasajawa.model.IndoJawa;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private IndoJawaAdapter adapter;
    private List<IndoJawa> list = new ArrayList<>();
    private FloatingActionButton floatButton;
    private int position = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recycler_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        floatButton = findViewById(R.id.buttonAdd);
        floatButton.setOnClickListener(addListener);
        populateData();
    }

    private void populateData() {
        list = new Select().from(IndoJawa.class).execute();
        adapter = new IndoJawaAdapter(list, new IndoJawaAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(IndoJawa item) {
                position = list.indexOf(item);
                Intent intent = new Intent(ListActivity.this, DetailJawaIndoActivity.class);
                intent.putExtra("ID_INDOJAWA", item.getId());
                startActivityForResult(intent, 1);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private FloatingActionButton.OnClickListener addListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            position = -1;
            Intent intent = new Intent(ListActivity.this, DetailJawaIndoActivity.class);
            intent.putExtra("ID_INDOJAWA", -1);
            startActivityForResult(intent, 1);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            Long id = data.getLongExtra("ID_INDOJAWA", 0);
            String bIndo = data.getStringExtra("INDO");
            IndoJawa indoJawa = new Select().from(IndoJawa.class).where("id = ?", id).executeSingle();
            if(indoJawa == null){
                if(bIndo != null || !bIndo.equals("")){
                    Toast.makeText(ListActivity.this, "bahasa indonesia "+bIndo + " Terhapus", Toast.LENGTH_LONG).show();
                    populateData();
                }else {
                    Toast.makeText(ListActivity.this, "Error !! id = "+id, Toast.LENGTH_LONG).show();
                }
            }else {
                if (position == -1){
                    list.add(indoJawa);
                }else {
                    list.set(position, indoJawa);
                }
                adapter.notifyDataSetChanged();
            }
        }
    }
}
