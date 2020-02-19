package com.belafon.pvpsurvival.TheGame.Weather;

import android.util.Log;

import com.belafon.pvpsurvival.TheGame.Communication.Getters;
import com.belafon.pvpsurvival.TheGame.GameActivity;
import com.belafon.pvpsurvival.TheGame.GameSound;
import com.belafon.pvpsurvival.TheGame.SetActivity.SetActivity;
import com.belafon.pvpsurvival.TheGame.likeliness.Kostka;


public class Weather {
    private static final String TAG = "Weather";
    public static void setNothing() {
        // nastaví se na nulu
        if(WeatherStats.getTypeWeather() == 0){
            return;
        }
        makeThunders = false;
        WeatherStats.setTypeWeather(0);
        int alfafilter = SetActivity.cloudsAlfaFilter;
        SetActivity.cloudsAlfaFilter = SetActivity.cloudsAlfaFilterRain;
        SetActivity.cloudsAlfaFilterRain = 0;
        makeTranstionBetweenCloudAndSunClouds(alfafilter);

        //set music
        GameSound.changeCurrentSong(20);

        Weather.setChangeOfClouds(true);
        Getters.get_clouds(WeatherStats.getClouds());
    }

    public static void setThunderstorm() {
        // zapne song a blesky
        if(WeatherStats.getTypeWeather() == 4)return;
        // pouze pokud dříve bylo pěkně, musí se cloudsAlfaFilterRain nastavit na clouds AlfaFilter, proto, aby nedošlo k divnému skoku
        // barev. Následně se cloudsAlfaFilterRain změní na hodnotu, kterou chceme.
        if(WeatherStats.getTypeWeather() == 0)SetActivity.cloudsAlfaFilterRain = SetActivity.cloudsAlfaFilter;
        WeatherStats.setTypeWeather(4);
        makeTranstionBetweenCloudAndSunRain(150);
        startThunders(6);
        GameSound.changeCurrentSong(20);
    }

    public static void setStorm() {
        // zapne storm a blesky
        if(WeatherStats.getTypeWeather() == 3)return;

        if(WeatherStats.getTypeWeather() == 0)SetActivity.cloudsAlfaFilterRain = SetActivity.cloudsAlfaFilter;
        WeatherStats.setTypeWeather(3);
        makeTranstionBetweenCloudAndSunRain(140);
        startThunders(8);
        GameSound.changeCurrentSong(20);
    }

    public static void setHeavyRain() {
        // zapne song a pozadí změní na stálo
        if(WeatherStats.getTypeWeather() == 2)return;

        if(WeatherStats.getTypeWeather() == 0)SetActivity.cloudsAlfaFilterRain = SetActivity.cloudsAlfaFilter;
        WeatherStats.setTypeWeather(2);
        makeTranstionBetweenCloudAndSunRain(130);
        GameSound.changeCurrentSong(20);
    }

    public static void setRain() {
        // zapne song a pozadí změní na stálo
        if(WeatherStats.getTypeWeather() == 1)return;
        if(WeatherStats.getTypeWeather() == 0)SetActivity.cloudsAlfaFilterRain = SetActivity.cloudsAlfaFilter;
        WeatherStats.setTypeWeather(1);
        makeTranstionBetweenCloudAndSunRain(120);
        GameSound.changeCurrentSong(20);
    }

