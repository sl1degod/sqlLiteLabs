package com.example.sql;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sql.Network.DataBaseHelper;

public class ProfileUserActivity extends AppCompatActivity {

    TextView login, password;

    DataBaseHelper db = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

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
}