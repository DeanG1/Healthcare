package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuySupplementsDetailsActivity extends AppCompatActivity {

    TextView tvPackageName,tvTotalCost;
    EditText edDetails;
    Button btnBack,btnAddToCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_supplements_details);

        tvPackageName = findViewById(R.id.textViewBMS);
        tvTotalCost = findViewById(R.id.textViewTotalCostBMS);
        edDetails = findViewById(R.id.editTextTextMultilineBMS);
        edDetails.setOnKeyListener(null);
        btnBack = findViewById(R.id.buttonBackBMS);
        btnAddToCart = findViewById(R.id.buttonAddToCartBMS);

        Intent intent = getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total cost : "+intent.getStringExtra("text3")+"/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuySupplementsDetailsActivity.this,HomeActivity.class));
            }
        });
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = tvPackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                if(db.checkCart(username,product)==1){
                    Toast.makeText(getApplicationContext(),"Product already added", Toast.LENGTH_SHORT).show();
                }
                else{
                    db.addCart(username,product,price,"supplement");
                    Toast.makeText(getApplicationContext(),"Record inserted to Cart",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuySupplementsDetailsActivity.this, BuySupplementsActivity.class));
                }
            }
        });
        finish();
    }
}