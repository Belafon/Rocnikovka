package com.belafon.pvpsurvival.TheGame.ClientInventory;

import java.util.ArrayList;

/**
 * Created by ticha on 14.12.2019.
 */

public class ClientInventory {
    // BAG
    // veškeré items v batohu
    public ArrayList<FoodC> foodInBag;
    public ArrayList<ClothesC> clothesInBag;
    public ArrayList<ToolC> toolsInBag;
    public ArrayList<QuestItemsC> questItemsInBag;
    public ArrayList<Item> basicItemsInBag;
    // HANDS
    // items in your hands může být pouze nástroj
    private ToolC leftHand;
    private ToolC rightHand;

    public ClientInventory() {
        foodInBag = new ArrayList<>();
        clothesInBag = new ArrayList<>();
        toolsInBag = new ArrayList<>();
        questItemsInBag = new ArrayList<>();
        basicItemsInBag = new ArrayList<>();
        //totalWeight = player.get;
        //sizeOfExhaustion = (currentWeight / totalWeight) * 100;
    }

    public synchronized void addFoodInBag(FoodC food) {
        foodInBag.add(food);
    }
    public synchronized void addClothesInBag(ClothesC clothes) {
        clothesInBag.add(clothes);
    }
    public synchronized void addToolsInBag(ToolC tool) {
        toolsInBag.add(tool);
    }
}
