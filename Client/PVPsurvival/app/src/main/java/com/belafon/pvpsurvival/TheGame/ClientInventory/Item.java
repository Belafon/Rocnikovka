package com.belafon.pvpsurvival.TheGame.ClientInventory;


import android.util.Log;

import com.belafon.pvpsurvival.TheGame.AccessToResources.ItemsPropertiesResources;

public class Item {
    private static final String TAG = "Item";
    private String name;
    private String description;
    private int id;
    public Item(String name, int id, String description){
         this.name = name;
        this.description = description;
        this.id = id;
    }

    public String getName() {
        return ItemsPropertiesResources.findItemsNameResource(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
