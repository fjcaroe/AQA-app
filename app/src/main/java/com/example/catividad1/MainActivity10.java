package com.example.catividad1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity10 extends AppCompatActivity {
    EditText number, valueh, sector, percent;
    Button update, back;

    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);
        number = (EditText) findViewById(R.id.txtUsernameLogin);
        valueh = (EditText) findViewById(R.id.vh);
        sector = (EditText) findViewById(R.id.sector);
        update = (Button) findViewById(R.id.btnEditar);
        back = (Button) findViewById(R.id.btnFetch);
        DB = new DBHelper(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(),MainActivity5.class);
                startActivity(intent2);
            }});
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numero   = number.getText().toString();
                String valorhora = valueh.getText().toString();
                String sect = sector.getText().toString();
                String porcentaje = percent.getText().toString();

                if(numero.equals("")||valorhora.equals("")||sect.equals("")||porcentaje.equals("")){
                    Toast.makeText(MainActivity10.this, "Favor llenar los campos", Toast.LENGTH_SHORT).show();
                }
            }});
    }
}