package com.belafon.pvpsurvival.TheGame.AccessToResources;

import android.content.Context;

import com.belafon.pvpsurvival.R;
import com.belafon.pvpsurvival.TheGame.GameActivity;

/**
 * Created by ticha on 24.11.2019.
 */

public class EffectsResources {
    public static String findEffectResource(String name){
        Context context = GameActivity.gameActivity;
        String output = "Not Founded";
        switch(name){
            case "fire":
                output  = context.getResources().getString( R.string.effect_fire);
                break;
        }
        return output;
    }
}
