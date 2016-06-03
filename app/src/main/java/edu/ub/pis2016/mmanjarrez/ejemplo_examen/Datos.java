package edu.ub.pis2016.mmanjarrez.ejemplo_examen;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by orla_ on 03/06/2016.
 */
public class Datos extends AppCompatActivity {

    private Button guardar, cargar, cambio, personas;
    private EditText nom1, apellido1, edad1;
    private TextView texto;
    private RelativeLayout aux;
    private int x;
    private listaPersonas lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        this.x = 0;
        this.lista = new listaPersonas();

        this.guardar = (Button) findViewById(R.id.guardar);
        this.cargar = (Button) findViewById(R.id.cargar);
        this.cambio = (Button) findViewById(R.id.cambia);
        this.personas = (Button) findViewById(R.id.pers);
        this.nom1 = (EditText) findViewById(R.id.nom1);
        this.apellido1 = (EditText) findViewById(R.id.apellido1);
        this.edad1 = (EditText) findViewById(R.id.edad1);
        this.aux = (RelativeLayout) findViewById(R.id.aux);
        this.texto = (TextView) findViewById(R.id.personas);

        /**
         * Boton guardar
         */
        this.guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatos();

            }
        });

        /**
         * Boton cargar
         */
        this.cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarDatos();

            }
        });

        /**
         * Boton cambio imagen
         */
        this.cambio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (x == 0) {
                    x = 1;
                    aux.setBackgroundResource(R.drawable.ha2);
                } else {
                    x = 0;
                    aux.setBackgroundResource(R.drawable.ha1);
                }

            }
        });

        /**
         * Boton agrega personas
         */
        this.personas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista.agregaPersona(nom1.getText().toString(),apellido1.getText().toString(),edad1.getText().toString());
                texto.setText(lista.toString());
                texto.setMovementMethod(new ScrollingMovementMethod());
                nom1.setText("");
                apellido1.setText("");
                edad1.setText("");
            }
        });
    }


    public void guardarDatos () {
        try {
            ObjectOutputStream archivo=new ObjectOutputStream(openFileOutput("notas.txt", Activity.MODE_PRIVATE));
            archivo.writeObject(nom1.getText().toString());
            archivo.writeObject(apellido1.getText().toString());
            archivo.writeObject(edad1.getText().toString());
            archivo.writeObject(this.lista);
            archivo.writeObject(new Persona("orla","mar","23"));
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
        }
        Toast t = Toast.makeText(this, "Los datos fueron grabados",
                Toast.LENGTH_SHORT);
        t.show();
        finish();
    }

    public void cargarDatos () {
        try {
            ObjectInputStream br=new ObjectInputStream(openFileInput("notas.txt"));
            nom1.setText((String)br.readObject());
            apellido1.setText((String) br.readObject());
            edad1.setText((String) br.readObject());
            this.lista = (listaPersonas) br.readObject();
            Persona per = (Persona) br.readObject();
            texto.setText(per.getNombre());
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Toast t = Toast.makeText(this, "Los datos fueron cargados",
                Toast.LENGTH_SHORT);
        t.show();
    }

}
