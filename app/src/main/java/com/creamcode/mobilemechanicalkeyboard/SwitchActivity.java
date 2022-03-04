package com.creamcode.mobilemechanicalkeyboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.Arrays;
import java.util.List;

public class SwitchActivity extends AppCompatActivity {

    RecyclerView rcv;
    List<String> switches;
    List<Integer> switchesImg;
    List<Integer> switchesSounds;
    SwitchAdapter switchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

        rcv = findViewById(R.id.rcv_switches);
        switchesImg = Arrays.asList(R.drawable.img_cherry_mx_red,
                                    R.drawable.img_cherry_mx_black,
                                    R.drawable.img_cherry_mx_blue,
                                    R.drawable.img_cherry_mx_browns,
                                    R.drawable.img_holy_panda,
                                    R.drawable.img_gateron_black_inks,
                                    R.drawable.img_gateron_red_inks,
                                    R.drawable.img_novel_keys_cream);

        switchesSounds = Arrays.asList(R.raw.cherry_red_typeone,
                                        R.raw.cherry_black_typeone,
                                        R.raw.cherry_mx_blue_typeone,
                                        R.raw.cherry_mx_browns_typeone,
                                        R.raw.holy_panda_typeone,
                                        R.raw.gateron_black_inks_typeone,
                                        R.raw.gateron_red_inks_typeone,
                                        R.raw.novelkeys_cream_typeone);

        switches = Arrays.asList(getResources().getStringArray(R.array.array_switches));

        switchAdapter = new SwitchAdapter(this, switches,switchesImg,switchesSounds);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(gridLayoutManager);
        rcv.setAdapter(switchAdapter);
    }
}