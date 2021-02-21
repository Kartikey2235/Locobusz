package com.example.locobusz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class SplashScreen extends AppCompatActivity {
    DatabaseHandler databaseHandler;

    FirebaseDatabase mDatabase;
    DatabaseReference mDbRef;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imageView=findViewById(R.id.image);

        RotateAnimation rotate = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(5000);
        rotate.setInterpolator(new LinearInterpolator());

        imageView.startAnimation(rotate);

        databaseHandler=new DatabaseHandler(this);
        mDatabase = FirebaseDatabase.getInstance();
        mDbRef = mDatabase.getReference("University/Student");

            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
                        if(Integer.parseInt(Objects.requireNonNull(ds.child("id").getValue()).toString())>=databaseHandler.getCount()){
                        Item item=ds.getValue(Item.class);
                            assert item != null;
                            databaseHandler.addDetails(item);
                    }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            };
            mDbRef.addListenerForSingleValueEvent(eventListener);

        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(5200);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        thread.start();

    }
}