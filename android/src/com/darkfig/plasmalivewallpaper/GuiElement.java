package com.darkfig.plasmalivewallpaper;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.darkfig.plasmalivewallpaper.android.R;

/**
 * Created by Miguel Martrinez on 3/16/2017.
 */

public class GuiElement {
    private int index;
    Context context;
    DisplayMetrics metrics;
    private TextView title;
    private TextView numberView;
    private SeekBar seekBar;
    private Modifier modifier;
    private int maxDivisions;
    public GuiElement(Context context, DisplayMetrics metrics,int maxDivisions, int index) {
        this.context = context;
        this.metrics = metrics;
        this.index = index;
        this.maxDivisions = maxDivisions;
    }

    public void setModifier(Modifier modifier){
        this.modifier = modifier;
    }



    public RelativeLayout generateElement(int elements){
        //Set max number of division to 9
        int divisions = (elements >= maxDivisions) ? maxDivisions : elements;
        RelativeLayout relativeLayout = new RelativeLayout(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,metrics.heightPixels/divisions);
        relativeLayout.setLayoutParams(params);
        title = createTitleView(params);
        relativeLayout.addView(title);
        seekBar = createSeekbar(params);
        relativeLayout.addView(seekBar);
        numberView = createNumberView(params,seekBar.getId());
        relativeLayout.addView(numberView);
        return  relativeLayout;
    }

    private TextView createTitleView(RelativeLayout.LayoutParams p){
        TextView textView = new TextView(context);
        textView.setText(modifier.getTitle());
        textView.setTypeface(Typeface.MONOSPACE);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(20f);
        textView.setBackgroundColor(Color.TRANSPARENT);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,p.height/3);
        textView.setLayoutParams(params);
        return textView;
    }

    private SeekBar createSeekbar(RelativeLayout.LayoutParams p){
        seekBar = new SeekBar(context);
        seekBar.setId(Gui.generateId());
        seekBar.setThumb(ContextCompat.getDrawable(context, R.drawable.thumb));
        seekBar.setProgressDrawable(ContextCompat.getDrawable(context, R.drawable.progress));
        seekBar.setMax(modifier.getMaxValue());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) (metrics.widthPixels*.85),p.height/3*2);
        //params.addRule(RelativeLayout.BELOW);
        //params.addRule(RelativeLayout.CENTER_VERTICAL);
        params.topMargin = p.height/3;
        seekBar.setLayoutParams(params);
        seekBar.setProgress(modifier.getValue());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                modifier.setValue(i);
                numberView.setText(String.valueOf(modifier.getValue()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Here we save the changes to our sharedpreferences
                SettingsManager.preferenceEditor.putFloat(modifier.getTitle(),(float)modifier.getValue());
                SettingsManager.preferenceEditor.apply();
            }
        });

        return seekBar;
    }

    private TextView createNumberView(RelativeLayout.LayoutParams p, int seekbarId){
        TextView textView = new TextView(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) (metrics.widthPixels*.15),p.height/3*2);
        params.addRule(RelativeLayout.RIGHT_OF,seekbarId);
        params.setMargins(0,p.height/3,0,0);
        textView.setTextColor(Color.BLACK);
        textView.setText(String.valueOf(modifier.getValue()));
        textView.setTextSize(28f);
        textView.setTypeface(Typeface.MONOSPACE);
        textView.setLayoutParams(params);
        textView.setBackgroundColor(Color.TRANSPARENT);
        return  textView;
    }
}
