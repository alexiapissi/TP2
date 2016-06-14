package com.example.a41824471.tp2;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 41824471 on 10/5/2016.
 */
public class ProductosAdapter extends BaseAdapter {
    ArrayList<Producto> listaproductos;
    Context context;

    public ProductosAdapter(Context context, ArrayList<Producto> listaproductos) {
        this.context = context;
        this.listaproductos=listaproductos;
    }
    @Override
    public int getCount() {
        return listaproductos.size();
    }

    @Override
    public Object getItem(int i) {
        return listaproductos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.objetolista, viewGroup, false);
        }

        TextView desctv = (TextView)view.findViewById(R.id.desctv);
        TextView cattv = (TextView)view.findViewById(R.id.cattv);
        TextView preciotv = (TextView)view.findViewById(R.id.preciotv);
        ImageView fottv = (ImageView) view.findViewById(R.id.fotv);

        Producto prod = listaproductos.get(position);
        desctv.setText(prod.getDescripcion());
        cattv.setText(prod.getCategoria());
        String precio= String.valueOf(prod.getPrecio());
        preciotv.setText("$"+precio);
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.parse(prod.getFoto()));
            fottv.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }



}
