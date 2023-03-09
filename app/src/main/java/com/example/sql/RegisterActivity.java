package com.example.sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.sql.Network.DataBaseHelper;
import com.example.sql.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;

    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataBaseHelper = new DataBaseHelper(this);


        binding.buttonReg.setOnClickListener(v -> {
            String email = binding.emailTiet.getText().toString();
            String password = binding.passwordTiet.getText().toString();
            String confirmPassword = binding.repeatPasswordTiet.getText().toString();

            if (email.equals("") || password.equals("") || confirmPassword.equals("")) {
                Toast.makeText(this, "Все поля должны быть заполнены", Toast.LENGTH_SHORT).show();
            } else {
                if (password.equals(confirmPassword)) {
                    Boolean checkUserEmail = dataBaseHelper.checkEmail(email);
                    if (checkUserEmail == false) {
                        Boolean insert = dataBaseHelper.insertData(email, password);
                        if (insert == true) {
                            Toast.makeText(this, "Регистрация прошла успешно!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(this, "Регистрация произошла безуспешно", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else {
                        Toast.makeText(this, "Пользователь уже существует, пожалуйста, выполните вход", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(this, "Неверный пароль", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.footerTextViewLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, Login.class));
        });
    }
}