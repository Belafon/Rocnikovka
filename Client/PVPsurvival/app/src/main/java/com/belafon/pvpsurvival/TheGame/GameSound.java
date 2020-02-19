package com.belafon.pvpsurvival.TheGame;


import android.content.Context;
import android.util.Log;

import com.belafon.pvpsurvival.R;
import com.belafon.pvpsurvival.Sound.ClipsAdapter;
import com.belafon.pvpsurvival.TheGame.SetActivity.SetActivity;
import com.belafon.pvpsurvival.TheGame.Stats.Players.MainPlayer;
import com.belafon.pvpsurvival.TheGame.Time.Time;
import com.belafon.pvpsurvival.TheGame.Weather.WeatherStats;
import com.belafon.pvpsurvival.TheGame.likeliness.Kostka;
import com.belafon.pvpsurvival.TheGame.maps.Place;
import com.belafon.pvpsurvival.TheGame.maps.Places;

import java.util.ArrayList;

public class GameSound {
    private static final String TAG = "GameSound";
    public static void setNewClipsSounds(int song, Context context, float volume) {
        ClipsAdapter clipsAdapter = new ClipsAdapter();
        clipsAdapter.play(context, song, true, volume);
        clipsSounds.add(clipsAdapter);
        Log.d(TAG, "setNewClipsSounds: volume = " + volume);
        Log.d(TAG, "setNewClipsSounds: NEW CLIPSADAPTER ADDED... = " + clipsSounds.size());
    }

    private static ArrayList<ClipsAdapter> clipsSounds = new ArrayList<>();
    public static ClipsAdapter getClipsSounds(int position) {
        return clipsSounds.get(position);
    }
    public static ArrayList<ClipsAdapter> getClipsSounds() {
        return clipsSounds;
    }

    // sound of walking on the surface, its not influenced by anything
    private static ClipsAdapter currentWalkSound = new ClipsAdapter();
    public static ClipsAdapter currentWalkSound() {
        return currentWalkSound;
    }
    public static void setCurrentWalkSound(int currentWalk) {
        currentWalkSound.setCurrentSong(currentWalk);
    }
    public static void startCurrentWalkSound(){
        if(currentWalkSound != null){
//            currentWalkSound.setSpeed(speed);
            currentWalkSound.play(GameActivity.gameActivity, currentWalkSound.getCurrentSong(), true, 1f);
        }
    }
    public static void stopCurrentWalkSound(){
        currentWalkSound.stop();
    }

