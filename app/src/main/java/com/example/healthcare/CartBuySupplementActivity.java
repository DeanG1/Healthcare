package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartBuySupplementActivity extends AppCompatActivity {
    HashMap<String,String> item;
    SimpleAdapter sa;
    ArrayList list;
    ListView lst;
    TextView tvTotal;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton,btnCheckout,btnBack;
    private String[][] supplements = {};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_buy_supplements);
        dateButton = findViewById(R.id.buttonDatePickerCartBS);
        btnCheckout = findViewById(R.id.buttonCheckoutCartBS);
        btnBack = findViewById(R.id.buttonBackCartBS);
        tvTotal = findViewById(R.id.textViewTotalPriceCartBS);
        lst = findViewById(R.id.listViewCartBS);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();

        Database db = new Database(getApplicationContext(),"healthcare", null,1);
        float totalAmount = 0;
        ArrayList dbData = db.getCartData(username,"supplement");

        supplements = new String[dbData.size()][];
        for(int i = 0; i<supplements.length;i++){
            supplements[i] = new String[5];
        }
        for(int i = 0;i<dbData.size();i++){
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            supplements[i][0] = strData[0];
            supplements[i][4] = "Cost: "+strData[1]+"/-";
            totalAmount = totalAmount + Float.parseFloat(strData[1]);
        }
        tvTotal.setText("Total cost: "+totalAmount);

        list = new ArrayList();
        for(int i=0;i<supplements.length;i++){
            item = new HashMap<String,String>();
            item.put("line1", supplements[i][0]);
            item.put("line2", supplements[i][1]);
            item.put("line3", supplements[i][2]);
            item.put("line4", supplements[i][3]);
            item.put("line5", supplements[i][4]);
            list.add( item );
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[] {"line1","line2","line3","line4","line5"},
                new int[] {R.id.line_a, R.id.line_b, R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartBuySupplementActivity.this, BuySupplementsActivity.class));
            }
        });
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(CartBuySupplementActivity.this, BuySupplementsBookActivity.class);
                it.putExtra("price", tvTotal.getText());
                it.putExtra("date",dateButton.getText());
                startActivity(it);
            }
        });
        //datepicker popup
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

    }
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1=i1+1;
                dateButton.setText(i2+"/"+i1+"/"+i);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }
}