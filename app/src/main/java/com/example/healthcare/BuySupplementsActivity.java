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

public class BuySupplementsActivity extends AppCompatActivity {

    private String[][] supplements =
            {

                    {"Whey Gold Standard - Optimum Nutrition", "", "", "",
                            "50"},
                    {"Protein Whey Isolate - ProSupps","","","","305"},
                    {"L-Carnitine","","","","305"},
                    {"Mutant Mass - PVL","","","","539"},
                    {"Animal Pak - Universal Nutrition", "","","", "30"},
                    {"Creatine Powder - Optimum Nutrition", "","","", "50"},
                    {"Creatine monohydrate powder - Mammut Nutrition","","","","46"},
                    {"Creavita - Activlab","","","","40"},
            };
    private String[] supplement_details = {
            "Whey protein Gold Standard 100% Whey Protein \n" +
                    "contains a functional combination of whey isolate, concentrate and hydrolyzate.\n" +
                    "It has an extremely useful amino acid spectrum,\n" +
                    "good digestibility and a perfectly balanced taste.",
            "Whey Isolate is a unique nutritional supplement of whey protein isolate\n" +
            "containing 25 g of proteins  in one dose. \n" +
                    "This makes it popular among athletes who seek to maintain and increase their muscle mass .\n" +
                    "The product is sugar-free , but has a wonderful taste. \n" +
            "It also contains selected minerals and the fat burner l-carnitine.",
                    "L-carnitine ranks among the most popular fat burners. \n" +
                    " Its main job is to transport fats (fatty acids) to the cellular powerhouses called mitochondria,\n" +
            "where they are then burned to generate energy.\n" +
            "This fat burner works best in combination with physical activity and a properly composed diet.",
                    "Mutant Mass is an exceptional gainer for everyone who wants to gain quality muscle mass.\n" +
            " It contains 10 different protein ingredients and an excellent spectrum of amino acids. \n" +
                            "Each serving offers 26 g of protein and 85 g of carbohydrates , which is everything your muscles need for immediate growth.",
            "Animal Pak is a complex source of nutrients in the form of packets of tablets that contain the required dose of nutrients. \n" +
                    " Contains up to 60 ingredients , including vitamins, minerals, plant extracts, amino acids and enzymes.\n" +
            "They are suitable not only for athletes, but also for all people who want to strengthen their immunity, muscle function and metabolism, as well as reduce fatigue.",
            "Creatine Powder is 100% pure Creapure creatine monohydrate micronized into small particles, \n" +
                    "which ensures perfect dissolution in any drink. \n" +
                    "It has no taste or smell, but is crystalline and pure.\n" +
                    "It has a positive effect on overall sports achievements, supports the growth of muscle mass, reduces the feeling of fatigue, increases the energy level and endurance of the body.",
            "Creatine monohydrate is particularly popular with athletes who want to get the most out of their workouts. \n" +
                    "The product helps to improve physical performance during intense training. \n" +
                    "One dose of it contains an effective amount of 5.1 g of creatine, plus 129 mg of magnesium. \n" +
                    "In turn, magnesium contributes to the reduction of fatigue and exhaustion.\n" +
                    "In turn, magnesium contributes to the reduction of fatigue and exhaustion.",
            "Creavita is a supplement in the form of creatine monohydrate for increasing physical performance\n" +
                    "during short consecutive intervals of high-intensity training. \n" +
                    "It is also enriched with B vitamins and taurine, which is a common ingredient in energy drinks.\n" +
                    "This effective formula will be appreciated by strength athletes, fans of crossfit, HIIT training and other dynamic sports."
    };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack,btnGoToCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_supplements);

        lst = findViewById(R.id.lstSupInfo);
        btnBack = findViewById(R.id.buttonBackBS);
        btnGoToCart = findViewById(R.id.buttonGoToCartBS);

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuySupplementsActivity.this, CartBuySupplementActivity.class));
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuySupplementsActivity.this, HomeActivity.class));
            }
        });
        list = new ArrayList();
        for(int i = 0; i < supplements.length;i++){
            item = new HashMap<String,String>();
            item.put("line1", supplements[i][0]);
            item.put("line2", supplements[i][1]);
            item.put("line3", supplements[i][2]);
            item.put("line4", supplements[i][3]);
            item.put("line5", "Total cost:"+supplements[i][4]+"/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[] {"line1","line2","line3","line4","line5"},
                new int[] {R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);



        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(BuySupplementsActivity.this, BuySupplementsDetailsActivity.class);
                it.putExtra("text1",supplements[i][0]);
                it.putExtra("text2",supplement_details[i]);
                it.putExtra("text3",supplements[i][4]);
                startActivity(it);
            }
        });

    }

}