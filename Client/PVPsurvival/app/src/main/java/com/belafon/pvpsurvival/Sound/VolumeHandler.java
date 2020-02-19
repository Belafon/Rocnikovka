package com.belafon.pvpsurvival.Sound;

import android.media.AudioManager;
import android.util.Log;
import android.widget.Toast;

import com.belafon.pvpsurvival.MenuScreen.MenuActivity;
import com.belafon.pvpsurvival.R;

/**
 * Created by ticha on 17.04.2019.
 */

public class VolumeHandler {
    private static final String TAG = "VolumeHandler";
    public static void handleVolumeChanges(final int music_volume_level, final AudioManager audioManager){
        Thread thread = new Thread(){
            public void run(){
                float actual_music_volume_level = music_volume_level;
                while(true){
                    actual_music_volume_level = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                    if(actual_music_volume_level != music_volume_level)break;
                }

                float volume = getVolumeOfMainSound();
                for (int i = 0; i < MenuActivity.clipsAdapter.size(); i++)
                    MenuActivity.clipsAdapter.get(i).setVolume(volume);
 //               Pool.setVolumeAllSounds(volume);
                MenuActivity.menuActivity.setVolume(volume); // to change variable float volume
            }
        };
        thread.start();
    }

    public static float getVolumeOfMainSound() {
        AudioManager audioManager = (AudioManager) MenuActivity.menuActivity.getSystemService(MenuActivity.menuActivity.AUDIO_SERVICE);
        int music_volume_level = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        Log.d(TAG, "getVolumeOfMainSound: music_volume_level = " + music_volume_level);
        Log.d(TAG, "getVolumeOfMainSound: max = " + max);
        float music_volume_level_in_procents = (100 * (float) music_volume_level) / (float) max;
        float volume = 0f;// = ((100f - music_volume_level_in_procents) / 60) - (music_volume_level_in_procents / 100) + 0.02f;
        if(music_volume_level_in_procents < 15.0f){
            MenuActivity.menuActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MenuActivity.contextMenuActivity, R.string.reccomand_more_loud_sound, Toast.LENGTH_LONG).show();/*SHOW recommend to turn up the sound */
                }
            });
        }
        if(music_volume_level_in_procents < 26.7) volume = 1;
        else if(music_volume_level_in_procents < 33.4) volume = 0.82f;
        else if(music_volume_level_in_procents < 40.0f) volume = 0.724f;
        else if(music_volume_level_in_procents < 46.7f) volume = 0.624f;
        else if(music_volume_level_in_procents < 53.4f) volume = 0.524f;
        else if(music_volume_level_in_procents < 60.0f) volume = 0.424f;
        else if(music_volume_level_in_procents < 66.6f) volume = 0.324f;
        else if(music_volume_level_in_procents < 72.0f) volume = 0.224f;
        else if(music_volume_level_in_procents < 80.0f) volume = 0.124f;
        else if(music_volume_level_in_procents < 86.6f) volume = 0.094f;
        else if(music_volume_level_in_procents < 93.4f) volume = 0.064f;
        else if(music_volume_level_in_procents <= 100.0f) volume = 0.04f;

        Log.d(TAG, "getVolumeOfMainSound: VOLUME = " + volume);
        VolumeHandler.handleVolumeChanges(music_volume_level, audioManager); // START NEW THREAD TO VOLUME HANDLE WITH CHANGES
        return volume;
    }
}
