package com.jackie.pokedex;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        BarChart barChart = (BarChart) findViewById(R.id.pstats);
        TextView pname, pnumber, pspecies, pflavor, ptype;
        ImageView pokepicture;

        pname = findViewById(R.id.pname);
        pnumber = findViewById(R.id.pnumber);
        pspecies = findViewById(R.id.pspecies);
        pflavor = findViewById(R.id.pflavor);
        pokepicture = findViewById(R.id.pokepicture);
        ptype = findViewById(R.id.ptype);


        ArrayList<BarEntry> entries = new ArrayList<>(); //TODO fill in stats
        entries.add(new BarEntry(0f, 39f));
        entries.add(new BarEntry(1f, 52f));
        entries.add(new BarEntry(2f, 43f));
        entries.add(new BarEntry(3f, 60f));
        entries.add(new BarEntry(4f, 50f));
        entries.add(new BarEntry(5f, 65f));

        final ArrayList<String> theTypes = new ArrayList<>();
        theTypes.add("HP");
        theTypes.add("ATK");
        theTypes.add("DEF");
        theTypes.add("SP.ATK");
        theTypes.add("SP.DEF");
        theTypes.add("SPD");
        theTypes.add("TOT");


        XAxis bottom = barChart.getXAxis();
        bottom.setGranularity(1f);
        bottom.setCenterAxisLabels(true);
        bottom.setLabelRotationAngle(-90);
        bottom.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if (value >= 0) {
                    if (value <= theTypes.size() - 1) {
                        return theTypes.get((int) value);
                    }
                    return "";
                }
                return  "";
            }
        });

        YAxis left = barChart.getAxisLeft();
        left.setAxisMaximum(255);
        left.setDrawLabels(true); //  axis labels
        left.setDrawAxisLine(true); // axis line
        left.setDrawGridLines(false); // no grid lines
        left.setDrawZeroLine(true); // draw a zero line
        barChart.getAxisRight().setEnabled(false); // no right axis

        BarDataSet set = new BarDataSet(entries, "BarDataSet");

        BarData data = new BarData(set);
        data.setBarWidth(0.9f); // set custom bar width
        barChart.setData(data);
        barChart.setFitBars(true); // make the x-axis fit exactly all bars
        barChart.invalidate(); // refresh


        Button button = (Button) findViewById(R.id.button_search_web);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String q = "https://bulbapedia.bulbagarden.net/wiki/" + "charmander"; //TODO name of pokemon
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH );
                intent.putExtra(SearchManager.QUERY, q);
                startActivity(intent);


            }
        });
    }




}
