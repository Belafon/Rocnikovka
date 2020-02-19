package com.belafon.pvpsurvival.TheGame.Communication;

import android.util.Log;

import com.belafon.pvpsurvival.R;
import com.belafon.pvpsurvival.TheGame.Behavior.LookAroundButtons;
import com.belafon.pvpsurvival.TheGame.ClientInventory.ClothesC;
import com.belafon.pvpsurvival.TheGame.ClientInventory.FoodC;
import com.belafon.pvpsurvival.TheGame.ClientInventory.Item;
import com.belafon.pvpsurvival.TheGame.ClientInventory.QuestItemsC;
import com.belafon.pvpsurvival.TheGame.ClientInventory.ToolC;
import com.belafon.pvpsurvival.TheGame.GameActivity;
import com.belafon.pvpsurvival.TheGame.GameSound;
import com.belafon.pvpsurvival.TheGame.SetActivity.SetActivity;
import com.belafon.pvpsurvival.TheGame.Stats.Players.MainPlayer;
import com.belafon.pvpsurvival.TheGame.Stats.Players.Players;
import com.belafon.pvpsurvival.TheGame.Time.Time;
import com.belafon.pvpsurvival.TheGame.Weather.Weather;
import com.belafon.pvpsurvival.TheGame.Weather.WeatherStats;
import com.belafon.pvpsurvival.TheGame.Stats.Players.Player;

// GET VALUES FORM SERVER, MESSAGE WAS SENT FROM SERVER
public class Getters {
    private static final String TAG = "Getters";
    public static void  findGetter(String[] message){
        Log.d(TAG, "findGetter: Lets choose the getter");
        switch(message[1]){
            case "get_background_and_sound":
                int k = GameActivity.setNumberOfCurrentThreads(+1,"get_background_and_sound",-1);
                get_background_and_sound(message);
                GameActivity.setNumberOfCurrentThreads(-1, "get_background_and_sound", k);
                break;
            case "get_part_of_day":
                int l = GameActivity.setNumberOfCurrentThreads(+1,"get_part_of_day",-1);
                changePartOfDay = true;
                Log.d(TAG, "findGetter: lets get part of day " + changePartOfDay);
                get_part_of_day(message);
                GameActivity.setNumberOfCurrentThreads(-1, "get_part_of_day", l);
                break;
            case "set_color_filter":
                int m = GameActivity.setNumberOfCurrentThreads(+1,"set_color_filter",-1);
                SetActivity.setFilterColor(Integer.parseInt(message[2]), 0, 0,Integer.parseInt(message[3]));
                GameActivity.setNumberOfCurrentThreads(-1, "set_color_filter", m);
                break;
            case "get_clouds": // jestli padá stín od mraku
                int n = GameActivity.setNumberOfCurrentThreads(+1,"get_wierd_clouds",-1);
                Weather.setChangeOfClouds(true);
                get_clouds(Integer.parseInt(message[2]));
                GameActivity.setNumberOfCurrentThreads(-1, "get_wierd_clouds", n);
                break;
            case "get_type_of_weather": // jestli prší
                int o = GameActivity.setNumberOfCurrentThreads(+1,"get_type_of_weather",-1);
                get_type_of_weather(message[2]);
                GameActivity.setNumberOfCurrentThreads(-1, "get_type_of_weather", o);
                break;
            case "look_around":
                LookAroundButtons.setLook_around(message);
                break;
            case "add_item_to_inventory":
                Item item = solve_item_view(message);
                break;
            case "remove_item_from_inventory":

                break;
            case "get_current_behaviour":
                get_current_behaviour(message); // playerId, typeOfCurrentBehaviour
                break;
            case "get_main_id":
                Players.mainPlayer.setId(Integer.parseInt(message[2]));
                break;
        }
    }

    private static void get_current_behaviour(String[] message) {
        Player player = Players.getPlayer(Integer.parseInt(message[2]));
        if(message[3] == "move" && player.getCurrentBehaviour() != "move"){
            GameSound.startCurrentWalkSound();
        }else if(!message[3].equals("move") && player.getCurrentBehaviour().equals("move")){
            GameSound.stopCurrentWalkSound();
        }
        player.setCurrentBehaviour(message[3]);

    }