    private static boolean makeThunders;
    private static void startThunders(final int howOffen) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int k = GameActivity.setNumberOfCurrentThreads(+1,"thunders",-1);
                Kostka kostka = new Kostka(howOffen);
                makeThunders = true;
                while(makeThunders){
                    if(kostka.hod() > 5){
                        Kostka kostka2 = new Kostka(10);
                        SetActivity.setColorFilterCloud(20 + kostka2.hod(), 90,90,255);
                        SetActivity.setColorFilter(0,0,0,0);
                        try {
                            Kostka kostka1 = new Kostka(3);
                            Thread.sleep(200 * kostka1.hod());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        SetActivity.setColorFilterCloud(SetActivity.cloudsAlfaFilterRain, 0,0, 0);
                        SetActivity.setColorFilter(SetActivity.alfaCanal,SetActivity.yellow,SetActivity.yellow,SetActivity.blue);
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                GameActivity.setNumberOfCurrentThreads(-1,"thunders",k);
            }
        }).start();
    }

    public synchronized static void  makeTranstionBetweenCloudAndSunRain(int alfa){
        boolean b = true;
        while(true){
            if(SetActivity.cloudsAlfaFilterRain < alfa){
                if(b){
                    SetActivity.cloudsAlfaFilterRain += 1;
                    SetActivity.setClouds(SetActivity.cloudsAlfaFilterRain);
                    b = false;
                }else b = true;
                if(SetActivity.cloudsAlfaFilterRain >= alfa)break;
            }
            else if(SetActivity.cloudsAlfaFilterRain != alfa){
                if(b){
                    SetActivity.cloudsAlfaFilterRain -= 1;
                    SetActivity.setClouds(SetActivity.cloudsAlfaFilterRain);
                    b = false;
                }else b = true;
                if(SetActivity.cloudsAlfaFilterRain <= alfa)break;
            }
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static void makeTranstionBetweenCloudAndSunClouds(int alfa){
        if(WeatherStats.getTypeWeather() == 0){
            boolean b = true;
            while(true){
                if(SetActivity.cloudsAlfaFilter < alfa){
                    if(b){
                        SetActivity.cloudsAlfaFilter += 1;
                        SetActivity.setClouds(SetActivity.cloudsAlfaFilter);
                        b = false;
                    }else b = true;

                    if(SetActivity.cloudsAlfaFilter >= alfa)break;
                }
                else{
                    if(SetActivity.cloudsAlfaFilter != alfa && b){
                        SetActivity.cloudsAlfaFilter -= 1;
                        SetActivity.setClouds(SetActivity.cloudsAlfaFilter);
                        b = false;
                    }else b = true;
                    if(SetActivity.cloudsAlfaFilter <= alfa)break;
                }
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }else{
            SetActivity.cloudsAlfaFilter = alfa;
        }
    }

    public synchronized static void makeTranstionBetweenCloudAndSun(int alfa){
        Log.d(TAG, "makeTranstionBetweenCloudAndSun: NEW CLOUD IS SHOWING " + SetActivity.cloudsAlfaFilter);
        Log.d(TAG, "makeTranstionBetweenCloudAndSun: NEW CLOUD IS SHOWING" + SetActivity.cloudsAlfaFilterRain);
        if(WeatherStats.getTypeWeather() == 0){
            boolean b = true;
            while(true){
                if(SetActivity.cloudsAlfaFilter < alfa){
                    if(b){
                        SetActivity.cloudsAlfaFilter += 1;
                        SetActivity.setClouds(SetActivity.cloudsAlfaFilter);
                        b = false;
                    }else b = true;

                    if(SetActivity.cloudsAlfaFilter >= alfa)break;
                }
                else{
                    if(SetActivity.cloudsAlfaFilter != alfa && b){
                        SetActivity.cloudsAlfaFilter -= 1;
                        SetActivity.setClouds(SetActivity.cloudsAlfaFilter);
                        b = false;
                    }else b = true;
                    if(SetActivity.cloudsAlfaFilter <= alfa)break;
                }
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }else{
            boolean b = true;
            while(true){
                if(SetActivity.cloudsAlfaFilterRain < alfa){
                    if(b){
                        SetActivity.cloudsAlfaFilterRain += 1;
                        SetActivity.setClouds(SetActivity.cloudsAlfaFilterRain);
                        b = false;
                    }else b = true;
                    if(SetActivity.cloudsAlfaFilterRain >= alfa)break;
                }
                else if(SetActivity.cloudsAlfaFilterRain != alfa){
                    if(b){
                        SetActivity.cloudsAlfaFilterRain -= 1;
                        SetActivity.setClouds(SetActivity.cloudsAlfaFilterRain);
                        b = false;
                    }else b = true;
                    if(SetActivity.cloudsAlfaFilterRain <= alfa)break;
                }
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d(TAG, "makeTranstionBetweenCloudAndSun: NEW CLOUD IS ENDING" + SetActivity.cloudsAlfaFilter);
        Log.d(TAG, "makeTranstionBetweenCloudAndSun: NEW CLOUD IS ENDING" + SetActivity.cloudsAlfaFilterRain);
    }

    private  static boolean changeOfClouds = false;
    public static boolean isChangeOfClouds() {
     //   Log.d(TAG, "isChangeOfClouds: GETTING : " + changeOfClouds);
        return changeOfClouds;
    }
    public static void setChangeOfClouds(boolean changeOfClouds) {
        Log.d(TAG, "setChangeOfClouds: SETTING : " + changeOfClouds);
        Weather.changeOfClouds = changeOfClouds;
    }

    public static boolean isMakeingClouds = false;
    public synchronized static void makeClouds(final int size, final int durationOfCloud, final int howOften){
        Log.d(TAG, "makeClouds: changeOfCloads = " + isChangeOfClouds());
        new Thread(new Runnable() {
            @Override
            public void run() {
                isMakeingClouds = true;
                int k = GameActivity.setNumberOfCurrentThreads(+1,"makeClouds",-1);
                while(!isChangeOfClouds()){
                    Kostka kostka = new Kostka(7 + howOften);
                    if(kostka.hod() > 6){
                        // show the cloud
                        if(WeatherStats.getTypeWeather() != 0)return;
                        makeTranstionBetweenCloudAndSunClouds(size);
                        Kostka kostka1 = new Kostka(durationOfCloud);
                        try {
                            Thread.sleep(kostka1.hod() * 1000 + 2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.d(TAG, "run: changeOfCloads = " + isChangeOfClouds());
                        if(WeatherStats.getTypeWeather() != 0)return;
                        makeTranstionBetweenCloudAndSunClouds(0);
                        if(isChangeOfClouds())break;
                    }
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(isChangeOfClouds())break;
                    Log.d(TAG, "run: cloud maker is continue");
                }
                Log.d(TAG, "run: GETTERS : CLAUDS MAKER STOPED");
                setChangeOfClouds(false);
                GameActivity.setNumberOfCurrentThreads(-1,"makeClouds",k);
                isMakeingClouds = false;
            }
        }).start();
    }
}
