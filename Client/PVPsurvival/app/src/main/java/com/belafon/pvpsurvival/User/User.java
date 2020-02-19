package com.belafon.pvpsurvival.User;

public class User {
    public static String Name;
    public static int level;
    public static int exps;
    public static int money;

    public static String getName() {
        return Name;
    }
    public static void setName(String name) {
        Name = name;
    }
    public static int getLevel() {
        return level;
    }
    public static void setLevel(int level) {
        User.level = level;
    }
    public static int getExps() {
        return exps;
    }
    public static void setExps(int exps) {
        User.exps = exps;
    }
    public static int getMoney() {
        return money;
    }
    public static void setMoney(int money) {
        User.money = money;
    }
}
