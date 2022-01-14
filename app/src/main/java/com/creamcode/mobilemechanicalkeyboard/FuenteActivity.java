package com.creamcode.mobilemechanicalkeyboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.Arrays;
import java.util.List;

public class FuenteActivity extends AppCompatActivity {
    RecyclerView rcv;
    RecyclerView.LayoutManager mLayoutManager;
    List<String> fuentes;
    FontAdapter fontAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuente);

        rcv = findViewById(R.id.rcv_fuente);

        fuentes = Arrays.asList(getResources().getStringArray(R.array.array_fonts));

        fontAdapter = new FontAdapter(this,fuentes);
        mLayoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(mLayoutManager);
        rcv.setAdapter(fontAdapter);


    }
}