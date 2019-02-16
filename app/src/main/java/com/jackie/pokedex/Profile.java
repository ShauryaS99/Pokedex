package com.jackie.pokedex;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        TextView pname, pnumber, pspecies, pflavor, ptype;
        ImageView pokepicture;

        pname = findViewById(R.id.pname);
        pnumber = findViewById(R.id.pnumber);
        pspecies = findViewById(R.id.pspecies);
        pflavor = findViewById(R.id.pflavor);
        pokepicture = findViewById(R.id.pokepicture);
        ptype = findViewById(R.id.ptype);


        //*******

//        BarChart barChart = (BarChart) findViewById(R.id.chart);
//        ArrayList<BarEntry> entries = new ArrayList<>(); //TODO fill in stats
//        entries.add(new BarEntry(0f, 39f));
//        entries.add(new BarEntry(1f, 52f));
//        entries.add(new BarEntry(2f, 43f));
//        entries.add(new BarEntry(3f, 60f));
//        entries.add(new BarEntry(4f, 50f));
//        entries.add(new BarEntry(5f, 65f));
//
//        final ArrayList<String> theTypes = new ArrayList<>();
//        theTypes.add("HP");
//        theTypes.add("ATK");
//        theTypes.add("DEF");
//        theTypes.add("SP.ATK");
//        theTypes.add("SP.DEF");
//        theTypes.add("SPD");
//        theTypes.add("TOT");


        HorizontalBarChart chart = (HorizontalBarChart) findViewById(R.id.chart);

        BarDataSet set1;
        set1 = new BarDataSet(getDataSet(), "The year 2017");

        set1.setColors(Color.parseColor("#f75de1"), Color.parseColor("#5df7b5"), Color.parseColor("#00b2b2"), Color.parseColor("#f6f75d"), Color.parseColor("#ffc469"),  Color.parseColor("#dd0000"));

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(dataSets);

        // hide Y-axis
        YAxis left = chart.getAxisLeft();
        left.setDrawLabels(false);
        left.setDrawGridLines(false);
        left.setAxisMaximum(255);
        left.setDrawLabels(false);
        left.setDrawGridLines(false);
        left.setLabelCount(0);

        YAxis right = chart.getAxisRight();
        right.setDrawLabels(false);
        right.setDrawGridLines(false);
        right.setDrawLabels(false);
        right.setDrawGridLines(false);


        // custom X-axis labels
        String[] values = new String[] { "SPD", "SP. DEF", "SP. ATK", "DEF", "ATK", "HP"};
        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter(values));
        xAxis.setTextSize(12f);
        xAxis.setDrawGridLines(false);


        chart.setData(data);
        data.setDrawValues(true);
        data.setValueTextSize(12f);


        // custom description
        Description description = new Description();
        description.setText("Stats");
        description.setTextSize(12f);
        chart.setDescription(description);

        // hide legend
        chart.getLegend().setEnabled(false);

        chart.animateY(1000);
        chart.invalidate();

        //********




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


    public class MyXAxisValueFormatter implements IAxisValueFormatter {

        private String[] mValues;

        public MyXAxisValueFormatter(String[] values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int) value - 1];
        }

    }

    private ArrayList<BarEntry> getDataSet() {

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();

        BarEntry v1e2 = new BarEntry(1, 65f);
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(2, 95f);
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(3, 58f);
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(4, 10f);
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(5, 29f);
        valueSet1.add(v1e6);
        BarEntry v1e7 = new BarEntry(6, 27f);
        valueSet1.add(v1e7);


        return valueSet1;
    }








}