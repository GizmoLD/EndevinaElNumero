package com.example.endevinaelnumero;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.button);
        final EditText numeroEscrito = findViewById(R.id.editTextNumber);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final TextView numerosProvados = findViewById(R.id.numerosProvados);
        //
        final TextView intentosPartida = findViewById(R.id.intentos);

        button.setOnClickListener(new View.OnClickListener() {
            int numero= (int)(Math.random()*10+1);
            int intentos = 0;
            @Override
            public void onClick(View v){
                intentos = intentos+1;
                intentosPartida.setText("Intentos :"+intentos);
                System.out.println(numero);

                if(TextUtils.isEmpty(numeroEscrito.getText().toString())){
                    int duration = Toast.LENGTH_SHORT;
                   Toast toast = Toast.makeText(MainActivity.this,"Ingresa un numero",duration);
                   toast.show();
                }else{
                    int numeroEscrito2= Integer.parseInt(numeroEscrito.getText().toString());

                    if (this.numero == numeroEscrito2){
                        CharSequence texto = "Ganaste";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText( MainActivity.this, texto,duration);
                        toast.show();
                        builder.setTitle("Ganaste");
                        builder.setMessage("Volver a jugar");
                        builder.setPositiveButton("Aceptar",null);

                        AlertDialog dialog = builder.create();
                        dialog.show();

                        numero = (int)(Math.random()*10+1);
                        intentosPartida.setText("Intentos :"+0);
                        numerosProvados.setText("");
                    }else if (this.numero > numeroEscrito2){
                        numerosProvados.setText(numerosProvados.getText()+String.valueOf(numeroEscrito2)+"\n");
                        CharSequence texto = "El numero es mayor";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText( MainActivity.this, texto,duration);
                        toast.show();
                    }else{
                        numerosProvados.setText(numerosProvados.getText()+String.valueOf(numeroEscrito2)+"\n");
                        CharSequence texto = "El numero es menor";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText( MainActivity.this, texto,duration);
                        toast.show();
                    }
                }
            }
        });
    }
}