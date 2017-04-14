package com.darkfig.plasmalivewallpaper;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * Created by Miguel Martrinez on 3/12/2017.
 */

public class Gui {
    private static int idCount = 0;
    private LinearLayout fullLayout;
    private DisplayMetrics metrics;
    private Context context;
    private Modifier[] modifiers;

    public Gui(Context c, DisplayMetrics m) {
        this.context = c;
        this.metrics =m;
        fullLayout = new LinearLayout(c);
        fullLayout.setOrientation(LinearLayout.VERTICAL);
        fullLayout.setBackgroundColor(Color.TRANSPARENT);
    }

    public void loadModifiers(Modifier...modifiers){
        this.modifiers= modifiers;
    }


    public void generate(int elements){
        for(int i = 0; i < elements; i++) {
            GuiElement element = new GuiElement(context,metrics,8,i);
            element.setModifier(modifiers[i]);
            fullLayout.addView(element.generateElement(elements));
        }
    }

    public static int generateId(){
        idCount++;
        return idCount;
    }


    public ViewGroup getLayout(){
        return fullLayout;
    }
}
