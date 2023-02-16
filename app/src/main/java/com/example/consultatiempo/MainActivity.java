package com.example.consultatiempo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etLongitud;
    EditText etLatitud;
    Button btnConsultar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLongitud = findViewById(R.id.editTextLongitud);
        etLatitud = findViewById(R.id.editTextLatitud);
        btnConsultar = findViewById(R.id.btnConsulta);
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                    intent.putExtra("longitud", Double.parseDouble(etLongitud.getText().toString()));
                    intent.putExtra("latitud", Double.parseDouble(etLatitud.getText().toString()));
                    startActivity(intent);

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error en los datos", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}