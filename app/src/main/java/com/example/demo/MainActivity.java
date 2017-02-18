package com.example.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo.model.Student;

public class MainActivity extends AppCompatActivity {
    private EditText edit_name, edit_student_id, edit_surname, edit_year, edit_address, edit_age;
    private Button btn_submit;
    private SearchView searchView;
    DatabaseHelper dbHelper = new DatabaseHelper(this);
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindView();
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateStudent();
            }
        });
    }

    private void bindView() {
        edit_student_id = (EditText) findViewById(R.id.edit_student_id);
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_surname = (EditText) findViewById(R.id.edit_surname);
        edit_year = (EditText) findViewById(R.id.edit_year);
        edit_address = (EditText) findViewById(R.id.edit_address);
        edit_age = (EditText) findViewById(R.id.edit_age);
        btn_submit = (Button) findViewById(R.id.btn_submit);
    }

    private void validateStudent (){
        String studentId = edit_student_id.getText().toString();
        String name = edit_name.getText().toString();
        String surname = edit_surname.getText().toString();
        String year = edit_year.getText().toString();
        String address = edit_address.getText().toString();
        String age = edit_age.getText().toString();
        if (!studentId.isEmpty() && !name.isEmpty() && !surname.isEmpty() && !year.isEmpty() && !address.isEmpty() && !age.isEmpty()){
            Log.i("studenID", studentId);

            dbHelper.insertUser(studentId,name,surname,year,address,age);
        }
        else {
            Toast.makeText(this,"กรุณาใส่ข้อมูลให้ครบ", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.search);

        searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                student = new Student();
                student = dbHelper.searchStudent(query);
                Log.i("search",student.getStudentId() + " " +
                                student.getName() + " " +
                                student.getSurname());

                // ไปintent ข้อมูลเองนะไม่ยากแล้วทำ search ให้แล้ว แคส่งข้อมูลไปแสดงอีกหน้านึง keyword intent
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }


}
