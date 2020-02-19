package com.belafon.pvpsurvival.TheGame.AccessToResources;

import android.content.Context;

import com.belafon.pvpsurvival.R;
import com.belafon.pvpsurvival.TheGame.GameActivity;

/**
 * Created by ticha on 24.11.2019.
 */

public class PlacesLookResources {
    public static String findPlacesLookResource(String name){
        Context context = GameActivity.gameActivity;
        String output = "Not Founded";
        switch(name){
            case "basicLook_cliff":
                output  = context.getResources().getString( R.string.basicLook_cliff);
                break;
            case "basicLook_forest_leafy":
                output  = context.getResources().getString( R.string.basicLook_forest_leafy);
                break;
        }
        return output;
    }
}
