package com.example.endevinaelnumero;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int numero;
    private int intentos;
    private EditText numeroEscrito;
    private TextView numerosProvados;
    private TextView intentosPartida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.button);
        final Button button2 = findViewById(R.id.button2);

        numeroEscrito = findViewById(R.id.editTextNumber);
        numerosProvados = findViewById(R.id.numerosProvados);
        intentosPartida = findViewById(R.id.intentos);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarIntento();
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                goToRecord();
            }
        });

        numeroEscrito.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
                if (actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_DONE){
                    realizarIntento();
                    return true;
                }
                return false;
            }
        });

        reiniciarJuego();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void reiniciarJuego() {
        numero = (int)(Math.random()*10+1);
        intentos = 0;
        intentosPartida.setText("Intentos: 0");
        numerosProvados.setText("");
    }

    private void compararNumeros(int numeroEscrito) {
        showToast(""+numero);
        if (numero == numeroEscrito) {
            mostrarDialogoGanador();
        } else if (numero > numeroEscrito) {
            mostrarMensaje("El número es mayor");
        } else {
            mostrarMensaje("El número es menor");
        }
    }

    private void mostrarDialogoGanador() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Quieres subir tu récord al ranking?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mostrarDialogoNombre();
                        //reiniciarJuego();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        reiniciarJuego();
                    }
                })
                .show();
    }
    private void mostrarDialogoNombre(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Ingresa tu nombre");

        final EditText inputName = new EditText(this);
        builder.setView(inputName);

        builder.setPositiveButton("Enviar", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                Log.d("TAG", "Mensaje");
                String nombre = inputName.getText().toString();
                if (!TextUtils.isEmpty(nombre)){
                    // Enviar datos a la otra aplicación
                    Log.d("TAG", "Nombre: " + nombre + ", Puntuacion: " + intentos);
                    sendRecord(nombre,intentos);

                    // Abrir la aplicación de ranking
                    //showRanking();
                }else{
                    showToast("Por favor, ingresa tu nombre.");
                }
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                realizarIntento();
            }
        });

        builder.show();
    }
    private void mostrarMensaje(String mensaje) {
        numerosProvados.setText(numerosProvados.getText() + numeroEscrito.getText().toString() + "\n");
        showToast(mensaje);
    }

    private void realizarIntento() {
        intentos++;
        intentosPartida.setText("Intentos: " + intentos);

        if(TextUtils.isEmpty(numeroEscrito.getText().toString())){
            showToast("Ingresa un número");
        } else {
            int numeroEscrito2 = Integer.parseInt(numeroEscrito.getText().toString());
            compararNumeros(numeroEscrito2);
        }
        numeroEscrito.setText("");
    }

    public void sendRecord(String nombre, int record) {
        Intent intent = new Intent(this,MainActivity2.class);

        //intent.setAction("com.example.ranking.RECEIVE_DATA"); // Cambia a la acción correcta
        intent.putExtra("NOMBRE", nombre);
        intent.putExtra("PUNTUACION", record);
        //intent.setPackage("com.example.ranking"); // Paquete de la aplicación de destino
        reiniciarJuego();
        startActivity(intent);
    }

    public void goToRecord(){
        Intent i = new Intent(getApplicationContext(),MainActivity2.class);
        startActivity(i);
    }
}
