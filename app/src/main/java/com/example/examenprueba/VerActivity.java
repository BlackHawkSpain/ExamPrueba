package com.example.examenprueba;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.db.DbContactos;
import com.example.entidades.Contactos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerActivity extends AppCompatActivity {

    EditText txtNombre, txtTelefono;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabLlamar, fabBorrrar;
    Contactos contacto;
    int id = 0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        // btnGuarda = findViewById(R.id.btnGuarda);
        fabEditar = findViewById(R.id.fabEditar);
        fabLlamar = findViewById(R.id.fabLlamar);
        fabBorrrar = findViewById(R.id.fabBorrrar);
        btnGuarda.setVisibility(View.INVISIBLE);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbContactos dbContactos = new DbContactos(VerActivity.this);
        contacto = dbContactos.verContacto(id);

        if (contacto != null) {
            txtNombre.setText(contacto.getNombre());
            txtTelefono.setText(contacto.getTelefono());
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtTelefono.setInputType(InputType.TYPE_NULL);
        }
        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabBorrrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dbContactos.eliminarContacto(id)) {
                    Toast.makeText(VerActivity.this, "CONTACTO BORRADO", Toast.LENGTH_LONG).show();
                    lista();
                }

            }
        });

        fabLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = txtTelefono.getText().toString();
                Uri number = Uri.parse("tel:" + num);
                Intent dial = new Intent(Intent.ACTION_DIAL, number);
                startActivity(dial);

            }
        });

    }
    private void lista() {
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }


}
