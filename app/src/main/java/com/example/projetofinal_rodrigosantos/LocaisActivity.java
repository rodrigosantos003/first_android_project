package com.example.projetofinal_rodrigosantos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LocaisActivity extends ListActivity {

    private Cursor crs;
    Button guardarLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locais);

        final LocaisSQL dbHelper = new LocaisSQL(LocaisActivity.this);

        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final String[] fieldsDB = {"local", BaseColumns._ID};

        crs = db.query("locais", fieldsDB, null, null, null, null, "local ASC");

        final int []fieldsView = new int[] {android.R.id.text1};

        final SimpleCursorAdapter adapt = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item, crs, fieldsDB, fieldsView, 0);
        setListAdapter(adapt);

        final EditText text = findViewById(R.id.editText);
        guardarLocal = findViewById(R.id.btn_guardarLocal);

        guardarLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x = text.getText().toString();
                text.setText("");
                dbHelper.guardar(db, x);

                crs = db.query("locais", fieldsDB, null, null, null, null, "local ASC");
                final  SimpleCursorAdapter adapt = new SimpleCursorAdapter(LocaisActivity.this, android.R.layout.two_line_list_item, crs, fieldsDB, fieldsView, 0);
                setListAdapter(adapt);
            }
        });

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor crs = ((SimpleCursorAdapter)parent.getAdapter()).getCursor();
                crs.moveToPosition(position);
                String x = crs.getString(0);

                Intent i = new Intent(getApplicationContext(), MapaActivity.class);
                i.putExtra("local", x);
                startActivity(i);
            }
        });
    }
}