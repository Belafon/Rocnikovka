package com.belafon.pvpsurvival.TheGame.Behavior;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.belafon.pvpsurvival.R;
import com.belafon.pvpsurvival.TheGame.Communication.Setters;
import com.belafon.pvpsurvival.TheGame.GameActivity;
import com.belafon.pvpsurvival.TheGame.SetActivity.SetActivity;

import java.util.ArrayList;
import java.util.Set;

public class Buttons {
    private static final String TAG = "Buttons";
    public static int sizeOfButton = 290;
    public final int change_body_position = R.id.change_body_position;
    public final int explore_surrounding = R.id.explore_surrounding;
    public final int create_something = R.id.create_something;
    public final int hunt = R.id.hunt;
    public final int travel = R.id.travel;
    public final int fight = R.id.fight;
    // zde se ukládá, jaké behaviours může hráč zahrát
    public static ArrayList<String> change_body_positions = new ArrayList<>();
    public static ArrayList<String> explore_surroundings = new ArrayList<>();
    public static ArrayList<String> create_somethings = new ArrayList<>();
    public static ArrayList<String> hunts = new ArrayList<>();
    public static ArrayList<String> fights = new ArrayList<>();

    public void setButtons(GameActivity gameActivity){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        gameActivity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = (displaymetrics.heightPixels - 85) / 2;
        int width = (displaymetrics.widthPixels - 35) / 3 - 15;

        Button button1 = gameActivity.findViewById(change_body_position);

        ViewGroup.LayoutParams params = button1.getLayoutParams();
        params.height = height;
        params.width = width;
        button1.requestLayout();

        Button button2 = gameActivity.findViewById(explore_surrounding);
        params = button2.getLayoutParams();
        params.height = height;
        params.width = width;
        button1.requestLayout();

        Button button3 = gameActivity.findViewById(create_something);
        params = button3.getLayoutParams();
        params.height = height;
        params.width = width;
        button1.requestLayout();

        Button button4 = gameActivity.findViewById(hunt);
        params = button4.getLayoutParams();
        params.height = height;
        params.width = width;
        button1.requestLayout();

        Button button5 = gameActivity.findViewById(travel);
        params = button5.getLayoutParams();
        params.height = height;
        params.width = width;
        button1.requestLayout();

        Button button6 = gameActivity.findViewById(fight);
        params = button6.getLayoutParams();
        params.height = height;
        params.width = width;
        button1.requestLayout();
    }

    private static LinearLayout linearLayoutLines;
    public static void change_body_position() {
        LinearLayout linearLayout = GameActivity.gameActivity.findViewById(R.id.buttons_change_body_position);
        ScrollView scrollView = GameActivity.gameActivity.findViewById(R.id.scrllview_behavior_1);
        scrollView.setVisibility(View.VISIBLE);
        fill_layout(linearLayout, change_body_positions);
        Log.d(TAG, "makeBehavior: It IS Weard 1");
    }
    public static void explore_surrounding() {
        LinearLayout linearLayout = GameActivity.gameActivity.findViewById(R.id.buttons_explore_surrounding);
        ScrollView scrollView = GameActivity.gameActivity.findViewById(R.id.scrllview_behavior_2);
        scrollView.setVisibility(View.VISIBLE);
        fill_layout(linearLayout, explore_surroundings);
    }
    public static void create_something() {
        LinearLayout linearLayout = GameActivity.gameActivity.findViewById(R.id.buttons_create_something);
        ScrollView scrollView = GameActivity.gameActivity.findViewById(R.id.scrllview_behavior_3);
        scrollView.setVisibility(View.VISIBLE);
        fill_layout(linearLayout, create_somethings);
    }
    public static void fight() {
        LinearLayout linearLayout = GameActivity.gameActivity.findViewById(R.id.buttons_fight);
        ScrollView scrollView = GameActivity.gameActivity.findViewById(R.id.scrllview_behavior_4);
        scrollView.setVisibility(View.VISIBLE);
        fill_layout(linearLayout, fights);
    }
    public static void hunt() {
        LinearLayout linearLayout = GameActivity.gameActivity.findViewById(R.id.buttons_hunt);
        ScrollView scrollView = GameActivity.gameActivity.findViewById(R.id.scrllview_behavior_5);
        scrollView.setVisibility(View.VISIBLE);
        fill_layout(linearLayout, hunts);
    }

