package com.creamcode.mobilemechanicalkeyboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.util.Log;

import java.util.List;

public class CustomKeyboard extends KeyboardView {
    private Context  mContext;
    private Keyboard mKeyBoard;
    private SharedPreferences sharedPreferences;

    public CustomKeyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public CustomKeyboard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        Drawable dr;

        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(getResources().getDimensionPixelSize(R.dimen.key_size));
        paint.setColor(Color.BLACK);
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), sharedPreferences.getString("font","adam.ttf"));
        paint.setTypeface(font);
        List<Keyboard.Key> keys = getKeyboard().getKeys();
        for (Keyboard.Key key : keys) {
            switch (key.codes[0]){
                case -11:
                case -12:
                    dr = (Drawable) getContext().getResources().getDrawable(R.drawable.amarillo);
                    dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                    dr.draw(canvas);
                    break;
                case -10:
                    dr = (Drawable) getContext().getResources().getDrawable(R.drawable.rosa);
                    dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                    dr.draw(canvas);
                    canvas.drawText(sharedPreferences.getString("esckeycap","Esc"), key.x + (key.width/2), key.y+key.height/2, paint);
                    break;
                case -28:
                case -21:
                    dr = (Drawable) getContext().getResources().getDrawable(R.drawable.rosa);
                    dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                    dr.draw(canvas);
                    break;
                case -29:
                case -20:
                    dr = (Drawable) getContext().getResources().getDrawable(R.drawable.morado);
                    dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                    dr.draw(canvas);
                    break;
                case -23:
                case -22:
                    dr = (Drawable) getContext().getResources().getDrawable(R.drawable.azul);
                    dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                    dr.draw(canvas);
                    break;
                case -25:
                    dr = (Drawable) getContext().getResources().getDrawable(R.drawable.verde);
                    dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                    dr.draw(canvas);
                    break;
            }
            if(key.label.toString().equals("←")||key.label.toString().equals("▤")||key.label.toString().equals("⊞")||key.label.toString().equals(";:")){
                paint.setTextSize(getResources().getDimensionPixelSize(R.dimen.key_size_simbol));
            }else{
                paint.setTextSize(getResources().getDimensionPixelSize(R.dimen.key_size));
            }
            canvas.drawText(key.label.toString(), key.x + (key.width/2), key.y+key.height/2, paint);
        }
    }


}
