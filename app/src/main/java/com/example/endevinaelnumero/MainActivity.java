package com.example.endevinaelnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.button);
        final EditText numeroEscrito = findViewById(R.id.editTextNumber);

        button.setOnClickListener(new View.OnClickListener() {
            int numero = (int)(Math.random()*10+1);
            @Override
            public void onClick(View v){

                int numeroEscrito2= Integer.parseInt(numeroEscrito.getText().toString());

                if (this.numero == numeroEscrito2){
                    CharSequence texto = "Ganaste";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText( MainActivity.this, texto,duration);
                    toast.show();
                }else if (this.numero > numeroEscrito2){
                    CharSequence texto = "Ganaste";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText( MainActivity.this, texto,duration);
                    toast.show();
                }
            }
        });

    }
}