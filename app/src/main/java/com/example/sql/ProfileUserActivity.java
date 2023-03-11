package com.example.sql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sql.Network.DataBaseHelper;

public class ProfileUserActivity extends AppCompatActivity {

    TextView login, password;

    Toolbar toolbar;

    DataBaseHelper db = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        toolbar = findViewById(R.id.toolBar_profile);
        setSupportActionBar(toolbar);

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);





        setData();



    }

    private void setData() {
        Cursor cursor = db.readAllData();

        String email = Singleton.getInstance().getEmail();
        db.insertUser(email);

        while(cursor.moveToNext()) {
            login.setText(cursor.getString(0));
            password.setText(cursor.getString(1));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.arrowBack:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}