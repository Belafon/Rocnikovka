package com.belafon.pvpsurvival.TheGame.Stats.Players;


import java.util.ArrayList;

public class Players {
    public static MainPlayer mainPlayer;
    public static ArrayList<Player> players = new ArrayList<>();
    public Players(){
        mainPlayer = new MainPlayer();
    }

    public static Player getPlayer(int id) {
        if(mainPlayer.getId() == id)return mainPlayer;
        for(Player player : players)if(player.getId() == id)return player;
        return null;
    }
}
