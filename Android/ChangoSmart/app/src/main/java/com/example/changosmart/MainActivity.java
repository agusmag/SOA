package com.example.changosmart;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.changosmart.chango.Chango;
import com.example.changosmart.listasCompras.MisListas;
import com.example.changosmart.productos.AnadirProductoExpress;

import java.util.ArrayList;
import java.util.List;

import BD.MyAppDatabase;
import BD.ProductoTabla;

public class MainActivity extends AppCompatActivity {
    public static MyAppDatabase myAppDatabase;
    private boolean primeraEjecucion = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton btSettings = findViewById(R.id.bluetoothConfiguration);
        btSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Abro vista bt
            }
        });

        //Botón para mover el chango
        FloatingActionButton moveChart = findViewById(R.id.moveChart);
        moveChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveChango = new Intent( view.getContext(), Chango.class);
                startActivity(moveChango);
            }
        });

        // INICIALIZO LA BASE
        // HAY QUE CREAR UN HILO PARA QUE NO TRABAJE EN EL HILO PRINCIPAL
        myAppDatabase = Room.databaseBuilder(this.getApplicationContext(), MyAppDatabase.class, "db").allowMainThreadQueries().build();

        if(! primeraEjecucion){
            cargarProductos();
            primeraEjecucion = true;
        }

    }

    private void cargarProductos() {
        if (myAppDatabase.myDao().getProductos().isEmpty()) {
            List<ProductoTabla> listaProductoTabla = new ArrayList<ProductoTabla>();
            listaProductoTabla.add(new ProductoTabla("Oreo", "Comida", 20));
            listaProductoTabla.add(new ProductoTabla("Paty Swirft", "Congelado", 30));
            listaProductoTabla.add(new ProductoTabla("Axe", "Higiene", 15));
            listaProductoTabla.add(new ProductoTabla("Cuchillo", "Hogar", 30));
            listaProductoTabla.add(new ProductoTabla("Birome Bic", "Libreria", 10));

            myAppDatabase.myDao().cargarProductos(listaProductoTabla);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendMessage (View view){
        Intent openMisListas = new Intent(this, MisListas.class);
        startActivity(openMisListas);
    }

    public void openListExpress (View view){
        Intent openListExpress = new Intent(this, AnadirProductoExpress.class);
        startActivity(openListExpress);
    }
}
