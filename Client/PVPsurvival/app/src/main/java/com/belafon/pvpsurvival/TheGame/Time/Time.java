package com.belafon.pvpsurvival.TheGame.Time;


import android.util.Log;

import com.belafon.pvpsurvival.TheGame.Communication.Getters;
import com.belafon.pvpsurvival.TheGame.GameSound;
import com.belafon.pvpsurvival.TheGame.SetActivity.SetActivity;
import com.belafon.pvpsurvival.TheGame.Weather.WeatherStats;

public class Time{
    private static final String TAG = "Time";
    private static partsOfDay currentPartOfDay = partsOfDay.after_midnight;
    public static partsOfDay getCurrentPartOfDay() {
        return currentPartOfDay;
    }
    // 9 hodin noc, 12 hodin den, 3 hodiny mezi
    // vlákno musí změnit filtr background drawable, buď to zesvětlit, či ztmavit,
    //  zároveň je potřeba vyslat do chatu upozornění (nemusí), že se začíná ztmívat, nebo obráceně.
    // dále ovlivňuje viditelnost, zvuky aktivity (kvákání žab, či sova, cvrčci)

    public enum partsOfDay{
        sunrise_1, sunrise_2, morning, afternoon, sunset_1, sunset_2, night, after_midnight
    }

    public static void getPartOfDay(String value){
        switch (value){
            case "night":
                setColorFilter(partsOfDay.night);
                currentPartOfDay = partsOfDay.night;
                break;
            case "sunset_2":
                setColorFilter(partsOfDay.sunset_2);
                currentPartOfDay = partsOfDay.sunset_2;
                break;
            case "sunset_1":
                setColorFilter(partsOfDay.sunset_1);
                currentPartOfDay = partsOfDay.sunset_1;
                break;
            case "afternoon":
                setColorFilter(partsOfDay.afternoon);
                currentPartOfDay = partsOfDay.afternoon;
                break;
            case "morning":
                setColorFilter(partsOfDay.morning);
                currentPartOfDay = partsOfDay.morning;
                break;
            case "sunrise_1":
                setColorFilter(partsOfDay.sunrise_1);
                currentPartOfDay = partsOfDay.sunrise_1;
                break;
            case "sunrise_2":
                setColorFilter(partsOfDay.sunrise_2);
                currentPartOfDay = partsOfDay.sunrise_2;
                break;
            case "after_midnight":
                setColorFilter(partsOfDay.after_midnight);
                currentPartOfDay = partsOfDay.after_midnight;
                break;
        }
        if(WeatherStats.getTypeWeather() == 0 ){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(getCurrentPartOfDay() == partsOfDay.sunset_1 || partsOfDay.sunrise_1 == getCurrentPartOfDay() || partsOfDay.night  == getCurrentPartOfDay())
                        GameSound.changeCurrentSong(500);
                }
            }).start();
        }
    }


    private static void setColorFilter(partsOfDay partOfDay){
        switch (partOfDay){
            case night:
                setColorByAllTime(9500, 190, 5, 0);
                break;
            case sunset_2:
                setColorByAllTime(4500, 60, 35 , 0);
                break;
            case sunset_1:
                if(SetActivity.cloudsAlfaFilter < 70 && SetActivity.cloudsAlfaFilterRain < 70)setColorByAllTime(4500, 40, 60, 255);
                else setColorByAllTime(4500, 40, 60, 40);
                break;
            case afternoon:
                setColorByAllTime(17000, 10, 10, 0);
                break;
            case morning:
                setColorByAllTime(17000, 1, 1, 0);
                break;
            case sunrise_2:
                setColorByAllTime(4500, 50, 20, 0);
                break;
            case sunrise_1:
                if(SetActivity.cloudsAlfaFilter < 70 && SetActivity.cloudsAlfaFilterRain < 70)setColorByAllTime(4500, 40, 100, 255);
                else setColorByAllTime(4500, 50, 100, 50);
                break;
            case after_midnight:
                setColorByAllTime(9500, 150, 15, 0);
                break;
        }
    }
    private static void setColorByAllTime(long duration, int colorAlfa, int colorBlue, int colorYellow){ // čím menší, tím rychlejší
        int pocitadloAlfa = SetActivity.alfaCanal;
        int pocitadloBlue = SetActivity.blue;
        int pocitadlo = Math.abs(pocitadloAlfa - colorAlfa);
        if(pocitadlo < Math.abs(pocitadloBlue - colorBlue))pocitadlo = Math.abs(pocitadloBlue - colorBlue);
        if(pocitadlo < Math.abs(SetActivity.yellow - colorYellow))pocitadlo = Math.abs(SetActivity.yellow - colorYellow);
        long speed = 0;
        try{
            speed = duration / (long) pocitadlo;
        }catch (Exception e){
            Log.d(TAG, "setColorByAllTime: Devided by zero!");
            return;
        }
        while(!Getters.changePartOfDay){
           // Log.d(TAG, "setColorByAllTime: Getters.changePartOfDay");
            if(SetActivity.alfaCanal != colorAlfa + 1 && SetActivity.alfaCanal != colorAlfa - 1 && SetActivity.alfaCanal != colorAlfa){
                if(colorAlfa > SetActivity.alfaCanal) SetActivity.setFilterColor(SetActivity.alfaCanal + 1, (SetActivity.yellow) / 4, SetActivity.yellow, SetActivity.blue);
                else SetActivity.setFilterColor(SetActivity.alfaCanal - 1, SetActivity.yellow, (SetActivity.yellow) / 4, SetActivity.blue);
            }
            if(SetActivity.blue != colorBlue && SetActivity.blue != colorBlue + 1 && SetActivity.blue != colorBlue - 1){
                if(colorBlue > SetActivity.blue) SetActivity.setFilterColor(SetActivity.alfaCanal, SetActivity.yellow, (SetActivity.yellow) / 4, SetActivity.blue + 1);
                else SetActivity.setFilterColor(SetActivity.alfaCanal, SetActivity.yellow, (SetActivity.yellow) / 4, SetActivity.blue - 1);
            }
            if(SetActivity.yellow != colorYellow && SetActivity.yellow != colorYellow + 1 && SetActivity.yellow != colorYellow - 1){
                if(colorYellow > SetActivity.yellow) SetActivity.setFilterColor(SetActivity.alfaCanal, SetActivity.yellow + 1, (SetActivity.yellow - 1) / 4, SetActivity.blue);
                else SetActivity.setFilterColor(SetActivity.alfaCanal, SetActivity.yellow - 1, (SetActivity.yellow - 1) / 4, SetActivity.blue);
            }
            try {
                Thread.currentThread().sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void setColorForWeather(long speed, int addColorAlfa, int addColorBlue){ // čím menší, tím rychlejší
        int pocitadloAlfa = SetActivity.alfaCanal;
        int pocitadloBlue = SetActivity.blue;
        int initialAlfa = pocitadloAlfa;
        int initialBlue = pocitadloBlue;
        while(pocitadloAlfa != initialAlfa + addColorAlfa || pocitadloBlue != initialBlue + addColorBlue){
            if(addColorAlfa + initialAlfa > SetActivity.alfaCanal && SetActivity.alfaCanal < 200) SetActivity.setFilterColor(SetActivity.alfaCanal - 1, 0, 0, SetActivity.blue);
            else SetActivity.setFilterColor(SetActivity.alfaCanal + 1, 0, 0, SetActivity.blue);
            if(addColorBlue + initialBlue > SetActivity.blue)
               /* if()*/SetActivity.setFilterColor(SetActivity.alfaCanal, 0, 0, SetActivity.blue - 1);
                else SetActivity.setFilterColor(SetActivity.alfaCanal, 0, 0, SetActivity.blue + 1);
            try {
                Thread.currentThread().sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
