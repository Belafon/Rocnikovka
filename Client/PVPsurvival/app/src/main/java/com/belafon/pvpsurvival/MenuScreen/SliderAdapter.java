package com.belafon.pvpsurvival.MenuScreen;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.belafon.pvpsurvival.R;
import com.belafon.pvpsurvival.Sound.Pool;

public class SliderAdapter extends PagerAdapter{

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    public static int idEditText = 1;
    public int[] slideTextView = {
            R.string.slider1,
            R.string.slider2,
            R.string.slider3,
            R.string.slider4};

    @Override
    public int getCount() {
        return slideTextView.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }

    public Object instantiateItem(ViewGroup conteiner, int position){

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, conteiner, false);
        TextView textView = view.findViewById(R.id.textIntreduction);
        textView.setText(slideTextView[position]);
        if(position == 3){
            LinearLayout linContent = view.findViewById(R.id.linContent);
           // linLaySetUserName.setVisibility(View.VISIBLE);

            final EditText editText = new EditText(MenuActivity.menuActivity);
            editText.setLayoutParams(new LinearLayout.LayoutParams(
                    600,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            editText.setHint(R.string.edittextSetName);
            editText.setHintTextColor(Color.argb(150, 220, 220, 220));
            editText.setTextColor(Color.argb(200, 250, 250, 250));
            editText.setId(idEditText);
            linContent.addView(editText);
            Button butContinue = new Button(MenuActivity.menuActivity);
            butContinue.setLayoutParams(new LinearLayout.LayoutParams(
                    250,
                    100));
            butContinue.setHint("Continue");
            butContinue.setHintTextColor(Color.WHITE);
            butContinue.setBackgroundResource(R.drawable.button);
            linContent.addView(butContinue);
            butContinue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(editText.getText().length() < 4){
                            Pool.playMenuSound(5, 1);
                            Toast.makeText(MenuActivity.menuActivity, R.string.warning1,Toast.LENGTH_SHORT).show();
                            return;
                        }else{
                            MenuActivity.menuActivity.newGameContinue(true);
                            Pool.playMenuSound(1, 1);
                        }
                    }
                }
            );

        }
        conteiner.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup conteiner, int position, Object object){
        conteiner.removeView((RelativeLayout)object);
    }
}
