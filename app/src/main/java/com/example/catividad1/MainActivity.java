package com.example.catividad1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txtUsername, txtPassword;
    Button btnIngresar, btnRegistrar;

    RequestQueue requestQueue;

    private static final String URL2 = "http://192.168.100.54/android/save.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);
        initUI();

        btnIngresar.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
    }

    private void initUI() {

        txtUsername = findViewById(R.id.txtUsernameLogin);
        txtPassword =  findViewById(R.id.txtPasswordLogin);
//Buttons
        btnRegistrar =  findViewById(R.id.btnEditar);
        btnIngresar =  findViewById(R.id.btnIngresar);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();


        if(id== R.id.btnIngresar){
            String name = txtUsername.getText().toString().trim();
            String password = txtPassword.getText().toString().trim();

            findUser(name, password);
        } else if (id == R.id.btnEditar){
            Intent intent = new Intent(getApplicationContext(),MainActivity7.class);
            startActivity(intent);
        }
    }

    private void findUser(final String name, final String password) {

    }
}