    // metoda je volána pouze v případě změny denní doby, kdy je aplikace zapnutá a není pozastavena
    public synchronized static void changeCurrentSong(int speed) {
        setMainSound(MainPlayer.getTypeOfSurrounding(), 0, true);
        Log.d(TAG, "changeCurrentSong: clipsSounds.size() - 1 = " + (clipsSounds.size() - 1));
        makeChangeSound(clipsSounds.get(clipsSounds.size() - 1), clipsSounds.get(0), speed);
    }
    // provede změnu zvuků vytvořením přechodu
    private static void makeChangeSound(ClipsAdapter clipsAdapterNew, ClipsAdapter clipsAdapterLast, int speed){
        while(clipsAdapterLast.getVolumeSize() > 0 || clipsAdapterNew.getVolumeSize() < 1){
            float volume = clipsAdapterNew.getVolumeSize();
            if(volume < 1){
                clipsAdapterNew.setVolume(volume + 0.01f);
                clipsAdapterNew.setVolumeSize(volume + 0.01f);
            }
            volume = clipsAdapterLast.getVolumeSize();
            if(volume > 0){
                clipsAdapterLast.setVolume(volume - 0.01f);
                clipsAdapterLast.setVolumeSize(volume - 0.01f);
            }
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        clipsAdapterLast.stop();
        clipsSounds.remove(clipsAdapterLast);
        Log.d(TAG, "makeChangeSound: LAST SONG IN CLIPSADAPTER REMOVED AND STOPPED!!!");
        Log.d(TAG, "makeChangeSound: LAST SONG IN CLIPSADAPTER REMOVED AND STOPPED!!! " + clipsSounds.size());
    }

    public static void setMainSound(String name, float volume, boolean toWeight){
        Place place = null;
        int song = 0;
        // vybere správný places -> který obsahuje info o daném typu prostředí
        Log.d(TAG, "setMainSound: THE PLACE NAME NAME NAME ------- " + name);
        for (int i = 0; i < Places.places.size(); i++) {
            if(Places.places.get(i).getName() == name){
                place = Places.places.get(i);
            }
        }
        if(place == null)System.out.println("GETTERS: setMainSound: something went WRONG!!!");
        // v případě, že neprší, nastavíme to
        Log.d(TAG, "setMainSound: WEATHER WEATHER WEATHER = " + WeatherStats.getTypeWeather());
        if(WeatherStats.getTypeWeather() != 0){
            // songy se zde nemohou překrývat
            // nejprve je potřeba vypnout hudbu běžného dne CHYBA CHYBA CHABY CHYBA CHYBA
            int eee = WeatherStats.getTypeWeather();
            Log.d(TAG, "setMainSound: eee " + eee);
            switch(eee){
                case 1: // rain
                    Kostka kostka1 = new Kostka(2);
                    int hod = kostka1.hod();
                    if(hod == 1)song = R.raw.heavy_rain_a;
                    else if(hod == 2)song = R.raw.heavy_rain_b;
                    break;
                case 2: // heavyrain
                    Kostka kostka2 = new Kostka(2);
                    int hod2 = kostka2.hod();
                    if(hod2 == 1)song = R.raw.heavy_rain_a;
                    else if(hod2 == 2)song = R.raw.heavy_rain_b;
                    break;
                case 3: // thunder
                    Kostka kostka3 = new Kostka(5);
                    int hod3 = kostka3.hod();
                    if(hod3 == 1)song = R.raw.storm_a;
                    else if(hod3 == 2)song = R.raw.storm_b;
                    else if(hod3 == 3)song = R.raw.storm_c;
                    else if(hod3 == 4)song = R.raw.storm_d;
                    else if(hod3 == 5)song = R.raw.storm_e;
                    break;
                case 4: // stromthunder
                    Kostka kostka4 = new Kostka(1);
                    int hod4 = kostka4.hod();
                    if (hod4 == 1)song = R.raw.thunderstorm_a;
                    break;
            }
        }else{
            // mohou se překrývat songy, není potřeba nic zastavovat (musíme zastavit při změně denní doby)

            Time.partsOfDay time = Time.getCurrentPartOfDay();
            Log.d(TAG, "setMainSound: TIME TIME TIME = " + Time.getCurrentPartOfDay());
            Log.d(TAG, "setMainSound: TIME TIME TIME = " + Time.getCurrentPartOfDay());
            Log.d(TAG, "setMainSound: TIME TIME TIME = " + Time.getCurrentPartOfDay());
            if(time == Time.partsOfDay.morning || time == Time.partsOfDay.afternoon || time == Time.partsOfDay.sunrise_1 || Time.partsOfDay.sunrise_2 == time){
                song = place.chooseSunSong();
            }else if(time == Time.partsOfDay.sunset_1 || time == Time.partsOfDay.sunset_2){
                song = place.chooseNightSong();
            }else if(time == Time.partsOfDay.night || time == Time.partsOfDay.after_midnight){
                // přez noc pak nebude hrát nic, nebo akorád vítr
                song = place.chooseMidNightSong();
            }
        }
        Log.d(TAG, "setMainSound: SONG SONG SONG = " + song);
        if(song == 0)song = R.raw.night_forest_leafy_a;
        SetActivity.setSong(song, volume, toWeight);
    }
}
