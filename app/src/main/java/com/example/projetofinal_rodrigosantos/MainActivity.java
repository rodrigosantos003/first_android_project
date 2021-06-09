package com.example.projetofinal_rodrigosantos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button locais, data, criador;
    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locais = findViewById(R.id.btn_locais);
        locais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LocaisActivity.class);
                startActivity(i);
            }
        });

        data = findViewById(R.id.btn_data);
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DataNascimentoActivity.class);
                startActivity(i);
            }
        });

        criador = findViewById(R.id.btn_criador);
        criador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CriadorActivity.class);
                startActivity(i);
            }
        });

        aniversario();
    }

    @Override
    protected void onResume(){
        super.onResume();

        aniversario();
    }

    private void aniversario(){
        SharedPreferences dados = getSharedPreferences("DATA", 0);
        int day = dados.getInt("DIA", 0);
        int month = dados.getInt("MES", 0) + 1;
        int year = dados.getInt("ANO", 0);

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        int currentYear = cal2.get(Calendar.YEAR);
        int currentMonth = cal2.get(Calendar.MONTH);
        int currentDay = cal2.get(Calendar.DAY_OF_MONTH);

        if (currentMonth > month-1){
            currentYear += 1;
        }
        if(currentMonth == month-1 && currentDay > day){
            currentYear += 1;
        }

        cal1.set(currentYear, month-1, day);

        long m1 = cal1.getTimeInMillis();
        long m2 = cal2.getTimeInMillis();

        long dif = m1-m2;
        long difDays = dif / (24 * 60 * 60 * 1000);

        long difYears = currentYear - year;

        texto = findViewById(R.id.texto);

        if (year == 0){
            texto.setText(R.string.texto);
        }
        else{
            texto.setText("Faltam " + difDays + " dias para o teu " + difYears + "º aniversario");
        }
    }
}