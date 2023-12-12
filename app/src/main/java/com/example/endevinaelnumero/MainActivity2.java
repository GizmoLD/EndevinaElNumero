package com.example.endevinaelnumero;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity2 extends AppCompatActivity {
    static ArrayList<Record> records = new ArrayList<Record>();
    private TextView textView2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView2 = findViewById(R.id.textView2);
        button = findViewById(R.id.button);

        // Inicializar registros
        if (records.size()==0){
            Record r1 = new Record("Juan", 10);
            Record r2 = new Record("Carlos", 20);
            Record r3 = new Record("Tyson", 1);

            records.add(r1);
            records.add(r2);
            records.add(r3);
        }

        // Mostrar registros en el TextView
        displayRecords();

        if (getIntent().getStringExtra("NOMBRE")!=null){
            Record r4 = new Record(
                    getIntent().getStringExtra("NOMBRE"),
                    getIntent().getIntExtra("PUNTUACION",0));
            records.add(r4);
            displayRecords();
        }

        // Registrar el receptor de difusión
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRecord();
            }
        }
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    public void goToRecord(){
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }

    private void displayRecords() {
        StringBuilder texto = new StringBuilder();
        Collections.sort(records,(r1,r2) -> Integer.compare(r2.getPuntuacio(), r1.getPuntuacio()));
        Collections.reverse(records);

        for (Record r : records) {
            texto.append(r.getNom()).append("     ").append(r.getPuntuacio()).append("\n");
        }
        textView2.setText(texto.toString());
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String nom = intent.getStringExtra("NOMBRE");
            int puntuacion = intent.getIntExtra("PUNTUACION", 0);
            // Agregar nuevo registro
            Record record = new Record(nom, puntuacion);
            records.add(record);

            // Mostrar registros actualizados
            displayRecords();
        }
    };
}