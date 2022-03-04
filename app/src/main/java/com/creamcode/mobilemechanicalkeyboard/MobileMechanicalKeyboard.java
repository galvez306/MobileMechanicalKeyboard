package com.creamcode.mobilemechanicalkeyboard;


import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

import java.util.Arrays;
import java.util.List;


public class MobileMechanicalKeyboard extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private Keyboard keyboard;
    private CustomKeyboard customKeyboard;
    private boolean isCaps = false;
    private boolean isShift = false;
    private boolean isAlt = false;
    private boolean isAccent = false;
    private MediaPlayer mediaPlayer;
    private AnimationDrawable animationDrawable;
    List<String> switches;
    private SharedPreferences sharedPreferences;
    private String switchType;

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
        switches = Arrays.asList(getResources().getStringArray(R.array.array_switches_sound));
        String soundName="";
        int resID;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        switchType = sharedPreferences.getString("SWITCH_TYPE","CHERRY MX RED");
        //GET THE SOUND EXTENSION NAME FROM THE SWITCH IN PREFERENCES
        switch (switchType){
            case "CHERRY MX RED":
                soundName = switches.get(0);
                break;
            case "CHERRY MX BLACK":
                soundName = switches.get(1);
                break;
            case "CHERRY MX BLUE":
                soundName = switches.get(2);
                break;
            case "CHERRY MX BROWN":
                soundName = switches.get(3);
                break;
            case "HOLY PANDA":
                soundName = switches.get(4);
                break;
            case "GATERON BLACK INK":
                soundName = switches.get(5);
                break;
            case "GATERON RED INK":
                soundName = switches.get(6);
                break;
            case "NOVELKEYS CREAM":
                soundName = switches.get(7);
                break;
        }
        //una vez seteado el sonido dependiendo de la preferencia solo queda reproducirlo
        switch (i) {
            case -10:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 48:
            case 45:
            case 61:
                resID = this.getResources().getIdentifier(soundName+"typeone", "raw", this.getPackageName());
                mediaPlayer = MediaPlayer.create(this,resID);
                break;
            case -14:
            case 113:
            case 119:
            case 101:
            case 114:
            case 116:
            case 121:
            case 117:
            case 105:
            case 111:
            case 112:
            case 91:
            case 93:
            case 124:
                resID = getResources().getIdentifier(soundName+"typetwo", "raw", this.getPackageName());
                mediaPlayer = MediaPlayer.create(this,resID);
                break;
            case -1:
            case 97:
            case 115:
            case 100:
            case 102:
            case 103:
            case 104:
            case 106:
            case 107:
            case 108:
            case 59:
            case 39:
                resID = getResources().getIdentifier(soundName+"typethree", "raw", this.getPackageName());
                mediaPlayer = MediaPlayer.create(this,resID);
                break;
            case 122:
            case 120:
            case 99:
            case 118:
            case 98:
            case 110:
            case 109:
            case 44:
            case 46:
            case 47:
            case -21:
            case -20:
            case -22:
            case -23:
            case -29:
            case -28:
                resID = getResources().getIdentifier(soundName+"typefour", "raw", this.getPackageName());
                mediaPlayer = MediaPlayer.create(this,resID);
                break;
            case 32:
                resID = getResources().getIdentifier(soundName+"space", "raw", this.getPackageName());
                mediaPlayer = MediaPlayer.create(this,resID);
                break;
            case -4:
                resID = getResources().getIdentifier(soundName+"enter", "raw", this.getPackageName());
                mediaPlayer = MediaPlayer.create(this,resID);
                break;
            case -11:
            case -12:
                resID = getResources().getIdentifier(soundName+"shift", "raw", this.getPackageName());
                mediaPlayer = MediaPlayer.create(this,resID);
                break;
            case -5:
                resID = getResources().getIdentifier(soundName+"backspace", "raw", this.getPackageName());
                mediaPlayer = MediaPlayer.create(this,resID);
                break;

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