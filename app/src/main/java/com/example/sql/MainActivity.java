package com.example.sql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sql.Adapters.CustomAdapter;
import com.example.sql.Network.DataBaseHelper;
import com.example.sql.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    Toolbar toolbar;

    DataBaseHelper myDB;
    ArrayList<String> user_email = new ArrayList<>();
    ArrayList<String> user_password = new ArrayList<>();
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        toolbar = binding.toolBar;
        setSupportActionBar(toolbar);
        setContentView(binding.getRoot());

        toolbar.setTitle("Главная");


        myDB= new DataBaseHelper(this);

        storeDataArrays();
        binding.recyclerViewUsers.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        customAdapter = new CustomAdapter(MainActivity.this, user_email, user_password);
        binding.recyclerViewUsers.setAdapter(customAdapter);

    }

    private void storeDataArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Данных не найдено", Toast.LENGTH_SHORT).show();
        } else {
            while(cursor.moveToNext()) {
                user_email.add(cursor.getString(0));
                user_password.add(cursor.getString(1));
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tool_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.profileUser:
                startActivity(new Intent(this, ProfileUserActivity.class));
                break;
            case R.id.insertUser:
                startActivity(new Intent(this, InsertUsersActivity.class));
                break;
            case R.id.deleteUser:
                myDB.deleteUsers();
                user_email.clear();
                user_password.clear();
                customAdapter = new CustomAdapter(MainActivity.this, user_email, user_password);
                binding.recyclerViewUsers.setAdapter(customAdapter);

        }
        return super.onOptionsItemSelected(item);
    }
}