package com.belafon.pvpsurvival.TheGame.Stats.Players;


import com.belafon.pvpsurvival.User.User;

public class MainPlayer extends Player{
    public MainPlayer(){
        super(4, User.Name);
    }
    private static String typeOfSurrounding;
    public static String getTypeOfSurrounding() {
        return typeOfSurrounding;
    }
    public static void setTypeOfSurrounding(String typeOfSurroundin) {
        typeOfSurrounding = typeOfSurroundin;
    }
}
