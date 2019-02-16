package com.jackie.pokedex;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends BaseAdapter {

    private int[] _typeImgs;
    private String[] _typeNames;
    private Context _context;
    private List<Integer> _selectedPositions = new ArrayList<>();


    public GridAdapter(Context context, int[] typeImgs, String[] typeNames) {
        _typeImgs = typeImgs;
        _typeNames = typeNames;
        _context = context;

    }

    public List<Integer> getSelectedPositions() {
        return _selectedPositions;
    }

    public void removePos(int position) {
        _selectedPositions.remove(position);
    }

    public void addPos(int position) {
        _selectedPositions.add(position);
    }

    @Override
    public int getCount() {
        return _typeNames.length;
    }

    @Override
    public Object getItem(int position) {
        return _typeNames[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomGridView customView = (convertView == null) ?
                new CustomGridView(_context) : (CustomGridView) convertView;
        customView.display(_typeImgs[position], _selectedPositions.contains(position));
        customView.display(_typeNames[position], _selectedPositions.contains(position));
        return customView;

    }


}
