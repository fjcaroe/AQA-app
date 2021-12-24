package com.example.catividad1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity8 extends AppCompatActivity {

    EditText username, password, repassword;
    Button signup, back;

    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        username = (EditText) findViewById(R.id.txtUsernameLogin);
        password = (EditText) findViewById(R.id.txtPasswordLogin);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnEditar);
        back = (Button) findViewById(R.id.btnFetch);
        DB = new DBHelper(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(),MainActivity3.class);
                startActivity(intent2);
            }});

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals("")){
                    Toast.makeText(MainActivity8.this, "Favor llenar los campos", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(user.equals("admin")){

                            Boolean insert = DB.insertData(user,pass);
                            if(insert==true){
                                Toast.makeText(MainActivity8.this,"Bienvenido Admin", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity8.this, "Usuario ya existe", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            if(checkuser==false){
                                Boolean insert = DB.insertData(user,pass);
                                if(insert==true){
                                    Toast.makeText(MainActivity8.this,"Registro Exitoso", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),MainActivity3.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(MainActivity8.this, "Registro fallido", Toast.LENGTH_SHORT).show();

                                }
                            }
                            else {
                                Toast.makeText(MainActivity8.this, "Usuario ya existe", Toast.LENGTH_SHORT).show();
                            }
                        }

                }
                    else {
                        Toast.makeText(MainActivity8.this, "Clave no coincide", Toast.LENGTH_SHORT).show();
                    }
            }
        }});
    }
}