package com.example.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class StudentActivity extends AppCompatActivity {
    private TextView textStudentId,textName,textSurname,textAddress,textAge,textYear;
    private String studentId,name,surname,address,age,year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        bindView();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            Log.i("student",bundle.getString("studentId") + " ");
            studentId = bundle.getString("studentId");
            name = bundle.getString("name");
            surname = bundle.getString("surname");
            address = bundle.getString("address");
            age = bundle.getString("age");
            year = bundle.getString("year");

            textStudentId.setText(studentId);
            textName.setText(name);
            textSurname.setText(surname);
            textAddress.setText(address);
            textAge.setText(age);
            textYear.setText(year);
        }
    }

    private void bindView() {
        textStudentId = (TextView) findViewById(R.id.text_student_id);
        textName = (TextView) findViewById(R.id.text_name);
        textAddress = (TextView) findViewById(R.id.text_address);
        textSurname = (TextView) findViewById(R.id.text_surname);
        textAge = (TextView) findViewById(R.id.text_age);
        textYear = (TextView) findViewById(R.id.text_year);
    }
}
