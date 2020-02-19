package com.belafon.pvpsurvival.TheGame.AccessToResources;

import android.content.Context;
import android.util.Log;

import com.belafon.pvpsurvival.R;
import com.belafon.pvpsurvival.TheGame.ClientInventory.ClothesC;
import com.belafon.pvpsurvival.TheGame.ClientInventory.FoodC;
import com.belafon.pvpsurvival.TheGame.ClientInventory.Item;
import com.belafon.pvpsurvival.TheGame.ClientInventory.QuestItemsC;
import com.belafon.pvpsurvival.TheGame.ClientInventory.ToolC;
import com.belafon.pvpsurvival.TheGame.GameActivity;

import java.util.HashMap;

/**
 * Created by ticha on 12.12.2019.
 */

public class ItemsPropertiesResources {
    private static final String TAG = "ItemsPropertiesResource";
    private static HashMap<String, String> itemsProperties = new HashMap<>();
    private static HashMap<String, String> itemsName = new HashMap<>();
    public static void setItemResourcesHashes(){
        setItemsPropertiesHash();
        setItemsNameHash();
    }
    private static void setItemsNameHash(){
        itemsName.put("nameItem_apple", GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_nameItem_apple));
        itemsName.put("nameItem_leatherJacket", GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_nameItem_leatherJacket));
    }
    private static void setItemsPropertiesHash(){
        itemsProperties.put("lookItem_apple", GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_lookItem_apple));
        itemsProperties.put("lookItem_leatherJacket", GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_lookItem_leatherJacket));
    }
    public static String findItemsNameResource(String name){
        return itemsName.get(name);
    }
    public static String findItemsPropertiesResource(Item item){
        String name = item.getDescription();
        String[] nameMessage = name.split(" ");

        short typeOfItem = 0;// BASIC ITEM
        if (item instanceof FoodC)typeOfItem = 1;
        else if(item instanceof ToolC)typeOfItem = 2;
        else if(item instanceof ClothesC)typeOfItem = 3;
        else if(item instanceof QuestItemsC)typeOfItem = 4;

        String outputItemCondition = getItemCondition(Integer.parseInt(nameMessage[0]), typeOfItem);
        return itemsProperties.get(nameMessage[1]) + " " + outputItemCondition;
    }
    public static String getItemCondition(int value, short typeOfItem) {
        String condition = "Condition not found ";

        if(value < 10){
            switch(typeOfItem) {
                case 0:
                    condition = GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_condition_Basic_rotten);
                    break;
                case 1:
                    condition = GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_condition_Food_rotten);
                    break;
                case 2:
                    condition = GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_condition_Tool_rotten);
                    break;
                case 3:
                    condition = GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_condition_Clothes_rotten);
                    break;
                case 4:
                    condition = GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_condition_Quest_rotten);
                    break;
            }
        }else if(value < 30){
            switch(typeOfItem) {
                case 0:
                    condition = GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_condition_Basic_bad);
                    break;
                case 1:
                    condition = GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_condition_Food_bad);
                    break;
                case 2:
                    condition = GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_condition_Tool_bad);
                    break;
                case 3:
                    condition = GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_condition_Clothes_bad);
                    break;
                case 4:
                    condition = GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_condition_Quest_bad);
                    break;
            }
        }else if(value < 60){
            switch(typeOfItem) {
                case 0:
                    condition = GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_condition_Basic_medium);
                    break;
                case 1:
                    condition = GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_condition_Food_medium);
                    break;
                case 2:
                    condition = GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_condition_Tool_medium);
                    break;
                case 3:
                    condition = GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_condition_Clothes_medium);
                    break;
                case 4:
                    condition = GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_condition_Quest_medium);
                    break;
            }
        }else if(value < 80){
            switch(typeOfItem) {
                case 0:
                    condition = GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_condition_Basic_fine);
                    break;
                case 1:
                    condition = GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_condition_Food_fine);
                    break;
                case 2:
                    condition = GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_condition_Tool_fine);
                    break;
                case 3:
                    condition = GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_condition_Clothes_fine);
                    break;
                case 4:
                    condition = GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_condition_Quest_fine);
                    break;
            }
        }else{
            switch(typeOfItem) {
                case 0:
                    condition = GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_condition_Basic_fresh);
                    break;
                case 1:
                    condition = GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_condition_Food_fresh);
                    break;
                case 2:
                    condition = GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_condition_Tool_fresh);
                    break;
                case 3:
                    condition = GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_condition_Clothes_fresh);
                    break;
                case 4:
                    condition = GameActivity.gameActivit.getResources().getString(R.string.ItemDescriptionResources_condition_Quest_fresh);
                    break;
            }
        }
        return condition;
    }
}
