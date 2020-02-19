package com.belafon.pvpsurvival.TheGame.Time;


import android.util.Log;

public class Timer extends Thread{
    private static final String TAG = "Timer";
    public static float time = 0;

    @Override
    public void run() {
        super.run();
        while(true){
            try {
                Thread.currentThread().sleep(998);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time++;
            Log.d(TAG, "COOL time: " + time);
        }
    }
}
