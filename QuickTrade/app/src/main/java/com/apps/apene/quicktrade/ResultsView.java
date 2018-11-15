package com.apps.apene.quicktrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.apps.apene.quicktrade.model.Product;
import com.apps.apene.quicktrade.model.ProductSearch;
import com.apps.apene.quicktrade.model.QuickTradeAdapter;

import java.util.List;

public class ResultsView extends AppCompatActivity {

    // Lista para recoger las entradas de los personajes parseadas de la base de datos
    protected List<Product> mListProducts = null;

    // Objeto para referenciar el RecyclerView
    protected RecyclerView mRecyclerView = null;

    // Objeto para referenciar el Adapter que rellenará el RecyclerView
    protected QuickTradeAdapter mAdapter = null;

    // Objeto para referenciar el LayoutManager que dipondrá las vistas del RecyclerView
    protected LinearLayoutManager mManager = null;

    protected String attribute = null;

    protected String value = null;



    public ResultsView () {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_view);

        Bundle extras = getIntent().getExtras();
        attribute = extras.getString("attribute");
        value = extras.getString("value");
        ProductSearch search = new ProductSearch();
        mListProducts.addAll(search.getProducts("category", "Vehicles"));

        // Creamos la referencia del Recycler pasándole el elemento gráfico con el método findVeiwById
        mRecyclerView = findViewById(R.id.rv_results_viewer);
        // Creamos la referncia del adapter pasándole como parámetro a su constructor el List en el que hemos cargado los resultados
        mAdapter = new QuickTradeAdapter(mListProducts);
        // Creamos la referencia del LayoutManager pasándole el contexto (this)
        mManager = new LinearLayoutManager(this);
        // Cambiamos la orientación del LayoutManager
        mManager.setOrientation(LinearLayoutManager.VERTICAL);
        // Creadas las referencias le asignamos al RecyclerView su Adapter u su LayoutManager
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mManager);
    }

    protected void getData(){
        Bundle extras = getIntent().getExtras();
        attribute = extras.getString("attribute");
        value = extras.getString("value");
    }

}
