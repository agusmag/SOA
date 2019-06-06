package com.example.changosmart.productos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.changosmart.R;
import com.example.changosmart.listasCompras.LineaCompra;

import java.util.ArrayList;

public class MiAdaptadorDetalleLista extends BaseAdapter {
    private Context contexto;
    private ArrayList<LineaCompra> listaDetalle;
    private static LayoutInflater inflater = null;

    public MiAdaptadorDetalleLista(Context contexto, ArrayList<LineaCompra> listaDetalle) {
        this.contexto = contexto;
        this.listaDetalle = listaDetalle;
    }

    public Context getContexto() {
        return contexto;
    }

    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }

    public ArrayList<LineaCompra> getListaDetalle() {
        return listaDetalle;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View myView = inflater.inflate(R.layout.detalle_linea_compra,null);

        TextView nombreProducto     = (TextView) myView.findViewById(R.id.textViewDetalleProducto);
        TextView categoriaProducto  = (TextView) myView.findViewById(R.id.textViewDetalleCategoria);
        TextView precioProducto     = (TextView) myView.findViewById(R.id.textViewDetallePrecio);
        TextView cantidadProducto   = (TextView) myView.findViewById(R.id.textViewDetalleProducto);


        nombreProducto.setText(listaDetalle.get(position).getProducto().getNombre());
        categoriaProducto.setText(listaDetalle.get(position).getProducto().getCategoria());
        precioProducto.setText(listaDetalle.get(position).getProducto().getPrecio());
        cantidadProducto.setText(listaDetalle.get(position).getcantidadAComprar());

        return myView;
    }

    public void setListaDetalle(ArrayList<LineaCompra> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }

    @Override
    public int getCount() {
        return listaDetalle.size();
    }

    @Override
    public Object getItem(int position) {
        return listaDetalle.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
