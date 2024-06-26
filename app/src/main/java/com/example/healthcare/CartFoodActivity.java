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
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartFoodActivity extends AppCompatActivity {
    HashMap<String,String> item;
    SimpleAdapter sa;
    ArrayList list;
    ListView lst;
    TextView tvTotal;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton,timeButton,btnCheckout,btnBack;
    private String[][] meals = {};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_menu);

        dateButton = findViewById(R.id.buttonDatePickerCart);
        timeButton = findViewById(R.id.buttonTimePickerCart);
        btnCheckout = findViewById(R.id.buttonCheckoutCart);
        btnBack = findViewById(R.id.buttonBackCart);
        tvTotal = findViewById(R.id.textViewTotalPriceCart);
        lst = findViewById(R.id.listViewCart);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();

        Database db = new Database(getApplicationContext(),"healthcare", null,1);
        float totalAmount = 0;
        ArrayList dbData = db.getCartData(username,"lab");

        meals = new String[dbData.size()][];
        for(int i = 0; i<meals.length;i++){
            meals[i] = new String[5];
        }
        for(int i = 0;i<dbData.size();i++){
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            meals[i][0] = strData[0];
            meals[i][4] = "Cost: "+strData[1]+"/-";
            totalAmount = totalAmount + Float.parseFloat(strData[1]);
        }
        tvTotal.setText("Total cost: "+totalAmount);

        list = new ArrayList();
        for(int i=0;i<meals.length;i++){
            item = new HashMap<String,String>();
            item.put("line1", meals[i][0]);
            item.put("line2", meals[i][1]);
            item.put("line3", meals[i][2]);
            item.put("line4", meals[i][3]);
            item.put("line5", meals[i][4]);
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
                startActivity(new Intent(CartFoodActivity.this, HealthyFood.class));
            }
        });
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(CartFoodActivity.this, HealthyFoodBookActivity.class);
                it.putExtra("price", tvTotal.getText());
                it.putExtra("date",dateButton.getText());
                it.putExtra("time",timeButton.getText());
                startActivity(it);
//                startActivity(new Intent(CartLabActivity.this,LabTestBookActivity.class));
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
        //timepicker popup
        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
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
    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeButton.setText(i+":"+i1);
            }
        };
        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int mins = cal.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);
    }
}