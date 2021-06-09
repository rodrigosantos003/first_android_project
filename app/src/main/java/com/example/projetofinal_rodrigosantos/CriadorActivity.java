package com.example.projetofinal_rodrigosantos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class CriadorActivity extends AppCompatActivity {

    TextView nome, telefone, local;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criador);

        nome = findViewById(R.id.txt_Nome);
        telefone = findViewById(R.id.txt_Telefone);
        local = findViewById(R.id.txt_Local);

        nome.setText(Html.fromHtml(getString(R.string.nome)));
        telefone.setText(Html.fromHtml(getString(R.string.telefone)));
        local.setText(Html.fromHtml(getString(R.string.local)));
    }
}