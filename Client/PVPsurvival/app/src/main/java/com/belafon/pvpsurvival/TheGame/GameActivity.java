package com.belafon.pvpsurvival.TheGame;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.belafon.pvpsurvival.MenuScreen.MenuActivity;
import com.belafon.pvpsurvival.MenuScreen.MenuFragment;
import com.belafon.pvpsurvival.R;
import com.belafon.pvpsurvival.Sound.ClipsAdapter;
import com.belafon.pvpsurvival.TheGame.AccessToResources.ItemsPropertiesResources;
import com.belafon.pvpsurvival.TheGame.ClientInventory.ClientInventory;
import com.belafon.pvpsurvival.TheGame.ClientInventory.Item;
import com.belafon.pvpsurvival.TheGame.Behavior.Buttons;
import com.belafon.pvpsurvival.TheGame.ClientInventory.ToolC;
import com.belafon.pvpsurvival.TheGame.Communication.Getters;
import com.belafon.pvpsurvival.TheGame.Fragments.LookAroundFragment;
import com.belafon.pvpsurvival.TheGame.Behavior.SetBeginningPossibleBehavior;
import com.belafon.pvpsurvival.TheGame.Fragments.BehaviorFragment;
import com.belafon.pvpsurvival.TheGame.Fragments.ChatFragment;
import com.belafon.pvpsurvival.TheGame.Fragments.GameMenuFragment;
import com.belafon.pvpsurvival.TheGame.Fragments.InventaryFragment;
import com.belafon.pvpsurvival.TheGame.Server.Client;
import com.belafon.pvpsurvival.TheGame.SetActivity.SetActivity;
import com.belafon.pvpsurvival.TheGame.maps.Places;
import com.belafon.pvpsurvival.TheGame.Stats.Players.Players;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    private static final String TAG = "GameActivity";
    public static int GameId = -1;
    public static int PlayersId = -1;
    public static Activity gameActivity;
    public static GameActivity gameActivit;
    public static View colorFilter;
    public static View colorFilterCloud;
    public static ClipsAdapter clipsAdapter;
    public static Fragment fragment;
    private FragmentManager fragmentManager;
    private android.app.FragmentTransaction fragmentTransaction;
    public static ClientInventory inventary = new ClientInventory();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideBars();
        setContentView(R.layout.activity_game);
        MenuActivity.stopClipsAdapters(); //
            //} Zastavení hudby

        gameActivity = this;
        gameActivit = this;
        colorFilter = findViewById(R.id.color_filter);
        colorFilterCloud = findViewById(R.id.color_filter_clouds);
        int k = GameActivity.setNumberOfCurrentThreads(+1,"UI",-1);

        Places places = new Places();

        Client client = new Client();
        Thread thread = new Thread(client);
        thread.start();

        setBeginingPopap();

        fragment = new GameMenuFragment();
        openFragment(fragment);

        ItemsPropertiesResources.setItemResourcesHashes();

      /*  SetBeginningPossibleBehavior.setBeginningBehaviors();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Client.decomposeTheString("getter get_type_of_weather thunderstorm");
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Client.decomposeTheString("getter get_type_of_weather nothing");
            }
        }).start();*/
    }
    public static Players players = new Players();

    public void send_letter(View view){
        Log.d(TAG, "send_letter: Button touched");
        EditText editText = findViewById(R.id.chatEditText);
        final String message = String.valueOf(editText.getText());
        if(message.length() == 0)return;

        TextView text = new TextView(gameActivity);
        text.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        text.setTextColor(Color.rgb(50,255,50));
        text.setTextSize(18);
        text.setText(message);
        LinearLayout linearLayout = findViewById(R.id.chat);
        linearLayout.addView(text);
        skipDownInChat();
        editText.setText("");
        new Thread(new Runnable() {
            @Override
            public void run() {
                int k = setNumberOfCurrentThreads(+1, "chat_wirte_to_server", -1);
                Log.d(TAG, "run: send letter, new thread created");
                Client.write("chat " + message);
                setNumberOfCurrentThreads(-1,"chat_wirte_to_server", k);
            }
        }).start();
        Log.d(TAG, "send_letter: method is continue");
    }
    public void showForeignMessage(final String message){
        Log.d(TAG, "showForeignMessage: Game Activity 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4");
        SetActivity.setPopapChat(message);
        Log.d(TAG, "showForeignMessage: eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
    }

    public void skipDownInChat(){
        ScrollView sliderAdapter = findViewById(R.id.chatScrollView);
        sliderAdapter.fullScroll(View.FOCUS_DOWN);
    }

    // DISPLAYING THREADS
    private static int currentThreads = 0; // main thread from the beginning
    private static ArrayList<String> threads = new ArrayList<>();
    private static int pocitadlo = 0;
    private static ArrayList<String> typeThreads = new ArrayList<>(); // ukládá pouze typ vlákna bez id
    public static int getPositionOfThread(String name){
        return typeThreads.indexOf(name);
    }
    // IT COUNTS NUMBER OF CURRENT THREADS
    public static synchronized int setNumberOfCurrentThreads(int newThreads, String typeOfThread, int pocitadlo2){
      /*  currentThreads += newThreads;
        if(newThreads == 1){
            pocitadlo++;
            threads.add(typeOfThread + "_" + pocitadlo);
            typeThreads.add(typeOfThread);
            Log.d(TAG, "setNumberOfCurrentThreads: THREAD ADDED");
        }else{
            typeThreads.remove(typeOfThread);
            threads.remove(typeOfThread + "_" + pocitadlo2);
            Log.d(TAG, "setNumberOfCurrentThreads: THRAD REMOVED");
        }
        Log.d(TAG, "setNumberOfCurrentThreads:  NUMBER OF CURRENT THREARS = " + currentThreads);
        for (int i = 0; i < threads.size(); i++) Log.d(TAG, "setNumberOfCurrentThreads: THREAD - " + threads.get(i));
        return pocitadlo;*/
      return 0;
    }

    // FRAGMENTS
    public void openFragment(Fragment fragment){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }
    public void changeFragment(View view){
        if(view == findViewById(R.id.Behavior)){
            Log.d(TAG, "changeFragment: KOKOSka1");
            fragment = new BehaviorFragment();
        }else if(view == findViewById(R.id.Inventory)){
            fragment = new InventaryFragment();
        }else if(view == findViewById(R.id.Chat)){
            fragment = new ChatFragment();
        }else if(view == findViewById(R.id.Trip)){
            fragment = new LookAroundFragment();
        }else if(view == findViewById(R.id.menu_fragment)){
            Log.d(TAG, "changeFragment: KOKOSka 2");
            fragment = new MenuFragment();
        }
        openFragment(fragment);
    }

    public void goBackToGameMenu(View view){
        fragment = new GameMenuFragment();
        openFragment(fragment);
    }
    // POPAP
    public static TextView popapText1;
    public static TextView popapText2;
    public static TextView popapText3;
    public void dissapearPopap(View view){
        SetActivity.stopPopap = true;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(popapText1.getVisibility() == View.VISIBLE)popapText1.setClickable(false);
                if(popapText2.getVisibility() == View.VISIBLE)popapText2.setClickable(false);
                if(popapText3.getVisibility() == View.VISIBLE)popapText3.setClickable(false);
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 144; i > 0; i-=2) {
                    setAlpha(i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                setGoneVisibility();
            }
            private void setAlpha(final int alpha){
                GameActivity.gameActivit.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(GameActivity.popapText1.getVisibility() == View.VISIBLE){
                            GameActivity.popapText1.setBackgroundColor(Color.argb(alpha, 0, 0, 0));
                            GameActivity.popapText1.setHintTextColor(Color.argb(alpha, 255, 255, 255));
                        }
                        if(GameActivity.popapText2.getVisibility() == View.VISIBLE){
                            GameActivity.popapText2.setBackgroundColor(Color.argb(alpha, 0, 0, 0));
                            GameActivity.popapText2.setHintTextColor(Color.argb(alpha, 255, 255, 255));
                        }
                        if(GameActivity.popapText3.getVisibility() == View.VISIBLE){
                            GameActivity.popapText3.setBackgroundColor(Color.argb(alpha, 0, 0, 0));
                            GameActivity.popapText3.setHintTextColor(Color.argb(alpha, 255, 255, 255));
                        }
                    }
                });
            }
            private void setGoneVisibility(){
                GameActivity.gameActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        popapText1.setVisibility(View.GONE);
                        popapText2.setVisibility(View.GONE);
                        popapText3.setVisibility(View.GONE);
                        popapText1.setBackgroundColor(Color.argb(144, 0, 0, 0));
                        popapText2.setBackgroundColor(Color.argb(144, 0, 0, 0));
                        popapText3.setBackgroundColor(Color.argb(144, 0, 0, 0));
                        popapText1.setHintTextColor(Color.argb(255, 255,255,255));
                        popapText2.setHintTextColor(Color.argb(255, 255,255,255));
                        popapText3.setHintTextColor(Color.argb(255, 255,255,255));
                    }
                });
            }
        }).start();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(popapText1.getVisibility() == View.VISIBLE)popapText1.setClickable(true);
                if(popapText2.getVisibility() == View.VISIBLE)popapText2.setClickable(true);
                if(popapText3.getVisibility() == View.VISIBLE)popapText3.setClickable(true);
            }
        });
    }
    public void setBeginingPopap(){
        popapText1 = gameActivity.findViewById(R.id.popapTextNoticed);
        popapText2 = gameActivity.findViewById(R.id.popapTextFeels);
        popapText3 = gameActivit.findViewById(R.id.popapTextChat);
        SetActivity.setPopapFeels("WOW ahojky ako se dari?");
    }
    public void makePopap(View view){
        SetActivity.setPopapChat("eeeeeeee eeeeee eee eeeee");
        SetActivity.setPopapFeels("khas sjhdk akjh iwd iuwh hdks aksj das");
    }

    // GO BACK TO MENU
    public void backToPreGame(View view){
        Intent menuIntent = new Intent(this, MenuActivity.class);
        startActivity(menuIntent);
    }

    // BEHAVIOUR
    public static Buttons buttons;
    public void makeBehavior(View view){
        LinearLayout linearLayout = findViewById(R.id.behavior_menu_buttons);
        linearLayout.setVisibility(View.GONE);
        if(view.getId() == buttons.change_body_position){
            Buttons.change_body_position();
        }else if(view.getId() == buttons.create_something){
            Buttons.create_something();
        }else if(view.getId() == buttons.explore_surrounding){
            Buttons.explore_surrounding();
        }else if(view.getId() == buttons.fight){
            Buttons.fight();
        }else if(view.getId() == buttons.hunt){
            Buttons.hunt();
        }else if(view.getId() == buttons.travel){
            SetActivity.changeFragment("Trip");
        }
    }

    // INVENTORY
    public void setInventaryInfoAboutItemGone(View view){
        findViewById(R.id.inventary_background_cant_be_touched_specific_item).setVisibility(View.GONE);
        findViewById(R.id.relLaySignAboutOneItem).setVisibility(View.GONE);
        currentItemsList.setVisibility(View.VISIBLE);
        findViewById(R.id.menu_inventory_layout).setVisibility(View.VISIBLE);


    }
    public void setInventaryInfoAboutItemsGone(View view){
        findViewById(R.id.inventary_background_cant_be_touched).setVisibility(View.GONE);
        findViewById(R.id.relLaySignAboutItmes).setVisibility(View.GONE);
        LinearLayout linearLayout = findViewById(R.id.list_items);
        linearLayout.removeAllViews();

    }
    public void inventary_food_but(View view){
        findViewById(R.id.inventary_background_cant_be_touched).setVisibility(View.VISIBLE);
        findViewById(R.id.relLaySignAboutItmes).setVisibility(View.VISIBLE);
        currentItemsList = findViewById(R.id.relLaySignAboutItmes);
        LinearLayout linearLayout = findViewById(R.id.list_items);
        int number = 0;
        for(Item item : inventary.foodInBag){
            addItem(item, linearLayout, number);
            number++;
        }
    }
    public void inventary_clothes_but(View view){
        findViewById(R.id.inventary_background_cant_be_touched).setVisibility(View.VISIBLE);
        findViewById(R.id.relLaySignAboutItmes).setVisibility(View.VISIBLE);
        currentItemsList = findViewById(R.id.relLaySignAboutItmes);
        LinearLayout linearLayout = findViewById(R.id.list_items);
        int number = 0;
        for(Item item : inventary.clothesInBag){
            addItem(item, linearLayout, number);
            number++;
        }
    }
    public void inventary_tools_but(View view){
        findViewById(R.id.inventary_background_cant_be_touched).setVisibility(View.VISIBLE);
        findViewById(R.id.relLaySignAboutItmes).setVisibility(View.VISIBLE);
        currentItemsList = findViewById(R.id.relLaySignAboutItmes);
        LinearLayout linearLayout = findViewById(R.id.list_items);
        int number = 0;
        for(Item item : inventary.toolsInBag){
            addItem(item, linearLayout, number);
            number++;
        }
    }
    private  RelativeLayout currentItemsList;
    private void addItem(final Item item, LinearLayout linearLayout,final int number) {
        Button button = new Button(this);
        LinearLayout.LayoutParams layoutParams = new  LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setBackgroundColor(getResources().getColor(R.color.transparentGreen));
        button.setLayoutParams(layoutParams);
        button.setHint(item.getName());
        button.setHintTextColor(getResources().getColor(R.color.white));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.inventary_background_cant_be_touched_specific_item).setVisibility(View.VISIBLE);
                RelativeLayout relativeLayout = findViewById(R.id.relLaySignAboutOneItem);
                relativeLayout.setVisibility(View.VISIBLE);

                currentItemsList.setVisibility(View.GONE);
                findViewById(R.id.menu_inventory_layout).setVisibility(View.GONE);

                // lets set up description of item
                TextView description = new TextView(gameActivity);
                LinearLayout.LayoutParams textViewLayoutParams = new LinearLayout.LayoutParams(500, 200);
                textViewLayoutParams.setMargins(10, 50, 0, 0);
                description.setLayoutParams(textViewLayoutParams);
                description.setText(item.getName() + "\n \n" + ItemsPropertiesResources.findItemsPropertiesResource(item));
                relativeLayout.addView(description);
                description.setTextColor(getResources().getColor(R.color.white));
                description.setTextSize(18);

            }
        });
        linearLayout.addView(button);


    }
    public void inventary_quest_but(View view){
        findViewById(R.id.inventary_background_cant_be_touched).setVisibility(View.VISIBLE);
        findViewById(R.id.relLaySignAboutItmes).setVisibility(View.VISIBLE);
    }
    public void inventary_nature_resources_but(View view){
        findViewById(R.id.inventary_background_cant_be_touched).setVisibility(View.VISIBLE);
        findViewById(R.id.relLaySignAboutItmes).setVisibility(View.VISIBLE);
    }
    public void inventary_others_but(View view){
        findViewById(R.id.inventary_background_cant_be_touched).setVisibility(View.VISIBLE);
        findViewById(R.id.relLaySignAboutItmes).setVisibility(View.VISIBLE);
    }
    public static ArrayList<Item> items = new ArrayList<Item>();


    // HIDE BARS
    private int currentApiVersion;

    @Override
    protected void onStop() {
        super.onStop();
        Server.
    }


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
}
