package com.belafon.pvpsurvival.TheGame.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.belafon.pvpsurvival.R;
import com.belafon.pvpsurvival.TheGame.Behavior.Buttons;
import com.belafon.pvpsurvival.TheGame.GameActivity;

public class BehaviorFragment extends Fragment {
   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_behavior, container, false);
    }
    public void onStart() {
        super.onStart();
        GameActivity.gameActivit.buttons = new Buttons();
        GameActivity.gameActivit. buttons.setButtons(GameActivity.gameActivit);
    }
 }
