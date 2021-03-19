package com.example.databaseapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText etUsername, etPassword;
    TextView tvLoad;
    DatabaseAdapter databaseAdapter = new DatabaseAdapter(this);
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        tvLoad = (TextView) findViewById(R.id.tvLoad);
    }

    public void saveData(View view) {
        String userName = etUsername.getText().toString();
        String userPassword = etPassword.getText().toString();
        etUsername.setText("");
        etPassword.setText("");
        if (userName.isEmpty()) {
            etUsername.setError("Field cannot be empty");
        } else if (userPassword.isEmpty()) {
            etPassword.setError("Field cannot be empty");
        } else {
            id = databaseAdapter.insertData(userName, userPassword);
            if (id < 0) {
                Toast toast = Toast.makeText(getApplicationContext(), "Save Unsuccesfull!", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

    }

    public void loadData(View view) {
        String data = databaseAdapter.getData();
        tvLoad.setText(data);
    }
}