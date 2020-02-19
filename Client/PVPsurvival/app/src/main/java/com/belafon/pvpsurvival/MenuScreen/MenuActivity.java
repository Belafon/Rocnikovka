package com.belafon.pvpsurvival.MenuScreen;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.belafon.pvpsurvival.MainActivity;
import com.belafon.pvpsurvival.PreGame.PreGameActivity;
import com.belafon.pvpsurvival.R;
import com.belafon.pvpsurvival.Sound.ClipsAdapter;
import com.belafon.pvpsurvival.Sound.Pool;
import com.belafon.pvpsurvival.Sound.VolumeHandler;
import com.belafon.pvpsurvival.User.DataLibrary;
import com.belafon.pvpsurvival.User.User;

import java.util.ArrayList;


public class MenuActivity extends AppCompatActivity {

    private static final String TAG = "MenuActivity";

    public static MenuActivity menuActivity;

    public static Fragment fragment1;
    private FragmentManager fragmentManager;
    private android.app.FragmentTransaction fragmentTransaction;

    //Values for intreduuction fragment
    private ViewPager viewPager;
    private SliderAdapter sliderAdapter;

    // values of memory
    private SharedPreferences sharedPreferences;
    private static boolean ifFirstTimeStarted = true; // -> true, jinak false
    private String nameOfMemory = "memory";
    private String SHARED_PREFES = "SharedPrefs";

    //sound
    public static ArrayList<ClipsAdapter> clipsAdapter;

    public static Context contextMenuActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
       hideBars();

        menuActivity = this;
        contextMenuActivity = this;

        // - INTREDUCTION
        if(LoadDataBool()){
            setSliderFragment();
            viewPager = findViewById(R.id.viewPager);
            viewPager.setVisibility(View.VISIBLE);
        }else{
            fragment1 = new MenuFragment();
            openFragment(fragment1);
            DataLibrary dataLibrary = new DataLibrary("name", "nameShared");
            User.setName(dataLibrary.LoadDataString("name", "nameShared"));
        }

        ClipsAdapter clipsAdapter;
        clipsAdapter = new ClipsAdapter();
        clipsAdapter.play(this, R.raw.menu, true, 0.50f);
        clipsAdapter.setVolumeSize(0.50f);
        //volume = VolumeHandler.getVolumeOfMainSound();
        this.clipsAdapter = new ArrayList<ClipsAdapter>();
        this.clipsAdapter.add(clipsAdapter);
        Pool pool = new Pool(); // set mechanism for sounds
        pool.letsRun();
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        TextView textName = MenuActivity.menuActivity.findViewById(R.id.textName);
        textName.setHint(User.getName());
    }

    // SET VOLUME, WHEN IT WAS CHANGED

    private int currentApiVersion;
    public void hideBars(){
        getSupportActionBar().hide(); // hide the title bar
        // HIDE NAVIGATION BAR
        currentApiVersion = android.os.Build.VERSION.SDK_INT;

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        // This work only for android 4.4+
        if(currentApiVersion >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(flags);
            // Code below is to handle presses of Volume up or Volume down.
            // Without this, after pressing volume buttons, the navigation bar will
            // show up and won't hide
            final View decorView = getWindow().getDecorView();
            decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
            {

                @Override
                public void onSystemUiVisibilityChange(int visibility)
                {
                    if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) decorView.setSystemUiVisibility(flags);
                }
            });
        }

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    public void openFragment(Fragment fragment){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.menu_fragment, fragment);
        fragmentTransaction.commit();
    }
    // FRAGMENT, WHEN APP IS FIRST OPENED -------------------------------------

    private void setSliderFragment(){
        sliderAdapter = new SliderAdapter(this);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(sliderAdapter);
    }
    public void saveData(){
        sharedPreferences = getSharedPreferences(SHARED_PREFES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(nameOfMemory, false);
        editor.commit();
        DataLibrary save = new DataLibrary("name", "nameShared");
        EditText editText = findViewById(SliderAdapter.idEditText);
        save.saveDataString(editText.getText().toString());
    }
    public boolean LoadDataBool(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFES, MODE_PRIVATE);
        ifFirstTimeStarted = sharedPreferences.getBoolean(nameOfMemory, ifFirstTimeStarted);
        return ifFirstTimeStarted;
    }

    // FRAGMENT MENU ----------------------------------------------------------

    public void NewGame(View view){
        float volume = 1;
        Log.d(TAG, "NewGame: AHOJKY " + volume);
        Pool.playMenuSound(3, volume);
        newGameContinue(false);
    }
    public void Character(View view){
        float volume = 1;
        Log.d(TAG, "NewGame: AHOJKY " + volume);
        Pool.playMenuSound(1, volume);
    }
    public void Stats(View view){
        float volume = 1;
        Log.d(TAG, "NewGame: AHOJKY " + volume);
        Pool.playMenuSound(2, volume);
    }
    public void StartSettings(View view){
        float volume = 1;
        Log.d(TAG, "NewGame: AHOJKY " + volume);
        Pool.playMenuSound(3, volume);
    }
    public void About(View view){
        float volume = 1;
        Log.d(TAG, "NewGame: AHOJKY " + volume);
        Pool.playMenuSound(4, volume);
    }
    public void Exit(View view){
        float volume = 1;
        Log.d(TAG, "NewGame: AHOJKY " + volume);
        finish();
        moveTaskToBack(true);
        Pool.playMenuSound(5, volume);
        stopClipsAdapters();
    }

    public static void stopClipsAdapters() {
        int size = clipsAdapter.size();
        for (int i = 0; i < size; i++) {
            clipsAdapter.get(i).stop();
        }
    }

    public void newGameContinue(boolean first) {
        if(first)menuActivity.saveData(); // uložení value pro příští neotevření intreduction je až zde.
        Intent menuIntent = new Intent(MenuActivity.menuActivity, PreGameActivity.class);
        startActivity(menuIntent);
    }

    public static float volume;
    public void setVolume(float volume) {
        this.volume = volume;
    }
}
