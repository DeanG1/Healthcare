package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class FitnessArticlesActivity extends AppCompatActivity {


    private String[][] fitness_details = {
    {"Walking Daily","","","","Click More Details"},
        {"The ideal stretching routine","","","","Click More Details"},
            {"The best exercises for your bones","","","", "Click More Details"},
                    {"A 7-Minute Core Workout for Absolute Beginners","","","","Click More Details"},
                            {"7 Potential Health Benefits of Running","","","","Click More Details"}
    };

    private int[] images = {
            R.drawable.walking,
            R.drawable.stretching,
            R.drawable.bones,
            R.drawable.coreworkout,
            R.drawable.running
    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnBack;
    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_articles);
        lst = findViewById(R.id.listViewArticle);
        btnBack = findViewById(R.id.buttonBackArticle);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FitnessArticlesActivity.this,HomeActivity.class));
            }
        });
        list = new ArrayList();
        for(int i = 0; i < fitness_details.length;i++){
            item = new HashMap<String,String>();
            item.put("line1", fitness_details[i][0]);
            item.put("line2", fitness_details[i][1]);
            item.put("line3", fitness_details[i][2]);
            item.put("line4", fitness_details[i][3]);
            item.put("line5", fitness_details[i][4]);
            list.add( item );
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[] {"line1","line2","line3","line4","line5"},
                new int[] {R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e,});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(FitnessArticlesActivity.this, FitnessArticlesDetailsActivity.class);
                it.putExtra("text1",fitness_details[i][0]);
                it.putExtra("text2",images[i]);
                startActivity(it);

            }
        });
        finish();
    }
}