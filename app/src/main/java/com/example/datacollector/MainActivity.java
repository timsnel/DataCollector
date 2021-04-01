package com.example.datacollector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView headerMain;
    TextView txtQMealSize;
    TextView txtLoggedMealValue;
    Button btnSmallMeal;
    Button btnNormalMeal;
    Button btnLargeMeal;
    FloatingActionButton addSomething;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mLoggedMealValueRef = mRootRef.child("LoggedMealValue");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        headerMain = (TextView)findViewById(R.id.headerMain);
        txtQMealSize = (TextView)findViewById(R.id.txtQMealSize);
        txtLoggedMealValue = (TextView)findViewById(R.id.txtLoggedMealValue);
        btnSmallMeal = (Button)findViewById(R.id.btnSmallMeal);
        btnNormalMeal= (Button)findViewById(R.id.btnNormalMeal);
        btnLargeMeal = (Button)findViewById(R.id.btnLargeMeal);
        addSomething = findViewById(R.id.addSomething);


    }

    @Override
    protected void onStart() {
        super.onStart();

        mLoggedMealValueRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String text = snapshot.getValue(String.class);
                txtLoggedMealValue.setText(text);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

         btnSmallMeal.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 mLoggedMealValueRef.setValue("Small");
             }
         });

        btnNormalMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoggedMealValueRef.setValue("Normal");
            }
        });

        btnLargeMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoggedMealValueRef.setValue("Large");
            }
        });
    }
}