package com.belafon.pvpsurvival.TheGame.Communication;

// SET SERVERS VALUES

import com.belafon.pvpsurvival.TheGame.GameActivity;
import com.belafon.pvpsurvival.TheGame.GameSound;
import com.belafon.pvpsurvival.TheGame.Server.Client;

public class Setters {
    public static void move(int idPlace){
        Client.write("behavior move " + idPlace);
        GameSound.startCurrentWalkSound();
    }
    public static void lie_down() {
        Client.write("behavior " + GameActivity.GameId + GameActivity.PlayersId + " change_body_position lie_down");
    }

    public static void sit_down() {
        Client.write("behavior " + GameActivity.GameId + GameActivity.PlayersId + " change_body_position sit_down");
    }

    public static void stand_up() {
        Client.write("behavior " + GameActivity.GameId + GameActivity.PlayersId + " change_body_position stand_up");
    }

    public static void crouch() {
        Client.write("behavior " + GameActivity.GameId + GameActivity.PlayersId + " change_body_position crouch");
    }

    public static void climb_to_tree() {
        Client.write("behavior " + GameActivity.GameId + GameActivity.PlayersId + " change_body_position climb_to_tree");
    }

    public static void take_notice() {
        Client.write("behavior " + GameActivity.GameId + GameActivity.PlayersId + " explore_surrounding take_notice");
    }

    public static void get_look_around() {
        Client.write("getter get_look_around");
    }
}
