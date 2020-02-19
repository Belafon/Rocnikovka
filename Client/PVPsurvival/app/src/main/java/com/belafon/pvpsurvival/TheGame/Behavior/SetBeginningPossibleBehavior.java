package com.belafon.pvpsurvival.TheGame.Behavior;

import android.util.Log;

// WE HAVE TO FILL ARRAYLISTS IN CLASS BUTTONS, WHICH BEHAVIOUR IS POSSIBLE TO DO
public class SetBeginningPossibleBehavior {
    private static final String TAG = "SetBeginningPossibleBeh";
    public static void setBeginningBehaviors() {
        Buttons.change_body_positions.add("lie_down");
        Buttons.change_body_positions.add("sit_down");
        Buttons.change_body_positions.add("stand_up");
        Buttons.change_body_positions.add("crouch");
        Buttons.change_body_positions.add("climb_to_tree");
        Buttons.explore_surroundings.add("take_notice");
    }
}
