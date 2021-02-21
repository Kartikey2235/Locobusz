package com.example.locobusz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

// Database Handler class for SQL Lite Database

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME,null,Util.DATABASE_VERSION);
    }

//  Method to create the table

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PERSON_DETAILS_TABLE="CREATE TABLE "+ Util.TABLE_NAME+"("
                + Util.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ Util.KEY_NAME+ " TEXT,"
                +Util.KEY_ROLLNO+" TEXT,"+Util.KEY_DEPARTMENT+" TEXT,"+Util.KEY_DEPARTMENTCODE+" TEXT,"+Util.KEY_DATEOFBIRTH+" TEXT,"
                +Util.KEY_GENDER+" TEXT,"+Util.KEY_TIMESTAMP+" TEXT"+")";
        db.execSQL(CREATE_PERSON_DETAILS_TABLE);
    }

//    Method to upgrade the version

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE= String.valueOf(R.string.drop_table);
        db.execSQL(DROP_TABLE, new String[]{Util.DATABASE_NAME});

        onCreate(db);
    }

//    Method to add the details

    public void addDetails(Item item){
        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Util.KEY_NAME,item.getName());
        values.put(Util.KEY_ROLLNO,item.getRollNo());
        values.put(Util.KEY_DEPARTMENT,item.getDepartment());
        values.put(Util.KEY_DEPARTMENTCODE,item.getDepartmentCode());
        values.put(Util.KEY_DATEOFBIRTH,item.getDateOfBirth());
        values.put(Util.KEY_GENDER,item.getGender());
        values.put(Util.KEY_TIMESTAMP,item.getTimeStamp());

        db.insert(Util.TABLE_NAME,null,values);
        db.close();
    }

//    Method to fetch the detail of an item

    public Item getDetails(int id){
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(Util.TABLE_NAME,
                new String[]{Util.KEY_ID,Util.KEY_NAME,Util.KEY_ROLLNO,Util.KEY_DEPARTMENT,Util.KEY_DEPARTMENTCODE,Util.KEY_DATEOFBIRTH,Util.KEY_GENDER,Util.KEY_TIMESTAMP},
                Util.KEY_ID +"=?",new String[]{String.valueOf(id)},
                null,null,null);

        if(cursor != null)
            cursor.moveToFirst();

        Item item=new Item();
        item.setId(Integer.parseInt(cursor.getString(0)));
        item.setName(cursor.getString(1));
        item.setRollNo(Integer.parseInt(cursor.getString(2)));
        item.setDepartment(cursor.getString(3));
        item.setDepartmentCode(Integer.parseInt(cursor.getString(4)));
        item.setDateOfBirth(cursor.getString(5));
        item.setGender(cursor.getString(6));
        item.setTimeStamp(cursor.getString(7));
        return item;
    }

//    Method to get all the details

    public List<Item> getAllDetails(){
        List<Item> personDetails=new ArrayList<>();

        SQLiteDatabase db=getReadableDatabase();

        String selectAll="SELECT * FROM "+Util.TABLE_NAME;
        Cursor cursor=db.rawQuery(selectAll,null);

        if(cursor.moveToFirst()){
            do{
                Item item =new Item();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setName(cursor.getString(1));
                item.setRollNo(Integer.parseInt(cursor.getString(2)));
                item.setDepartment(cursor.getString(3));
                item.setDepartmentCode(Integer.parseInt(cursor.getString(4)));
                item.setDateOfBirth(cursor.getString(5));
                item.setGender(cursor.getString(6));
                item.setTimeStamp(cursor.getString(7));
                personDetails.add(item);
            }while(cursor.moveToNext());
        }
        return personDetails;
    }

//    Method to update the detail

    public int updateDetails(Item item){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(Util.KEY_NAME,item.getName());
        values.put(Util.KEY_ROLLNO,item.getRollNo());
        values.put(Util.KEY_DEPARTMENT,item.getDepartment());
        values.put(Util.KEY_DEPARTMENTCODE,item.getDepartmentCode());
        values.put(Util.KEY_DATEOFBIRTH,item.getDateOfBirth());
        values.put(Util.KEY_GENDER,item.getGender());
        values.put(Util.KEY_TIMESTAMP,item.getTimeStamp());

        return db.update(Util.TABLE_NAME,values,Util.KEY_ID +"=?",
                new String[]{String.valueOf(item.getId())});
    }

//    Method to delete the details

    public void deleteDetails(Item item){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(Util.TABLE_NAME, Util.KEY_ID +"=?",
                new String[]{String.valueOf(item.getId())});
        db.close();
    }

//    Method to get the size of the database

    public int getCount(){
        String countQuery="SELECT*FROM "+Util.TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(countQuery,null);
        return cursor.getCount();
    }
}
