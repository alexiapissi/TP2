package com.example.a41824471.tp2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class ActividadPrincipal extends AppCompatActivity {

    EditText descripcion, precio;
    ImageButton imagen;
    Spinner categoria;
    TextView tvuri;
    TextView cantobj;
    TextView objactual;
    ArrayList<Producto> listaproductos;
    static public int REQUEST_IMAGE_GET = 1;
    static public int REQUEST_LIST=2;
    public int posactual=-1;
    ArrayAdapter<String> categAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);
        Inicializar_variables();

        ArrayList<String> categorias = new ArrayList<>();
        categorias.add("Electrónica");
        categorias.add("Vestimenta");
        categorias.add("Hogar");
        categorias.add("Automoviles");
        categorias.add("Deportes");


        categAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorias);


        categAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        categoria.setAdapter(categAdapter);
        cantobj.setText("Cantidad de Objetos:"+listaproductos.size());

    }

    public void Inicializar_variables() {
        descripcion = (EditText) findViewById(R.id.descripcion);
        imagen = (ImageButton) findViewById(R.id.imagen);
        tvuri = (TextView) findViewById(R.id.uri);
        categoria = (Spinner) findViewById(R.id.categoria);
        precio = (EditText) findViewById(R.id.precio);
        listaproductos = new ArrayList<>();
        cantobj=(TextView)findViewById(R.id.cantobj);
        objactual=(TextView)findViewById(R.id.objactual);

    }

    public void selectImage(View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_GET);
        }
    }

    public void btnListar(View v) {
        if (listaproductos.size()==0)  {
            Toast.makeText(this, "No hay nada para listar", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, ActividadListView.class);
            intent.putExtra("listaproductos", listaproductos);
            startActivityForResult(intent, REQUEST_LIST);
        }
    }
    public void btnActualizar(View v) {
        if (posactual == -1) {
            Toast.makeText(this, "No hay nada para actualizar", Toast.LENGTH_SHORT).show();
        } else {
            Producto p = listaproductos.get(posactual);
            p.setCategoria(categoria.getSelectedItem().toString());
            p.setDescripcion(descripcion.getText().toString());
            p.setPrecio(Integer.valueOf(precio.getText().toString()));
            p.setFoto(tvuri.getText().toString());
            objactual.setText("Objeto Actual:"+posactual);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_LIST && resultCode == RESULT_OK && data != null) {
            int posicion = data.getIntExtra("pos",0);
            posactual=posicion;
            objactual.setText("Objeto Actual:"+posactual);
            Producto p= listaproductos.get(posicion);
            descripcion.setText(p.getDescripcion());
            int pos = categAdapter.getPosition(p.getCategoria());
            categoria.setSelection(pos);
            String sprecio= String.valueOf(p.getPrecio());
            precio.setText(sprecio);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(p.getFoto()));
                imagen.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imagen.setImageBitmap(bitmap);
                tvuri.setText(uri.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void btnAgregar(View v) {
        if (descripcion.getText().toString().isEmpty() || precio.getText().toString().isEmpty() || imagen.getDrawable() == null) {
            Toast.makeText(this, "Campos incompletos", Toast.LENGTH_SHORT).show();
        } else {
            Producto prod = new Producto(categoria.getSelectedItem().toString(), descripcion.getText().toString(), Integer.valueOf(precio.getText().toString()), tvuri.getText().toString());
            listaproductos.add(prod);
            Toast.makeText(getApplicationContext(), "Agregado", Toast.LENGTH_SHORT).show();
            descripcion.setText("");
            precio.setText("");
            imagen.setImageResource(0);
            cantobj.setText("Cantidad de Objetos:"+listaproductos.size());
            int oa=listaproductos.size()-1;
            objactual.setText("Objeto Actual:"+oa);
        }
    }





}
