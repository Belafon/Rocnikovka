package com.belafon.pvpsurvival.TheGame.maps;

// obsahuje info o vzdálenějším okolí
public class Surroundings {
    // pole obsahuje na každém políčku popis daného place,
    // při prohledávání okolí hráčem se mu vypíše popis místa + přibližná vzdálenost
    public static String[][] places = new String[11][11];
    public static void setPlaces(int i, int j, String description){
        places[i][j] = description;
    }
    // text se vytváří již na servru
}
