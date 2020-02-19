package com.belafon.pvpsurvival.TheGame.SetActivity;

import android.graphics.Color;
import android.os.AsyncTask;

import com.belafon.pvpsurvival.TheGame.GameActivity;

/**
 * Created by ticha on 13.10.2019.
 */

public final class CloudsAsyncTask extends AsyncTask<Integer, Integer, Integer> {
    @Override
    protected Integer doInBackground(Integer... integers) {
        this.integers = integers;
        return null;
    }
    private Integer[] integers;
    @Override
    protected void onPostExecute(Integer integer) {
        GameActivity.colorFilterCloud.setBackgroundColor(Color.argb(integers[0], integers[1], integers[2], integers[3]));
    }
}
