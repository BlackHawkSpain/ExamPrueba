package com.example.examenprueba;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.db.DbContactos;

public class NuevoActivity extends AppCompatActivity {
    EditText txtNombre, txtTelefono;
    Button btnGuarda;
    //MediaPlayer mp, mp2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        btnGuarda = findViewById(R.id.btnGuarda);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbContactos dbContactos = new DbContactos(NuevoActivity.this);

                if (!txtNombre.getText().toString().equals("") && !txtTelefono.getText().toString().equals("")) {
                    long id = dbContactos.insertarContacto(txtNombre.getText().toString(), txtTelefono.getText().toString());

                    if (id > 0) {
                        Toast.makeText(NuevoActivity.this, "CONTACTO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR ", Toast.LENGTH_LONG).show();
                    }
                } else {

                    Toast.makeText(NuevoActivity.this, "COMPLETE LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }


            }
        });

    }
    private void limpiar() {
        txtNombre.setText("");
        txtTelefono.setText("");
    }
}
