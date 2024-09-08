package com.example.lab2_iot_20201638;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    public void buttonPress(View v){

        TextInputEditText nameField = findViewById(R.id.TextImput);
        String name = nameField.getText().toString();
        if (name.isEmpty()) {
            // Si está vacío, muestra un mensaje de error (puedes usar un Toast o setError en el campo)
            nameField.setError("Por favor, ingrese su nombre");  // Muestra un mensaje de error debajo del campo
        } else {
            // Si el campo no está vacío, procede a la siguiente actividad
            Intent miIntent = new Intent(this, MainActivity2.class);
            startActivity(miIntent);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


}