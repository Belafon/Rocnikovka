package com.belafon.pvpsurvival.TheGame.maps;


import android.util.Log;

import com.belafon.pvpsurvival.TheGame.likeliness.Kostka;

import java.util.ArrayList;

public class Place {
    private String name;
    private ArrayList<Integer> sunSongs;
    private ArrayList<Integer> nightSongs;
    private ArrayList<Integer> midNightSongs;
    private static final String TAG = "Place";
    public Place(){
        sunSongs = new ArrayList<>();
        nightSongs = new ArrayList<>();
        midNightSongs = new ArrayList<>();
    }
    public int chooseSunSong(){
        Kostka kostka = new Kostka(sunSongs.size());
        return sunSongs.get(kostka.hod() - 1);
    }
    public int chooseNightSong(){
        Kostka kostka = new Kostka(nightSongs.size());
        return nightSongs.get(kostka.hod() - 1);
    }
    public int chooseMidNightSong() {
        Kostka kostka = new Kostka(nightSongs.size());
        Log.d(TAG, "chooseMidNightSong: SIZE OF ARRAYLIST = " + midNightSongs.size());
        return midNightSongs.get(kostka.hod() - 1);
    }
    public int getSunsong(int i){
        return sunSongs.get(i);
    }
    public int getNightsong(int i){
        return nightSongs.get(i);
    }
    public int getMidNightsong(int i){
        return midNightSongs.get(i);
    }
    public void addSunSong(int song){
        sunSongs.add(song);
    }
    public void addNightSong(int song){
        nightSongs.add(song);
    }
    public void addMidNightSong(int song){
        midNightSongs.add(song);
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
