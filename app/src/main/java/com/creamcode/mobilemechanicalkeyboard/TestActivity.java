package com.creamcode.mobilemechanicalkeyboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {

    TextView tvTest;
    EditText edtTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        tvTest = findViewById(R.id.tv_test);
        edtTest = findViewById(R.id.edt_test);

        edtTest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String abc = edtTest.getText().toString();
                tvTest.setText(abc);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}