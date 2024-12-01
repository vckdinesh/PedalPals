package com.example.pedalpals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

public class Signup extends AppCompatActivity {

    Database db;

    EditText first_name, last_name, email, username, password, re_password, mobile_number;
    TextView incorrect;
    Button signUp, login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        db = new Database(this);
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        mobile_number = findViewById(R.id.mobile_number);
        password = findViewById(R.id.password);
        re_password = findViewById(R.id.re_password);
        signUp = findViewById(R.id.signup_button);
        login = findViewById(R.id.goto_login_button);
        incorrect = findViewById(R.id.incorrect_details);
        AddData();
    }

    private void AddData() {
        signUp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (first_name.getText().toString().trim().equals("") || username.getText().toString().trim().equals("") ||
                                email.getText().toString().trim().equals("") || mobile_number.getText().toString().trim().equals("") ||
                                password.getText().toString().equals("") || re_password.getText().toString().equals("")) {
                            incorrect.setText("Fields cannot be left blank.");
                            return;
                        }

                        Cursor res = db.getData_User_username(username.getText().toString().trim());
                        if (res.getCount() != 0) {
                            incorrect.setText("Username is already in use.");
                            return;
                        }
                        String emailInput = email.getText().toString().trim();
                        if (!emailInput.endsWith("@gmail.com")) {
                            incorrect.setText("Email must end with @gmail.com.");
                            return;
                        }
                        res = db.getData_User_email_id(email.getText().toString().trim());
                        if (res.getCount() != 0) {
                            incorrect.setText("Email-ID is already in use.");
                            return;
                        }

                        res = db.getData_User_mobile_number(mobile_number.getText().toString().trim());
                        if (res.getCount() != 0) {
                            incorrect.setText("Mobile Number is already in use.");
                            return;
                        }
                        String mobile = mobile_number.getText().toString().trim();
                        if (mobile.length() != 10 || !mobile.matches("\\d+")) {
                            incorrect.setText("Mobile Number must be exactly 10 digits.");
                            return;
                        }
                        String pass = password.getText().toString();
                        if (pass.length() < 8 || !pass.matches(".*[a-zA-Z].*") || !pass.matches(".*\\d.*")) {
                            incorrect.setText("Password must be at least 8 characters long and contain both letters and numbers.");
                            return;
                        }
                        if (pass.equals(re_password.getText().toString())) {
                            String defaultRoom = "102";
                            String defaultHall = "Shradda";
                            boolean isInserted = db.insertData_User(
                                    first_name.getText().toString().trim(),
                                    last_name.getText().toString().trim(),
                                    email.getText().toString().trim(),
                                    defaultHall,
                                    defaultRoom,
                                    username.getText().toString().trim(),
                                    pass,
                                    mobile
                            );

                            if (isInserted) {
                                incorrect.setText("");
                                Toast.makeText(Signup.this, "User Registered", Toast.LENGTH_SHORT).show();
                                login.setVisibility(View.VISIBLE);
                                first_name.getText().clear();
                                last_name.getText().clear();
                                email.getText().clear();
                                username.getText().clear();
                                password.getText().clear();
                                re_password.getText().clear();
                                mobile_number.getText().clear();
                            } else {
                                Toast.makeText(Signup.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Signup.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                            password.getText().clear();
                            re_password.getText().clear();
                        }
                    }
                }
        );
    }

}
