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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity11 extends AppCompatActivity implements View.OnClickListener {

    EditText userName, userPassword, userEmail, nameUser, etId;
    Button editar, borrar;
    String id;

    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);

        requestQueue = Volley.newRequestQueue(this);

    Bundle extras = getIntent().getExtras();
    if(extras!=null){
        Toast.makeText(MainActivity11.this, id, Toast.LENGTH_SHORT).show();
        id = extras.getString("id");
    }
        initUI();

    readUser();
    editar.setOnClickListener(this);
    borrar.setOnClickListener(this);

    }

    private void initUI() {
        //EditText
        nameUser = findViewById(R.id.txtUsername);
        userName = findViewById(R.id.txtUsernameLogin);
        userPassword =  findViewById(R.id.txtPasswordLogin);
        userEmail =  findViewById(R.id.txtEmail);
        etId = findViewById(R.id.txtFetch);
        //Buttons
        editar =  findViewById(R.id.btnEditar);
        borrar =  findViewById(R.id.btnFetch);
    }

    private void readUser(){
        String URL = "http://192.168.100.54/android/fetch.php?id=" + id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, URL, null,
                response -> {
                     String name, email, password, username;
                     try {
                         name = response.getString("name");
                         email = response.getString("email");
                         password = response.getString("password");
                         username = response.getString("username");

                         nameUser.setText(name);
                         userName.setText(username);
                         userEmail.setText(email);
                         userPassword.setText(password);

                     } catch (JSONException e) {
                         e.printStackTrace();
                     }
                 },
                error -> {

                }
        );

        requestQueue.add(jsonObjectRequest);

    }


    @Override
    public void onClick(View v){
        int id = v.getId();

        if(id == R.id.btnEditar){

            String name = nameUser.getText().toString().trim();
            String email = userEmail.getText().toString().trim();
            String password = userPassword.getText().toString().trim();
            String username = userName.getText().toString().trim();
            
            updateUser(name,email,password,username);

        } else if(id== R.id.btnBorrar){
            String idUser = etId.getText().toString().trim();

            removeUser(idUser);
        }
    }

    private void updateUser(String name, String email, String password, String username) {
        String URL = "http://192.168.100.54/android/edit.php";

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                response -> {
                    Toast.makeText(MainActivity11.this, "actualizado correctamente", Toast.LENGTH_SHORT).show();
                }, error -> {

        }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("name", name);
                params.put("username", username);
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    private void removeUser(String idUser) {
        String URL = "http://192.168.100.54/android/delete.php";

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                response -> {
                        finish();
                }, error -> {

                }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", idUser);
                        return super.getParams();
            }
        };

        requestQueue.add(stringRequest);

    }

}