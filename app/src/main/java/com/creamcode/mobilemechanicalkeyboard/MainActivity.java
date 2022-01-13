package com.creamcode.mobilemechanicalkeyboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    private AppCompatButton btnEscKey, btnKeyFont, btnTry;
    private ImageView ivMouse, ivUsb, ivEarpods, ivSwitch, ivCoffee;
    private MediaPlayer mouseSound, usbSound, earpodsSound, switchSound, coffeeSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEscKey =  findViewById(R.id.btn_esp_key);
        btnKeyFont = findViewById(R.id.btn_key_font);
        btnTry = findViewById(R.id.btn_practice);

        ivUsb = findViewById(R.id.iv_usb);
        ivMouse = findViewById(R.id.iv_mouse);
        ivEarpods = findViewById(R.id.iv_earpods);
        ivSwitch = findViewById(R.id.iv_switch);
        ivCoffee = findViewById(R.id.iv_coffee);

        mouseSound = MediaPlayer.create(this,R.raw.sound_mouse);
        usbSound = MediaPlayer.create(this,R.raw.sound_usb);
        earpodsSound = MediaPlayer.create(this,R.raw.sound_earpods);
        switchSound = MediaPlayer.create(this,R.raw.sound_switch_banana);
        coffeeSound = MediaPlayer.create(this,R.raw.sound_coffee);

        btnEscKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EscKeyActivity.class);
                startActivity(intent);
            }
        });

        ivUsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemSound(v);
            }
        });
        ivMouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemSound(v);
            }
        });
        ivEarpods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemSound(v);
            }
        });
        ivSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemSound(v);
            }
        });
        ivCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemSound(v);
            }
        });

    }
    private void itemSound(View v){
        switch (v.getId()){
            case R.id.iv_usb: usbSound.start();
                break;
            case R.id.iv_mouse: mouseSound.start();
                break;
            case R.id.iv_earpods: earpodsSound.start();
                break;
            case R.id.iv_switch: switchSound.start();
                break;
            case R.id.iv_coffee: coffeeSound.start();
                break;
        }

    }
    public void habilitarTeclado(View view){
        Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
        startActivity(intent);
    }
    public void seleccionarTeclado(View view){
        InputMethodManager imeManager = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
        imeManager.showInputMethodPicker();
    }
    public void ayuda(View view){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        final View customLayout = getLayoutInflater().inflate(R.layout.dialogo, null);
        alertDialog.setView(customLayout);
        final AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();
        Button submit = customLayout.findViewById(R.id.btn_ok);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });

    }
}