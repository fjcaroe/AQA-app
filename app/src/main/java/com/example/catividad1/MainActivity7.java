package com.example.catividad1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity7 extends AppCompatActivity implements View.OnClickListener {

    EditText userName, userPassword, userEmail, nameUser, userBuscar;
    Button registrar, buscarId;

    RequestQueue requestQueue;

    private static final String URL1 = "http://192.168.100.54/android/save.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);


        requestQueue = Volley.newRequestQueue(this);
        initUI();

    registrar.setOnClickListener(this);
    buscarId.setOnClickListener(this);

    }

    private void initUI() {
        //EditText
        nameUser = findViewById(R.id.txtUsername);
        userName = findViewById(R.id.txtUsernameLogin);
        userPassword =  findViewById(R.id.txtPasswordLogin);
        userEmail =  findViewById(R.id.txtEmail);
        userBuscar = findViewById(R.id.txtFetch);
        //Buttons
        registrar =  findViewById(R.id.btnEditar);
        buscarId =  findViewById(R.id.btnFetch);
    }
    @Override
    public void onClick(View v){
        int id = v.getId();

        if(id == R.id.btnEditar){
            String name = nameUser.getText().toString().trim();
            String email = userEmail.getText().toString().trim();
            String password = userPassword.getText().toString().trim();
            String username = userEmail.getText().toString().trim();

            createUser(name, email, password, username);
        } else if(id== R.id.btnFetch){
            Intent intent = new Intent(getApplicationContext(),MainActivity11.class);
            intent.putExtra("id", userBuscar.getText().toString().trim());
            startActivity(intent);
        }
    }

    private void createUser( String name,  String email,  String password,  String username) {

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL1,
                response -> Toast.makeText(MainActivity7.this, "Creado", Toast.LENGTH_SHORT).show(),
                error -> {

                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                params.put("username", username);
                return super.getParams();
            }
        };

        requestQueue.add(stringRequest);
    }
}