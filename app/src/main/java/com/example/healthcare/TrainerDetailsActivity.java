package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class TrainerDetailsActivity extends AppCompatActivity {
    private String[][] trainer_details1 =
            {
                    {"Trainer Name : Petar Vasilev", " Address: Plovdiv", "Exp : 5 years", "Mobile No:085945344", "149.90"},
                    {"Trainer Name : Gergi Atanasov", " Address: Sofia", "Exp : 7 years", "Mobile No:086326453", "220"},
                    {"Trainer Name : Stefan Karadjov", " Address: Plovdiv", "Exp : 4 years", "Mobile No:08734572", "90"},
            };
    private String[][] trainer_details2 =
            {
                    {"Trainer Name : Ivan Petrov", " Address: Burgas", "Exp : 6 years", "Mobile No:088123456", "120"},
                    {"Trainer Name : Elena Nikolova", " Address: Varna", "Exp : 8 years", "Mobile No:089654321", "212"},
                    {"Trainer Name : Maria Dimitrova", " Address: Ruse", "Exp : 5 years", "Mobile No:087456789", "179"},
            };
    private String[][] trainer_details3 =
            {
                    {"Trainer Name : Hristo Georgiev", " Address: Stara Zagora", "Exp : 10 years", "Mobile No:086789012", "199"},
                    {"Trainer Name : Plamen Ivanov", " Address: Pleven", "Exp : 3 years", "Mobile No:085678943", "144"},
                    {"Trainer Name : Desislava Popova", " Address: Sofia", "Exp : 12 years", "Mobile No:084567890", "150"},
            };
    private String[][] trainer_details4 =
            {
                    {"Trainer Name : Georgi Petkov", " Address: Varna", "Exp : 9 years", "Mobile No:089876543", "224"},
                    {"Trainer Name : Kalina Stoyanova", " Address: Plovdiv", "Exp : 2 years", "Mobile No:088234567", "110"},
                    {"Trainer Name : Viktor Dimitrov", " Address: Sofia", "Exp : 7 years", "Mobile No:087123654", "190"},
            };
    private String[][] trainer_details5 =
            {
                    {"Trainer Name : Todor Stanchev", " Address: Burgas", "Exp : 6 years", "Mobile No:085456789", "750"},
                    {"Trainer Name : Snezhana Petrova", " Address: Ruse", "Exp : 8 years", "Mobile No:086789123", "820"},
                    {"Trainer Name : Nikolay Ivanov", " Address: Plovdiv", "Exp : 5 years", "Mobile No:089456789", "700"},
            };
    TextView tv;
    Button btn;
    String[][] trainer_details = {};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_details);

        //Map textViewDDTitle
        tv = findViewById(R.id.textViewTDTitle);
        btn = findViewById(R.id.buttonBack);
        //Create object of Intent and then get the value of title
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);
        if(title.compareTo("Fitness Trainer")==0)
            trainer_details = trainer_details1;
        else
        if(title.compareTo("Lifestyle Coach")==0)
            trainer_details = trainer_details2;
        else
        if(title.compareTo("Sports Coach")==0)
            trainer_details = trainer_details3;
        else
        if(title.compareTo("Personal trainer")==0)
            trainer_details = trainer_details4;
        else
            trainer_details = trainer_details5;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TrainerDetailsActivity.this, FindTrainer.class));
            }
        });
        list = new ArrayList();
        for(int i=0; i<trainer_details.length;i++){
            item = new HashMap<String,String>();
            item.put("line1", trainer_details[i][0]);
            item.put("line2", trainer_details[i][1]);
            item.put("line3", trainer_details[i][2]);
            item.put("line4", trainer_details[i][3]);
            item.put("line5", "Cons Fees:"+trainer_details[i][4]+"/-");
            list.add( item );
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lst = findViewById(R.id.listViewTD);
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                Intent it = new Intent(TrainerDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",trainer_details[i][0]);
                it.putExtra("text3",trainer_details[i][1]);
                it.putExtra("text4",trainer_details[i][3]);
                it.putExtra("text5",trainer_details[i][4]);
                startActivity(it);
            }
        });
    }
}