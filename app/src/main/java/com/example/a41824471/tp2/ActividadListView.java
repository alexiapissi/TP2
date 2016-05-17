package com.example.a41824471.tp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ActividadListView extends AppCompatActivity {
    ListView listVW;
    ArrayList<Producto> listaproductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        listaproductos = (ArrayList<Producto>) extras.getSerializable("listaproductos");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        listVW = (ListView) findViewById(R.id.listv);
        ProductosAdapter adapter = new ProductosAdapter(this,listaproductos);
        listVW.setAdapter(adapter);
        listVW.setOnItemClickListener(new AdapterView.OnItemClickListener(){
        public void onItemClick (AdapterView<?> adapter, View V, int position, long l){
            Intent intent = new Intent();
            intent.putExtra("pos",position);
            setResult(RESULT_OK,intent);
            finish();
        }

    });
    }

}
