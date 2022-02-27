package com.creamcode.mobilemechanicalkeyboard;


import android.graphics.drawable.AnimationDrawable;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.MediaPlayer;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;


public class MobileMechanicalKeyboard extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private Keyboard keyboard;
    private CustomKeyboard customKeyboard;
    private boolean isCaps = false;
    private boolean isShift = false;
    private boolean isAlt = false;
    private boolean isAccent = false;
    private MediaPlayer mediaPlayer;
    private AnimationDrawable animationDrawable;

    @Override
    public View onCreateInputView() {
        customKeyboard = (CustomKeyboard) getLayoutInflater().inflate(R.layout.keyboard,null);
        keyboard = new Keyboard(this,R.xml.qwerty);
        customKeyboard.setKeyboard(keyboard);
        customKeyboard.setOnKeyboardActionListener(this);
        //set dynamic rgb background
        animationDrawable = (AnimationDrawable) customKeyboard.getBackground();
        animationDrawable.setEnterFadeDuration(10);
        animationDrawable.setExitFadeDuration(200);
        animationDrawable.start();
        return customKeyboard;
    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }


    @Override
    public void onKey(int i, int[] ints) {
        InputConnection ic = getCurrentInputConnection();
        playClick(i);
        switch (i)
        {
            case -28:
                InputMethodManager imeManager = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
                imeManager.showInputMethodPicker();
                break;
            case -10:
               ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_ESCAPE));

                break;
            //teclas repeditas
            case -11:
            case -12:
                isShift=!isShift;
                break;
            //teclas repetidas
            case -22:
            case -23:
                isAlt=!isAlt;
                break;
            case -14:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_TAB));
                break;
            case -20:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_HOME));
                break;
            case Keyboard.KEYCODE_DELETE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_DEL));
                // ic.deleteSurroundingText(1,0);
                break;
            case Keyboard.KEYCODE_SHIFT:
                isCaps = !isCaps;
                keyboard.setShifted(isCaps);
                break;
            case Keyboard.KEYCODE_DONE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_ENTER));
                break;
            case -21:
            case -29:
            case -25:
                break;

                //no es ninguna tecla especial
            default:
                if(isAccent==true){
                    switch (i) {
                        case 97:
                            ic.commitText("á", 1);
                            break;
                    }
                    isAccent=!isAccent;
                }
                //si se presionó la tecla shift previamente
                if(isShift==true){
                    switch (i){
                        case 49: ic.commitText("!",1);break;
                        case 50: ic.commitText("@",1);break;
                        case 51: ic.commitText("#",1);break;
                        case 52: ic.commitText("$",1);break;
                        case 53: ic.commitText("%",1);break;
                        case 54: ic.commitText("^",1);break;
                        case 55: ic.commitText("&",1);break;
                        case 56: ic.commitText("*",1);break;
                        case 57: ic.commitText("(",1);break;
                        case 48: ic.commitText(")",1);break;
                        case 45: ic.commitText("_",1);break;
                        case 61: ic.commitText("+",1);break;
                        case 91: ic.commitText("{",1);break;
                        case 93: ic.commitText("}",1);break;
                        case 59: ic.commitText(":",1);break;
                        case 39: ic.commitText(""+(char)34,1);break;
                        case 44: ic.commitText("<",1);break;
                        case 46: ic.commitText(">",1);break;
                        case 47: ic.commitText("?",1);break;
                        default:
                            char code = (char) i;
                            if (Character.isLetter(code) && isShift)
                            code = Character.toUpperCase(code);
                            ic.commitText(String.valueOf(code), 1);
                            break;
                    }
                    isShift=!isShift;
                }else {
                    //si se presiono la tecla alt previamente
                    if(isAlt){
                        if(i==110){
                            ic.commitText("ñ",1);
                        }
                        if(i==47){
                            ic.commitText("¿",1);
                        }
                        if(i==101){
                            ic.commitText("é",1);
                        }
                        if(i==117){
                            ic.commitText("ú",1);
                        }
                        if(i==105){
                            ic.commitText("í",1);
                        }
                        if(i==111){
                            ic.commitText("ó",1);
                        }
                        if(i==97){
                            ic.commitText("á",1);
                        }

                        isAlt=!isAlt;
                    }else {
                        //si no se ha presionado alguna tecla especial previamente
                        char code = (char) i;
                        if (Character.isLetter(code) && isCaps)
                            code = Character.toUpperCase(code);
                        ic.commitText(String.valueOf(code), 1);
                    }
                }
        }

    }

    private void playClick(int i) {
        switch(i){
            case -10: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_esc); break;
            case 49: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_one); break;
            case 50: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_two); break;
            case 51: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_three); break;
            case 52: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_four); break;
            case 53: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_five); break;
            case 54: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_six); break;
            case 55: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_seven); break;
            case 56: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_eight); break;
            case 57: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_nine); break;
            case 48: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_cero); break;
            case 45: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_under); break;
            case 61: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_plus); break;
            case -5: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_backspace); break;

            case -14: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_tab); break;
            case 113: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_q); break;
            case 119: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_w); break;
            case 101: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_e); break;
            case 114: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_r); break;
            case 116: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_t); break;
            case 121: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_y); break;
            case 117: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_u); break;
            case 105: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_i); break;
            case 111: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_o); break;
            case 112: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_p); break;
            case 91: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_braleft); break;
            case 93: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_braright); break;
            case 124: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_backslash); break;

            case -1: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_caps); break;
            case 97: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_a); break;
            case 115: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_s); break;
            case 100: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_d); break;
            case 102: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_f); break;
            case 103: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_g); break;
            case 104: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_h); break;
            case 106: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_j); break;
            case 107: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_k); break;
            case 108: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_l); break;
            case 59: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_colon); break;
            case 39: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_comma); break;
            case -4: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_enter); break;

            case -11: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_shiftleft); break;
            case 122: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_z); break;
            case 120: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_x); break;
            case 99: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_c); break;
            case 118: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_v); break;
            case 98: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_b); break;
            case 110: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_n); break;
            case 109: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_m); break;
            case 44: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_comma); break;
            case 46: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_dot); break;
            case 47: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_forwardslash); break;
            case -12: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_shiftright); break;

            case -21: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_ctrlleft); break;
            case -20: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_win); break;
            case -22: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_altleft); break;
            case 32: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_space); break;
            case -23: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_altright); break;
            case -29: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_options); break;
            case -28: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_fn); break;

            default: mediaPlayer = MediaPlayer.create(this,R.raw.cherry_red_p); break;


        }

        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer p1) {
                p1.release();
            }
        });
    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }


}