    private static void fill_layout(LinearLayout linearLayout, ArrayList<String> arrayList) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        GameActivity.gameActivity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        numberOfButtonsOnOneLine = (width) / 300;
        int modulo = width % 300;
        if((width % 300) > 150){
            sizeOfButton = 290 - ((290 - modulo) / (numberOfButtonsOnOneLine * 2));
            numberOfButtonsOnOneLine++;
        }
        linearLayoutLines = null;

        makeBackButton(linearLayout);
        for (int i = 0; i < arrayList.size(); i++) {
            String string = arrayList.get(i);
            makeButton(string, linearLayout);
        }
    }

    private static void makeButton(String string, LinearLayout linearLayout) {
        if(linearLayoutLines == null)makeNewLineLinearLayout(linearLayout);
        if(linearLayout.getChildCount() == 0 || linearLayoutLines.getChildCount() >= numberOfButtonsOnOneLine)makeNewLineLinearLayout(linearLayout);// MAKE NEW LinearLayout
        Button button = makeButtonView();
        setHint(string, button);
        setClicable(button, string);
    }


    private static void setHint(String string, Button button) {
        switch(string){
            // CHANGE POSITION
            case "lie_down":
                button.setHint("Lie down");
                break;
            case "sit_down":
                button.setHint("Sit down");
                break;
            case "stand_up":
                button.setHint("Stand up");
                break;
            case "crouch":
                button.setHint("Crouch");
                break;
            case "climb_to_tree":
                button.setHint("Climb to tree");
                break;


            //  EXPLORE SURROUNDING
            case "take_notice":
                button.setHint("Take notice");
                break;
        }
        button.setHintTextColor(Color.rgb(255,255,255));
    }
    private static void setClicable(final Button button, final String string){
        new Thread(new Runnable() {
            @Override
            public void run() {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch(string){

                            // CHANGE POSITION
                            case "lie_down":
                                Setters.lie_down();
                                break;
                            case "sit_down":
                                Setters.sit_down();
                                break;
                            case "stand_up":
                                Setters.stand_up();
                                break;
                            case "crouch":
                                Setters.crouch();
                                break;
                            case "climb_to_tree":
                                Setters.climb_to_tree();
                                break;


                            //  EXPLORE SURROUNDING
                            case "take_notice":
                                Setters.take_notice();
                                break;
                        }
                    }
                });
            }
        }).start();
    }


    private static void makeNewLineLinearLayout(LinearLayout linearLayout) {
        LinearLayout linearLayout1 = new LinearLayout(GameActivity.gameActivit);
        linearLayout1.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutLines = linearLayout1;
        linearLayout.addView(linearLayout1);
    }

    private static int numberOfButtonsOnOneLine;
    private static Button makeButtonView() {
        Button button = new Button(GameActivity.gameActivit);
        button.setLayoutParams(new LinearLayout.LayoutParams(
                sizeOfButton, sizeOfButton
        ));
        button.setBackgroundResource(R.drawable.button2);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) button.getLayoutParams();
        params.setMargins(10, 10, 0, 0); //substitute parameters for left, top, right, bottom
        button.setLayoutParams(params);
        linearLayoutLines.addView(button);
        return button;
    }
    private static void makeBackButton(LinearLayout linearLayout) {
        Button button = new Button(GameActivity.gameActivit);
        button.setLayoutParams(new LinearLayout.LayoutParams(
                sizeOfButton, 80
        ));
        button.setBackgroundResource(R.drawable.button2);
        button.setHint("Go back");
        button.setHintTextColor(Color.rgb(255, 255, 255));
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) button.getLayoutParams();
        params.setMargins(10, 10, 0, 0); //substitute parameters for left, top, right, bottom
        button.setLayoutParams(params);
        linearLayout.addView(button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetActivity.changeFragment("game_menu_fragment");
                Log.d(TAG, "onClick: KOKOSKA KOKOSKA KOKOSKA");
            }
        });
    }

}
