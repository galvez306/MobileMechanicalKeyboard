package com.creamcode.mobilemechanicalkeyboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.Arrays;
import java.util.List;

public class EscKeyActivity extends AppCompatActivity {
    RecyclerView rcv;
    List <String> keys;
    EscKeyAdapter escKeyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esc_key);
        rcv = findViewById(R.id.rcv_main);

        keys = Arrays.asList(getResources().getStringArray(R.array.array_esc_key));

        escKeyAdapter = new EscKeyAdapter(this,keys);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3,RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(gridLayoutManager);
        rcv.setAdapter(escKeyAdapter);


    }
}