package com.belafon.pvpsurvival.TheGame.maps;


import com.belafon.pvpsurvival.R;

import java.util.ArrayList;

public class Places {
    public static ArrayList<Place> places = new ArrayList<>();
    public Places(){
        int[] a = {R.raw.forest_1, R.raw.forest_2, R.raw.forest_3, R.raw.forest_4};
        int[] b = {R.raw.night_forest_leafy_a};
        int[] c = {R.raw.night_forest_leafy_a};
        setPlaces("forest_leafy", a, b, c);
        int[] d = {R.raw.wind_a, R.raw.wind_b};
        int[] e = {R.raw.wind_b, R.raw.wind_b};
        int[] f = {R.raw.wind_a, R.raw.wind_b};
        setPlaces("cliff", d, e, f);
    }
    private static void setPlaces(String name, int[] sunSongs, int[] nightSongs, int[] midNightSongs){
        Place place = new Place();
        place.setName(name);
        for (int i = 0; i < sunSongs.length; i++) {
            place.addSunSong(sunSongs[i]);
        }
        for (int i = 0; i < nightSongs.length; i++) {
            place.addNightSong(nightSongs[i]);
        }
        for (int i = 0; i < midNightSongs.length; i++) {
            place.addMidNightSong(nightSongs[i]);
        }
        places.add(place);
    }

}
