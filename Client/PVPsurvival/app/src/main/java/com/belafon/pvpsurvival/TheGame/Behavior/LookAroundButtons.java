package com.belafon.pvpsurvival.TheGame.Behavior;


import android.app.Fragment;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.belafon.pvpsurvival.R;
import com.belafon.pvpsurvival.TheGame.AccessToResources.ColorsResources;
import com.belafon.pvpsurvival.TheGame.AccessToResources.EffectsResources;
import com.belafon.pvpsurvival.TheGame.AccessToResources.PlacesLookResources;
import com.belafon.pvpsurvival.TheGame.Communication.Setters;
import com.belafon.pvpsurvival.TheGame.Fragments.BehaviorFragment;
import com.belafon.pvpsurvival.TheGame.Fragments.LookAroundFragment;
import com.belafon.pvpsurvival.TheGame.GameActivity;
import com.belafon.pvpsurvival.TheGame.SetActivity.SetActivity;
import com.belafon.pvpsurvival.TheGame.likeliness.Kostka;

public class LookAroundButtons {
    private static final String TAG = "LookAroundButtons";
    private static String[] look_around;
    private static int[] colorsOfButtons;
    private static final int sizeOfButton = 120;
    // LinearLayout, kam přidávám nov buttons
    private static LinearLayout linearLayoutContainer = GameActivity.gameActivit.findViewById(R.id.container_of_places_look_around);
    private static RelativeLayout relLayoutLookAround = GameActivity.gameActivit.findViewById(R.id.look_around_Rel_layout);
    public static void setLook_around(String[] look_aroundd){
        look_aroundd = letsSetText(look_aroundd);
        look_around = look_aroundd;
        // pokud bude právě otevřen fragment, tak se přímo vypíší změny
       // if(LookAroundFragment.isOpened)setButtonsLookAround();
    }
    public static void setButtonsLookAround(){ // zobrazí buttons ve LookAroundFragment
        if(look_around == null){
            Log.d(TAG, "setButtonsLookAround: It has returned, look_around = null!");
            return;
        }
        linearLayoutContainer = GameActivity.gameActivit.findViewById(R.id.container_of_places_look_around);
        if(linearLayoutContainer != null)linearLayoutContainer.removeAllViews();
        int ids = 0;
        for(int i = 0; i < 7; i++){
            LinearLayout linearLayout = makeNewLinearLayout();
            for (int j = 0; j < 7; j++) {
                if(ids == 24){
                    Button button2 = makeButtonView(linearLayout, ids);
                    button2.setBackgroundColor(Color.argb(30,255,230,230));
                  //  button2.setTextSize(30);
                    button2.setHint("*");
                }else{
                    Button button = makeButtonView(linearLayout, ids);
                    button.setId(ids);
                    if(colorsOfButtons[ids] != -1)setClicable(button);
                }
                ids++;
            }
        }
    }

    private static LinearLayout makeNewLinearLayout() {
        LinearLayout linearLayout1 = new LinearLayout(GameActivity.gameActivit);
        linearLayout1.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutContainer.addView(linearLayout1);
        return linearLayout1;
    }

