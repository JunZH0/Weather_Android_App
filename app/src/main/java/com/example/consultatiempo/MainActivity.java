package com.example.consultatiempo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText longitud;
    EditText latitud;
    Button consultar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        longitud = findViewById(R.id.editTextLongitud);
        latitud = findViewById(R.id.editTextLatitud);
        consultar = findViewById(R.id.btnConsulta);


    }
}