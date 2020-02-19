package com.belafon.pvpsurvival.TheGame.Weather;


import com.belafon.pvpsurvival.TheGame.likeliness.Kostka;

public class WeatherStats {
    private static int temperatur = 0; // (-5) - (5)
    private static int clouds = 0;
    private static int typeWeather = 0;
    private WeatherStats(){
        generateStats();
    }

    private static void generateStats() {
        temperatur = generateStrengthOfTemperatur(); // od -20 po 5 do +20 stupňů celsia
    }
    private static int generateStrengthOfTemperatur() {
        Kostka kostka = new Kostka(8);
        return  (kostka.hod() - 4) * 5;
    }
    public static int getTemperatur() {
        return temperatur;
    }

    public static void setTemperatur(int temperatur) {
        WeatherStats.temperatur = temperatur;
    }

    public static int getClouds() {
        return clouds;
    }

    public static void setClouds(int clouds) {
        WeatherStats.clouds = clouds;
    }

    public static int getTypeWeather() {
        return typeWeather;
    }

    public static void setTypeWeather(int typeWeather) {
        WeatherStats.typeWeather = typeWeather;
    }
}