    private static Button makeButtonView(LinearLayout linearLayout, int poradi) {
        Button button = new Button(GameActivity.gameActivit);
        button.setLayoutParams(new LinearLayout.LayoutParams(
                sizeOfButton, sizeOfButton
        ));
       if(colorsOfButtons[poradi] != -1) button.setBackgroundResource(colorsOfButtons[poradi]);
           else {
               button.setBackgroundColor(Color.argb(0,0,0,0));
               button.setHint("x");
           }
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) button.getLayoutParams();
        params.setMargins(10, 10, 0, 0); //substitute parameters for left, top, right, bottom
        button.setLayoutParams(params);
        linearLayout.addView(button);
        return button;
    }

    public static void openSignFreagment() {
        Fragment fragment;
        fragment = new BehaviorFragment();
    }
    private static void setClicable(final Button button){
        new Thread(new Runnable() {
            @Override
            public void run() {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // otevře se jiný fragment s podrobnostmi a možností zmáčknout tlačítko jít zde
                        CreateSign(v.getId());
                    }
                });
            }
        }).start();
    }
    private static RelativeLayout backgroundButton;

    // vytvoří se cedulka s infem o místě na které kliknul užiatel
    private static void CreateSign(int i){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        GameActivity.gameActivity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = (int)(((float)displaymetrics.heightPixels ) * (5f/6f));
        int width = (int)(((float)displaymetrics.widthPixels) * (4f/5f));


        relLayoutLookAround = GameActivity.gameActivit.findViewById(R.id.look_around_Rel_layout);

        // neviditelný RelativLayout bude překážet pozadí, takže hráč nebude moci kliknout na tlačítka v pozadí
        backgroundButton = new RelativeLayout(GameActivity.gameActivit);
        LinearLayout.LayoutParams paramsBackgroundButton = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        );
        backgroundButton.setLayoutParams(paramsBackgroundButton);
        backgroundButton.setBackgroundColor(Color.TRANSPARENT);


        LinearLayout linearLayout1 = new LinearLayout(GameActivity.gameActivit);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                width, height
        );
        params.setMargins((int)((float)(displaymetrics.widthPixels) * (1f/5f) / 2f), (int)((float)(displaymetrics.heightPixels) * (1f/6f) / 2f), 0, 0); //substitute parameters for left, top, right, bottom
        linearLayout1.setLayoutParams(params);
        linearLayout1.setOrientation(LinearLayout.VERTICAL);
        linearLayout1.setBackgroundColor(Color.argb(220, 255, 255, 255));

        setOnClickListenerCloseSign(linearLayout1, backgroundButton);
        relLayoutLookAround.addView(backgroundButton);
        relLayoutLookAround.addView(linearLayout1);

        // textView
        TextView textView = new TextView(GameActivity.gameActivit);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT - 30, height - 120
        );
        layoutParams2.setMargins(20,20, 0, 0);
        textView.setLayoutParams(layoutParams2);
        textView.setHint(look_around[i] + " ;");
        textView.setTextSize(20);
        textView.setHintTextColor(Color.BLACK);
        linearLayout1.addView(textView);

        // button
        Button button = new Button(GameActivity.gameActivit);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 90
        );
        layoutParams.setMargins(0,12, 0, 0);
        button.setLayoutParams(layoutParams);
        button.setHintTextColor(Color.rgb(255, 255, 255));
        button.setHint(R.string.lets_go_there);
        button.setBackgroundResource(R.drawable.button2);
        setOnClickListenerForButLetsTravel(i, button);
        linearLayout1.addView(button);
    }

    private static String[] letsSetText(String[] retez) {// retez je pouze splitnuty message
        String[] placeInfo = new String[49];
        int[] colors = new int[49];
        int numberOfPalce = 0;
        for (int numberOfStringInMessage = 2; numberOfStringInMessage < retez.length; numberOfStringInMessage++) {
            if(retez[numberOfStringInMessage].toCharArray()[0] == 120){ // x
                placeInfo[numberOfPalce] = "x";
                int kolikratNasobenoX = Integer.parseInt(retez[numberOfStringInMessage+1]); // kolik x ek je zasebou
                for (int i = numberOfPalce; i <= numberOfPalce + kolikratNasobenoX - 1; i++) {
                    colors[i] = -1;
                }
                numberOfPalce += Integer.parseInt(retez[numberOfStringInMessage+1]); // kolik x ek je zasebou
                numberOfStringInMessage += 2;
            }else if(retez[numberOfStringInMessage].toCharArray()[0] == 59){ // ;
                Log.d(TAG, "letsSetText: Somethin is Wrong ----------- letsSetText LookAroundButton class");
            }else { // neco
                String info ="";
                switch(new Kostka(4).hod()){
                    case 1:
                        info = GameActivity.gameActivity.getString(R.string.look_around_a);
                        break;
                    case 2:
                        info = GameActivity.gameActivity.getString(R.string.Look_around_b);
                        break;
                    case 3:
                        info = GameActivity.gameActivity.getString(R.string.Look_around_c);
                        break;
                    case 4:
                        info = GameActivity.gameActivity.getString(R.string.Look_around_d);
                        break;
                }
                // získáme barvu tlačítka
                colors[numberOfPalce] = ColorsResources.findEffectResource(retez[numberOfStringInMessage]);
            //    Log.d(TAG, "letsSetText: " + retez[numberOfStringInMessage] + " poleIndex = " + numberOfPalce + " indexOfString = "+ numberOfStringInMessage + " 1");
                numberOfStringInMessage++;

                // získáme základní popis místa
                info += PlacesLookResources.findPlacesLookResource(retez[numberOfStringInMessage]);

                // přidáme effecty
                while (retez[numberOfStringInMessage].toCharArray()[0] != 59) { // dokud nenarazíme na středník
                    info += EffectsResources.findEffectResource(retez[numberOfStringInMessage]);

                    numberOfStringInMessage++;
                }
                placeInfo[numberOfPalce] = info + ".";
                numberOfPalce++;
            }
            //for (int i : colors) Log.d(TAG, "letsSetText: COLOR = (" + i + ")");
        }
        colorsOfButtons = colors;
        return placeInfo;
    }

    private static void setOnClickListenerCloseSign(final LinearLayout linearLayout, final View textView) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        relLayoutLookAround.removeView(linearLayout);
                        backgroundButton.setVisibility(View.GONE);
                        backgroundButton = null;
                    }
                });
            }
        }).start();
    }
    private static void setOnClickListenerForButLetsTravel(final int i, final Button button) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // posun hráče na dané pole
                        Setters.move(i);
                        SetActivity.changeFragment("game_menu_fragment");
                    }
                });
            }
        }).start();
    }
}
