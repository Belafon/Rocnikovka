package com.belafon.pvpsurvival.TheGame.AccessToResources;

import android.content.Context;
import android.util.Log;

import com.belafon.pvpsurvival.R;
import com.belafon.pvpsurvival.TheGame.GameActivity;

/**
 * Created by ticha on 24.11.2019.
 */

public class ColorsResources {
    private static final String TAG = "ColorsResources";
    public static int findEffectResource(String name){
        Context context = GameActivity.gameActivity;
        int output = -1;
        Log.d(TAG, "findEffectResource: Color = " + name);
        switch(name){
            case "light_green":
                output = R.color.light_green;
                Log.d(TAG, "findEffectResource: light_green");
                break;
            case "light_gray_blue":
                output = R.color.light_gray_blue;
            break;
        }
        return output;
    }
}
