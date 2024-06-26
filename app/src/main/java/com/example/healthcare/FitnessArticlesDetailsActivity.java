package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FitnessArticlesDetailsActivity extends AppCompatActivity {
    TextView tv1;
    ImageView img;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_articles_details);

        btnBack = findViewById(R.id.buttonBackArticleDetails);
        tv1 = findViewById(R.id.textViewArticleDetails);
        img = findViewById(R.id.imageViewArticleDetails);

        Intent intent = getIntent();
        tv1.setText(intent.getStringExtra("text1"));

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            int resId = bundle.getInt("text2");
            img.setImageResource(resId);
        }
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FitnessArticlesDetailsActivity.this, FitnessArticlesActivity.class));
            }
        });
    }
}