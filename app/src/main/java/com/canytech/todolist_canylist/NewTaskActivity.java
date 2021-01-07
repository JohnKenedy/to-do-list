package com.canytech.todolist_canylist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class NewTaskActivity extends AppCompatActivity {

    Button btnSaveTask, btnCancel;
    DatabaseReference reference;
    Integer doesNum;
    EditText titleDoes;
    EditText descDoes;
    EditText dateDoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        titleDoes = findViewById(R.id.title_does);
        descDoes = findViewById(R.id.desc_does);
        dateDoes = findViewById(R.id.date_does);

        btnSaveTask = findViewById(R.id.btn_save_task);
        btnCancel = findViewById(R.id.btn_cancel_task);
        doesNum = new Random().nextInt();

        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // insert data to database
                reference = FirebaseDatabase.getInstance().getReference().child("BoxDoes")
                        .child("Does" + doesNum);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        snapshot.getRef().child("titleDoes").setValue(titleDoes.getText().toString());
                        snapshot.getRef().child("descDoes").setValue(descDoes.getText().toString());
                        snapshot.getRef().child("dateDoes").setValue(dateDoes.getText().toString());

                        Intent intent = new Intent( NewTaskActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
}