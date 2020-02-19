package com.belafon.pvpsurvival.TheGame.Stats.Players;


public class FrameFigure {
    private String name;
    private int position;
    public String features;
    private String currentBehaviour;
    private String currentBodyPosition;
    private int id;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getCurrentBehaviour() {
        return currentBehaviour;
    }
    public void setCurrentBehaviour(String currentBehaviour) {
        this.currentBehaviour = currentBehaviour;
    }
    public String getCurrentBodyPosition() {
        return currentBodyPosition;
    }
    public void setCurrentBodyPosition(String currentBodyPosition) {
        this.currentBodyPosition = currentBodyPosition;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public String getFeatures() {
        return features;
    }
    public void setFeatures(String features) {
        this.features = features;
    }

    public FrameFigure(int position, String name){
        this.position = position;
        this.name = name;
        currentBodyPosition = "nothing";
        currentBehaviour = "nothing";
    }
}
