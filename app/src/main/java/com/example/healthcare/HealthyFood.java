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

public class HealthyFood extends AppCompatActivity {

    private String[][] meals = {
            {"Meal 1 : Kung Pao Beef", "", "", "", "20.54"},
            {"Meal 2 : Chipotle Chicken Al Pastor", "", "", "", "23.44"},
            {"Meal 3 : Creamy Tuna Pasta Salad", "", "", "", "29.99"},
            {"Meal 4 : Grilled Cilantro Lime Chicken", "", "", "", "26.50"},
            {"Meal 5 : Shrimp and Grits", "", "", "", "33.70"},
            {"Meal 6 : Lemon Ricotta Pasta", "", "", "", "19.99"},

    };
    private String[] meal_details = {
            "1.Thinly slice the flank steak\n" +
                    "2.Garlic powder, ginger powder, salt and pepper \n" +
                    "3.Red bell pepper\n" +
                    "4.Unsalted roasted peanuts\n" +
                    "5.A blend of ketchup, franks hot sauce, apple cider vinegar, coconut aminos, and toasted sesame oil",
                    "1.Chipotle peppers in adobo sauce\n" +
                    "2.Olive oil\n" +
                            "3.Kosher salt and pepper\n" +
                    "4.ground coriander, cumin, garlic powder,\n" +
                            "5.boneless, skinless chicken thighs",
                    "1.Pasta\n" +
                     "2.Celery Stalks: fresh for the best crunch\n" +
                            "3.Frozen Green Peas, thawed\n" +
                            "4.Tuna in Olive Oil, 1 jar, drained and flaked\n" +
                            "5.Red Onion, thinly sliced, to garnish",
            "1.Skinless Chicken Breasts: Ideally organic, free-range\n" +
            "2.Fresh cilantro: using fresh is key\n" +
                    "3.Lime: you will need to zest it\n" +
                    "4.lime juice, about 2 limes\n" +
                    "5.Garlic cloves, try to use fresh. Garlic powder won’t be the same\n" +
                    "6.Ground coriander\n" +
                    "7.Salt and black pepper",
                    "1.Milk\n" +
                    "2.Vegetable stock: For cooking the grits and ensuring lots of delicious flavor\n" +
                    "3.Grits\n" +
                    "4.Kosher salt & freshly ground black pepper\n" +
                    "5.Olive oil\n" +
                    "6.Garlic\n" +
                    "7.Lemon\n" +
                    "8.Shrimp\n" +
                    "9.Fresh parsley",
                    "1.Pasta\n" +
                    "2.Olive oil: For cooking the garlic\n" +
                    "3.Garlic\n" +
                    "4.Milk\n" +
                    "5.Ricotta cheese\n" +
                    "6.Lemon\n" +
                    "7.Parmesan cheese\n" +
                    "8.Kosher salt and black pepper\n" +
                    "8.Basil"
    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnGoToCart,btnBack;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy_food);

        btnGoToCart = findViewById(R.id.buttonHFGoToCart);
        btnBack = findViewById(R.id.buttonHFBack);
        listView = findViewById(R.id.HFListView);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthyFood.this,HomeActivity.class));
            }
        });
        list = new ArrayList();
        for(int i=0; i<meals.length;i++){
            item = new HashMap<String,String>();
            item.put("line1", meals[i][0]);
            item.put("line2", meals[i][1]);
            item.put("line3", meals[i][2]);
            item.put("line4", meals[i][3]);
            item.put("line5", "Total cost: " + meals[i][4]+"/-");
            list.add( item );
        }

        sa = new SimpleAdapter(this,list,R.layout.multi_lines,
                new String[] {"line1","line2","line3","line4","line5"},
                new int[] {R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(HealthyFood.this, HealthyFoodDetailsActivity.class);
                it.putExtra("text1",meals[i][0]);
                it.putExtra("text2",meal_details[i]);
                it.putExtra("text3",meals[i][4]);
                startActivity(it);
            }
        });

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthyFood.this, CartFoodActivity.class));
            }
        });
    }
}