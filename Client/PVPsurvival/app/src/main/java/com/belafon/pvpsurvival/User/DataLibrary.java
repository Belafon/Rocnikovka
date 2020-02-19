package com.belafon.pvpsurvival.User;

import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.util.Log;

import com.belafon.pvpsurvival.MenuScreen.MenuActivity;

public class DataLibrary {
    private static SharedPreferences sharedPreferences;
    private String nameOfMemory = "memory";
    private String SHARED_PREFES = "SharedPrefs";

    public DataLibrary(String nameOfMemory, String nameSharedPreferences){
        this.nameOfMemory = nameOfMemory;
        this.SHARED_PREFES = nameSharedPreferences;
    }
    public void saveDataInteager(int value, SharedPreferences sharedPreferences){
        sharedPreferences = MenuActivity.menuActivity.getSharedPreferences(SHARED_PREFES, MenuActivity.menuActivity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(nameOfMemory, value);
        editor.commit();
    }
    public void saveDataString(String value){
        sharedPreferences = MenuActivity.menuActivity.getSharedPreferences(SHARED_PREFES, MenuActivity.menuActivity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(nameOfMemory, value);
        editor.commit();
    }
    public void saveDataBoolean(boolean value){
        sharedPreferences = MenuActivity.menuActivity.getSharedPreferences(SHARED_PREFES, MenuActivity.menuActivity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(nameOfMemory, value);
        editor.commit();
    }
    public int LoadDataInteager(String name, String nameOfShared){
        SharedPreferences sharedPreferences = MenuActivity.menuActivity.getSharedPreferences(nameOfShared, MenuActivity.menuActivity.MODE_PRIVATE);
        int value = 0;
        value = sharedPreferences.getInt(name, value);
        return value;
    }
    public String LoadDataString(String name, String nameOfShared){
        SharedPreferences sharedPreferences = MenuActivity.menuActivity.getSharedPreferences(nameOfShared, MenuActivity.menuActivity.MODE_PRIVATE);
        String value  = "true";
        value = sharedPreferences.getString(name, value);
        return value;
    }
    public boolean LoadDataBool(String name, String nameOfShared){
        SharedPreferences sharedPreferences = MenuActivity.menuActivity.getSharedPreferences(nameOfShared, MenuActivity.menuActivity.MODE_PRIVATE);
        boolean bool = true;
        bool = sharedPreferences.getBoolean(name, bool);
        return bool;
    }
}
