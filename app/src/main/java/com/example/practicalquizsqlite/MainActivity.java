package com.example.practicalquizsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText et1, et2;
    Button btnInsert, btnRetrieve;
    ListView lv;
    ArrayList<School> al;
    ArrayAdapter<School> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = findViewById(R.id.editText1);
        et2 = findViewById(R.id.editText2);
        btnInsert = findViewById(R.id.btnInsert);
        btnRetrieve = findViewById(R.id.btnRetrieve);
        lv = findViewById(R.id.lv);
        al = new ArrayList<>();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToDo
                //String dataStudents = et1.getText().toString();
                //String dataSchoolName = et2.getText().toString();
                DBHelper db = new DBHelper(MainActivity.this);
                db.insertSchool(et1.getText().toString(),et2.getText().toString());
                //db.insertSchool(dataStudents, dataSchoolName);
                //et1.getText().clear();
                //et2.getText().clear();

                //al.clear();
                //al.addAll(dbh.getSchools());
                //aa.notifyDataSetChanged();
                db.close();
                Toast.makeText(MainActivity.this, "Insert successful", Toast.LENGTH_LONG).show();
            }
        });

        btnRetrieve.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //ToDo
                DBHelper db = new DBHelper(MainActivity.this);
                //al.clear();
                al=db.getSchools();
                db.close();
                //aa.notifyDataSetChanged();
                aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_expandable_list_item_1, al);
                lv.setAdapter(aa);
            }
        });
    }

}