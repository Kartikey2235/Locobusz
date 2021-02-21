package com.example.locobusz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ShowAllDetail extends AppCompatActivity {

    TextView name,rollNo,dep,depcode,gender,dateofbirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_detail);

        name=findViewById(R.id.childNameAllDetail);
        rollNo=findViewById(R.id.childRollnoAllDEtail);
        dep=findViewById(R.id.childDepartmentAllDetail);
        depcode=findViewById(R.id.childDepartmentCodeAllDetail);
        gender=findViewById(R.id.childGenderAllDetail);
        dateofbirth=findViewById(R.id.childDateOfBirthAllDetail);

        name.setText(getIntent().getStringExtra("name"));
        rollNo.setText(String.valueOf(getIntent().getStringExtra("rollNo")));
        dep.setText(getIntent().getStringExtra("department"));
        depcode.setText(String.valueOf(getIntent().getStringExtra("departmentCode")));
        dateofbirth.setText(getIntent().getStringExtra("dateOfBirth"));
        gender.setText(getIntent().getStringExtra("gender"));
    }
}