package com.example.projetofinal_rodrigosantos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class DataNascimentoActivity extends AppCompatActivity {

    Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_nascimento);

        final DatePicker data = findViewById(R.id.data);
        DatePicker.OnDateChangedListener onDateChangedListener = null;
        data.init(2000, 0, 1, onDateChangedListener);

        guardar = findViewById(R.id.btn_guardarData);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences dados = getSharedPreferences("DATA", 0);
                SharedPreferences.Editor ed = dados.edit();

                int day = data.getDayOfMonth();
                ed.putInt("DIA", day);
                int month = data.getMonth();
                ed.putInt("MES", month);
                int year = data.getYear();
                ed.putInt("ANO", year);

                ed.commit();

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}