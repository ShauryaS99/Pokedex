package com.jackie.pokedex;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomGridView extends FrameLayout {

    private ImageView _icon;
    private TextView _name;

    public CustomGridView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.custom_grid_layout, this);
        _icon = (ImageView)getRootView().findViewById(R.id.gridImg);
        _name = (TextView)getRootView().findViewById(R.id.gridText);

    }

    public void display(String type, boolean isSelected) {
        _name.setText(type);
        display(isSelected);
    }

    public void display(int img, boolean isSelected) {
        _icon.setImageResource(img);
        display(isSelected);
    }


    public void display(boolean isSelected) {
        _icon.setBackgroundColor(isSelected? Color.parseColor("#EEEEEE") : Color.TRANSPARENT);
        _name.setBackgroundColor(isSelected? Color.parseColor("#EEEEEE") : Color.TRANSPARENT);
    }

    public TextView getTextView() {
        return _name;
    }
}
