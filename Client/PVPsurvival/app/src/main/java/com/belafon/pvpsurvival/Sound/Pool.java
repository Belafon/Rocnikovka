package com.belafon.pvpsurvival.Sound;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;

import com.belafon.pvpsurvival.MenuScreen.MenuActivity;
import com.belafon.pvpsurvival.R;

public class Pool{
        private static final String TAG = "Pool";

        public static Pool pool;
        public static SoundPool soundPool;
        private static int[] sound = new int[6];

        public Pool(){
            pool = this;
        }

        public static void letsRun() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                AudioAttributes audioAttributes = new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_GAME) // GAME, mohu nastavit
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build();

                soundPool = new SoundPool.Builder()
                        .setMaxStreams(8) // max number of sounds in same time
                        .setAudioAttributes(audioAttributes)
                        .build();
            } else {
                soundPool = new SoundPool(8, AudioManager.STREAM_MUSIC, 0);
            }
            setSoundsToPool(MenuActivity.contextMenuActivity);
        }

    private static void setSoundsToPool(Context context) {
        sound[0] = soundPool.load(context, R.raw.wlof_menu_click, 1);
        sound[1] = soundPool.load(context, R.raw.fly1, 1);
        sound[2] = soundPool.load(context, R.raw.fly2, 1);
        sound[3] = soundPool.load(context, R.raw.fly3, 1);
        sound[4] = soundPool.load(context, R.raw.fly4, 1);
        sound[5] = soundPool.load(context, R.raw.menu_but_click, 1);
    }

    public static void playMenuSound(int song, float volume){
            soundPool.play(sound[song], volume, volume, 1, 0, 1);
        }
    public static void playSoundLoop(int song, int loops){
            soundPool.setLoop(sound[song], loops);
            soundPool.play(sound[song], 1, 1, 0, 0, 1);
        }
    public static void setVolumeAllSounds(float volume){
        volume = volume - 0.0188f;
        int numberOfSounds = sound.length;
        Log.d(TAG, "setVolumeAllSounds: volume: " + volume + " ----------------------------------");
        for (int i = 0; i < numberOfSounds; i++){
            Log.d(TAG, "setVolumeAllSounds: i: " + i + " ----------------------------------");
            soundPool.setVolume(sound[i], volume, volume);
        }
    }
}
