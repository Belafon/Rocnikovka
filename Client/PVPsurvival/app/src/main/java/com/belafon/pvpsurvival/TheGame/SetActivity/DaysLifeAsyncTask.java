package com.belafon.pvpsurvival.TheGame.SetActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;

import com.belafon.pvpsurvival.TheGame.GameActivity;


public final class DaysLifeAsyncTask extends AsyncTask<Integer, Integer, Integer>{
    @Override
    protected Integer doInBackground(Integer... integers) {
        this.integers = integers;
        return null;
    }
    private Integer[] integers;
    @Override
    protected void onPostExecute(Integer integer) {
        GameActivity.colorFilter.setBackgroundColor(Color.argb(integers[0], integers[1], integers[2], integers[3]));
    }
}