    // it gets gotten string and separate on information of Item
    private static Item solve_item_view(String[] message) {
        Item item = null ;
        Log.d(TAG, "solve_item_view: Item is adding");
        switch(message[2]){
            // name, id, toss, quality, look
            case "Item.Tool":
                item = new ToolC(message[3], Integer.parseInt(message[4]),
                        Integer.parseInt(message[5]) + " " + message[6]);
                GameActivity.inventary.toolsInBag.add((ToolC)item);
                break;
            case "Item.Food":
                Log.d(TAG, "solve_item_view: Lets add food");
                item = new FoodC(message[3], Integer.parseInt(message[6]),
                         message[4] + " " + message[5]);
                Log.d(TAG, "solve_item_view: New Food Created");
                GameActivity.inventary.foodInBag.add((FoodC)item);
                Log.d(TAG, "solve_item_view: New Food Added");
                break;
            case "Item.Clothes":
                item = new ClothesC(message[3], Integer.parseInt(message[4]),
                        Integer.parseInt(message[5]) + " " + message[6]);
                GameActivity.inventary.clothesInBag.add((ClothesC)item);
                break;
            case "Item.QuestItem":
                item = new QuestItemsC(message[3], Integer.parseInt(message[4]),
                        Integer.parseInt(message[5]) + " " + message[6]);
                GameActivity.inventary.questItemsInBag.add((QuestItemsC)item);
                break;
            case "Item.BasicItem":
                item = new Item(message[3], Integer.parseInt(message[4]),
                        Integer.parseInt(message[5]) + " " + message[6]);
                GameActivity.inventary.basicItemsInBag.add((Item)item);
                break;
        }
        return item;
    }

    public static void get_type_of_weather(String weatherString) {
        if(weatherString != "nothing" && Weather.isMakeingClouds)Weather.setChangeOfClouds(true);
        while(true){
            if(!Weather.isChangeOfClouds() || !Weather.isMakeingClouds){
                Log.d(TAG, "get_type_of_weather: kokoska3x");
                break;
            }
        }
        Log.d(TAG, "get_type_of_weather: ITS IS CONTINUE CONTINUE " + weatherString);
        Log.d(TAG, "get_type_of_weather: ITS IS CONTINUE CONTINUE");

        switch(weatherString){
            case "nothing":
                Weather.setNothing();
                break;
            case "rain":
                Weather.setRain();
                break;
            case "heavy_rain":
                Weather.setHeavyRain();
                break;
            case "storm":
                Weather.setStorm();
                break;
            case "thunderstorm":
                Weather.setThunderstorm();
                break;
            default:
                System.out.println("Something went wrong");
        }
    }

    public static synchronized void get_clouds(final int clouds) {
        WeatherStats.setClouds(clouds);
        if(WeatherStats.getTypeWeather() == 0)
        new Thread(new Runnable() {
            @Override
            public void run() {
                int k = GameActivity.setNumberOfCurrentThreads(+1,"getCloud",-1);
                switch(clouds){
                    case 0:
                        Log.d(TAG, "run: 0");
                        Weather.makeTranstionBetweenCloudAndSunClouds(0);
                        Weather.setChangeOfClouds(false);
                        break;
                    case 1:
                        Log.d(TAG, "run: 1");
                        Weather.makeClouds(100, 4, 0);
                        break;
                    case 2:
                        Log.d(TAG, "run: 2");
                        Weather.makeClouds(110, 6, 2);
                        break;
                    case 3:
                        Log.d(TAG, "run: 3");
                        Weather.makeClouds(110, 8, 4);
                        break;
                    case 4:
                        Log.d(TAG, "run: 4");
                        Weather.makeTranstionBetweenCloudAndSunClouds(110);
                        Weather.setChangeOfClouds(false);
                        break;
                    case 5:
                        Log.d(TAG, "run: 5");
                        Weather.makeTranstionBetweenCloudAndSunClouds(150);
                        Weather.setChangeOfClouds(false);
                        break;
                }
                GameActivity.setNumberOfCurrentThreads(-1,"getCloud",k);
            }
        }
        ).start();
    }

    /* From server came message about change part of day */
    public static boolean changePartOfDay = false;
    private synchronized static void get_part_of_day(String[] message) {
        Log.d(TAG, "get_part_of_day: get part of day method invoked");
        Log.d(TAG, "get_part_of_day: get part of day method invoked");
        changePartOfDay = false;
        Time.getPartOfDay(message[2]);
    }

    /* The method will set varriables used for setting the
    * background image and sound*/
    public static void get_background_and_sound(String[] message) {
        Log.d(TAG, "get_background_and_sound: Lets set BACKGROUND ........");
        int song = 0;
        int drawable = 0;
        // background image
        switch(message[2]){
            case "forest_leafy_a":
                MainPlayer.setTypeOfSurrounding("forest_leafy");
                drawable = R.drawable.forest_leafy_a;
                if(GameSound.getClipsSounds().size() == 0)GameSound.setMainSound("forest_leafy", 1, false);
                else GameSound.changeCurrentSong(20);
                GameSound.setCurrentWalkSound(R.raw.walk_forest);
                break;
            case "cliff_a":
                MainPlayer.setTypeOfSurrounding("cliff");
                drawable = R.drawable.cliff_a;
                if(GameSound.getClipsSounds().size() == 0)GameSound.setMainSound("cliff", 1, false);
                else GameSound.changeCurrentSong(80);
                GameSound.setCurrentWalkSound(R.raw.walk_cliff);
                break;
        }
        SetActivity.setBackgroundAndSound(drawable, song, message[2]);
    }
}
