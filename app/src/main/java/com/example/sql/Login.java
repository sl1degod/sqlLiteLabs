package com.example.sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.sql.Network.DataBaseHelper;
import com.example.sql.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {

    ActivityLoginBinding binding;
    DataBaseHelper dataBaseHelper;

    public static String email = "";
    public static String password = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataBaseHelper = new DataBaseHelper(this);

        binding.buttonLogin.setOnClickListener(v -> {
            email = binding.emailTiet.getText().toString();
            password = binding.passwordTiet.getText().toString();



            if (email.equals("") || password.equals("")) {
                Toast.makeText(this, "Все поля должны быть заполнены", Toast.LENGTH_SHORT).show();
            } else {
                Boolean checkCredentials = dataBaseHelper.checkEmailPassword(email, password);

                if (checkCredentials == true) {
                    Toast.makeText(this, "Успешно вошли", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    Singleton.getInstance().setEmail(email);
                } else {
                    Toast.makeText(this, "Введены неверные данные", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.footerTextViewRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }



}