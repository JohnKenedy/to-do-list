package com.canytech.todolist_canylist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.canytech.todolist_canylist.adapter.DoesAdapter;
import com.canytech.todolist_canylist.database.MyDoes;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView rv_does;
    ArrayList<MyDoes> list;
    DoesAdapter doesAdapter;
    Button btnAddNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddNew = findViewById(R.id.btn_add_new);

        btnAddNew.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NewTaskActivity.class);
            startActivity(intent);
        });

        // working with data
        rv_does = findViewById(R.id.rv_does);
        rv_does.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<MyDoes>();

        // get data from firebase
        reference = FirebaseDatabase.getInstance().getReference().child("BoxDoes");
        doesAdapter = new DoesAdapter(MainActivity.this, list);
        rv_does.setAdapter(doesAdapter);

        //TODO Fix Bugs: when trying to edit without restarting the app, and when not editing all parameters
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // set code to retrive data and replace layout
                for(DataSnapshot dataSnapshot1 : snapshot.getChildren())
                {
                    MyDoes p = dataSnapshot1.getValue(MyDoes.class);
                    list.add(p);
                }
                doesAdapter =  new DoesAdapter(MainActivity.this, list);
                rv_does.setAdapter(doesAdapter);
                doesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // set code to show an error
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}