package com.canytech.todolist_canylist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditTaskDesk extends AppCompatActivity {

    EditText titleDoes, descDoes, dateDoes;
    Button btnSaveUpdate, btnDelete;
    DatabaseReference reference;
    String keyKeyDoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task_desk);

        titleDoes = findViewById(R.id.title_does);
        descDoes = findViewById(R.id.desc_does);
        dateDoes = findViewById(R.id.date_does);

        btnSaveUpdate = findViewById(R.id.btn_save_update);
        btnDelete = findViewById(R.id.btn_delete);

        //get value from previous page
        titleDoes.setText(getIntent().getStringExtra("titleDoes"));
        descDoes.setText(getIntent().getStringExtra("descDoes"));
        dateDoes.setText(getIntent().getStringExtra("dateDoes"));

        keyKeyDoes = getIntent().getStringExtra("keyDoes");

        reference = FirebaseDatabase.getInstance().getReference().child("BoxDoes")
                .child("Does" + keyKeyDoes);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Intent intent = new Intent(EditTaskDesk.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        //make an event for button
        btnSaveUpdate.setOnClickListener(v -> {
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    reference.child("titleDoes").setValue(titleDoes.getText().toString());
                    reference.child("descDoes").setValue(descDoes.getText().toString());
                    reference.child("dateDoes").setValue(dateDoes.getText().toString());
                    reference.child("keyDoes").setValue(keyKeyDoes);

                    Intent intent = new Intent(EditTaskDesk.this, MainActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {}

            });
        });
    }
}