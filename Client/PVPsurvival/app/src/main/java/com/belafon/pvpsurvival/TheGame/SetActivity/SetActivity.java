package com.belafon.pvpsurvival.TheGame.SetActivity;


import android.graphics.Color;
import android.support.annotation.UiThread;
import android.text.Layout;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.belafon.pvpsurvival.R;
import com.belafon.pvpsurvival.TheGame.Fragments.BehaviorFragment;
import com.belafon.pvpsurvival.TheGame.Fragments.ChatFragment;
import com.belafon.pvpsurvival.TheGame.Fragments.GameMenuFragment;
import com.belafon.pvpsurvival.TheGame.Fragments.InventaryFragment;
import com.belafon.pvpsurvival.TheGame.Fragments.TripFragment;
import com.belafon.pvpsurvival.TheGame.GameActivity;
import com.belafon.pvpsurvival.TheGame.GameSound;
import com.belafon.pvpsurvival.TheGame.Weather.Weather;
import com.belafon.pvpsurvival.TheGame.Weather.WeatherStats;

public class SetActivity {
    private static final String TAG = "SetActivity";
    public static int alfaCanal = 0;
    public static int blue = 0;
    public static int yellow = 0;
    public static int cloudsAlfaFilter = 0;
    public static int cloudsAlfaFilterRain = 0; // využívá se, když začne pršet

    //
    public static void setFilterColor(final int a, final int r, final int g, final int b){
        blue = b;
        alfaCanal = a;
        yellow = r;
        setColorFilter(a, r, g, b);
    }
    public static void setColorFilter(final int a, final int r, final int g, final int b){
        new Thread(new Runnable() {
            @Override
            public void run() {
                new DaysLifeAsyncTask().execute(a, r, g, b);
            }
        }).start();
    }

    // CLOUDS
    public static void setColorFilterCloud(final Integer a, final Integer r, final Integer g, final Integer b){
        new Thread(new Runnable() {
            @Override
            public void run() {
                new CloudsAsyncTask().execute(a, r, g, b);
            }
        }).start();
    }
    public static void setBackgroundAndSound(final int background, final int sound, final String message){
        GameActivity.gameActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RelativeLayout relativeLayout = GameActivity.gameActivity.findViewById(R.id.backrgroundOfLayoutGame);
                relativeLayout.setBackgroundResource(background);
                Log.d(TAG, "get_background_and_sound: message2 = " + message);
            }
        });
    }
    public static void makeToastText(final String letter){
        new Thread(new Runnable() {
            @Override
            public void run() {
                GameActivity.gameActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(GameActivity.gameActivity, letter, Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "run: The color of filter changed");
                    }
                });
            }
        }).start();
    }
    public static void setClouds(int cloudsAlfaFilterr) {
        //cloudsAlfaFilter = cloudsAlfaFilterr;
        if(WeatherStats.getTypeWeather() == 0)return;
        setColorFilterCloud(cloudsAlfaFilterr, 0, 0,0);
    }
    public static void setCloudsInRain(int cloudsAlfaFilterr, int blue) {
        cloudsAlfaFilterRain = cloudsAlfaFilterr;
        if(WeatherStats.getTypeWeather() != 0)return;
        setColorFilterCloud(cloudsAlfaFilterr, 0, 0,blue);
    }

    // SET POPAP MESSAGE
    public static void setPopapFeels(String message){
        stopPopap = true;
        setPopap(1, message);
    }
    public static void setPopapChat(String message){
        stopPopap = true;
        setPopap(3, message);
    }
    public static void setPopapNoticed(String message){
        stopPopap = true;
        setPopap(2, message);
    }
    public static boolean stopPopap = false;
    private static void setPopap(final int i, final String message) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                GameActivity.gameActivit.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView textView = null;
                        switch (i){
                            case 1:
                                textView = GameActivity.gameActivity.findViewById(R.id.popapTextFeels);
                                break;
                            case 2:
                                textView = GameActivity.gameActivity.findViewById(R.id.popapTextNoticed);
                                break;
                            case 3:
                                textView = GameActivity.gameActivity.findViewById(R.id.popapTextChat);
                                break;
                        }
                        textView.setHint(message);
                        textView.setVisibility(View.VISIBLE);
                    }
                });
                stopPopap = false;
                for (int j = 0; j < 100000; j++) {
                    try {
                        Thread.sleep(0,1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(stopPopap)return;
                }
               GameActivity.gameActivit.dissapearPopap(null);
            }
        }).start();
    }

    //SET SONG IN CLIPSADAPTER
    private static boolean weight = true;
    private static void setWeight(boolean b){
        weight = b;
    }
    private static boolean getWeight(){
        return weight;
    }
    public static void setSong(final int song, final float volume, final boolean toWeight) {
        setWeight(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                GameActivity.gameActivit.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        GameSound.setNewClipsSounds(song, GameActivity.gameActivity, volume);
                        setWeight(true);
                    }
                });
            }
        }).start();
        if(toWeight)while(true)if(getWeight())break;
    }

    // BEHAVIOR FRAGMENT

    // CHANGE FRAGMENT

    public static void changeFragment(String view){
        if(view == "behavior"){
            Log.d(TAG, "changeFragment: KOKOSka1");
            GameActivity.fragment = new BehaviorFragment();
        }else if(view == "Inventory"){
            GameActivity.fragment = new InventaryFragment();
        }else if(view == "Chat"){
            GameActivity.fragment = new ChatFragment();
        }else if(view == "Trip"){
            GameActivity.fragment = new TripFragment();
        }else if(view == "game_menu_fragment"){
            Log.d(TAG, "changeFragment: KOKOSka 2");
            GameActivity.fragment = new GameMenuFragment();
        }
        GameActivity.gameActivit.openFragment(GameActivity.fragment);
    }
    // BUTTONS
    public static void setBehaviorButton(final int idButton){
        GameActivity.gameActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Button button = GameActivity.gameActivity.findViewById(idButton);
                button.setVisibility(View.VISIBLE);
            }
        });
    }
}
