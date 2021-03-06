package com.example.locobusz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

// This activity is basically there if there is no data in firebase as well as sql lite it is doing the same work

public class MainActivity extends AppCompatActivity {
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private EditText itemName,itemRollNo,itemDepartment,itemDepartmentCode,itemDateOfBirth,itemGender;
    private Button saveButton;
    private ImageButton crossButton;
    private Calendar myCalendar;
    DatabaseHandler databaseHandler;

    FirebaseDatabase mDatabase;
    DatabaseReference mDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHandler=new DatabaseHandler(this);
        mDatabase = FirebaseDatabase.getInstance();
        mDbRef = mDatabase.getReference("University/Student");

        byPassActivity();

        List<Item> items= databaseHandler.getAllDetails();
        for(Item item:items){
            Log.d("kartik", "onCreate: "+item.getName());
        }
        FloatingActionButton floatingActionButton=findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPopupDialog();
            }
        });
    }

    private void byPassActivity() {
        if(databaseHandler.getCount()>0){
            startActivity(new Intent(MainActivity.this,Show.class));
        }
    }

    private void createPopupDialog() {
        builder=new AlertDialog.Builder(this);
        View view=getLayoutInflater().inflate(R.layout.popup,null);
        myCalendar = Calendar.getInstance();
        itemName=view.findViewById(R.id.childName);
        itemRollNo=view.findViewById(R.id.childRollNo);
        itemDepartment=view.findViewById(R.id.childDepartment);
        itemDepartmentCode=view.findViewById(R.id.childDepartmentCode);
        itemDateOfBirth=view.findViewById(R.id.childDateOfBirth);
        itemGender=view.findViewById(R.id.childGender);
        crossButton=view.findViewById(R.id.crossButton);
        saveButton=view.findViewById(R.id.saveButton);
        itemDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(view.getContext(),view );
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.popup_menu_department, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @SuppressLint("NonConstantResourceId")
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.action_CSE:
                                itemDepartment.setText(menuItem.getTitle());
                                break;
                            case R.id.ME:
                                itemDepartment.setText(menuItem.getTitle());
                                break;
                            case R.id.action_IT:
                                itemDepartment.setText(menuItem.getTitle());
                                break;
                            case R.id.EEE:
                                itemDepartment.setText(menuItem.getTitle());
                                break;
                            case R.id.ECE:
                                itemDepartment.setText(menuItem.getTitle());
                                break;
                            case R.id.action_BCOM:
                                itemDepartment.setText(menuItem.getTitle());
                                break;
                            case R.id.action_LLB:
                                itemDepartment.setText(menuItem.getTitle());
                                break;
                            case R.id.BBA:
                                itemDepartment.setText(menuItem.getTitle());
                                break;
                            case R.id.BCA:
                                itemDepartment.setText(menuItem.getTitle());
                                break;
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });

        itemGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popup = new PopupMenu(view.getContext(),view );
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @SuppressLint("NonConstantResourceId")
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.action_Male:
                                itemGender.setText(menuItem.getTitle());
                                break;
                            case R.id.action_Female:
                                itemGender.setText(menuItem.getTitle());
                                break;
                            case R.id.action_Others:
                                itemGender.setText(menuItem.getTitle());
                                break;
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        itemDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!itemName.getText().toString().isEmpty() &&
                        !itemRollNo.getText().toString().isEmpty() && !itemDepartment.getText().toString().isEmpty() &&
                        !itemDepartment.getText().toString().isEmpty()&& !itemDepartmentCode.getText().toString().isEmpty()
                        && !itemDateOfBirth.getText().toString().isEmpty()&& !itemGender.getText().toString().isEmpty()){
                    saveItem(view);
                }else{
                    Snackbar.make(view,"Fill all the details",
                            Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        crossButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        builder.setView(view);
        dialog=builder.create();
        dialog.show();
    }

    private void saveItem(View view) {
        Item item=new Item();

        String name=itemName.getText().toString().trim();
        String department=itemDepartment.getText().toString().trim();
        String dateOfBirth=itemDateOfBirth.getText().toString().trim();
        String gender=itemGender.getText().toString().trim();
        int departmentCode=Integer.parseInt(itemDepartmentCode.getText().toString().trim());
        int rollNo=Integer.parseInt(itemRollNo.getText().toString().trim());
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();

        item.setTimeStamp(ts);
        item.setRollNo(rollNo);
        item.setName(name);
        item.setDepartmentCode(departmentCode);
        item.setGender(gender);
        item.setDepartment(department);
        item.setDateOfBirth(dateOfBirth);
        item.setId(databaseHandler.getCount());

        //Setting firebase unique key for Hashmap list

        String userId = mDbRef.push().getKey();

        assert userId != null;
        mDbRef.child(userId).setValue(item);
        databaseHandler.addDetails(item);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                dialog.dismiss();
                Intent intent=new Intent(MainActivity.this,Show.class);
                startActivity(intent);
                finish();
            }
        },1200);
    }
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        itemDateOfBirth.setText(sdf.format(myCalendar.getTime()));
    }

}