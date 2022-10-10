package com.example.listilla;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Model: Record (intents=puntuació, nom)
    class Record {
        public int intents;
        public String nom;

        public Record(int _intents, String _nom ) {
            intents = _intents;
            nom = _nom;
        }
    }

    ArrayList<String> nousNoms;
    ArrayList<String> nousCognoms;

    // Model = Taula de records: utilitzem ArrayList
    ArrayList<Record> records;


    // ArrayAdapter serà l'intermediari amb la ListView
    ArrayAdapter<Record> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialitzem model
        records = new ArrayList<Record>();
        nousNoms = new ArrayList<String>();
        nousCognoms = new ArrayList<String>();

        //Generem alguns noms possibles quan mes poguem crear, mes facil sera barrejar

        nousNoms.add("Menja");
        nousNoms.add("Buscador de");
        nousNoms.add("Professional");
        nousNoms.add("Dutxat");
        nousNoms.add("Gimnasta");
        nousNoms.add("Friki");
        nousNoms.add("Guardia de la nit");

        nousCognoms.add(" independentista");
        nousCognoms.add(" amargat");
        nousCognoms.add(" programador");
        nousCognoms.add(" frances");
        nousCognoms.add(" botiflers");
        nousCognoms.add(" jove");

        // Afegim alguns exemples
        records.add( new Record(33,"Manolo") );
        records.add( new Record(12,"Pepe") );
        records.add( new Record(42,"Laura") );

        // Inicialitzem l'ArrayAdapter amb el layout pertinent
        adapter = new ArrayAdapter<Record>( this, R.layout.list_item, records )
        {
            @Override
            public View getView(int pos, View convertView, ViewGroup container)
            {
                // getView ens construeix el layout i hi "pinta" els valors de l'element en la posició pos
                if( convertView==null ) {
                    // inicialitzem l'element la View amb el seu layout
                    convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
                }
                // "Pintem" valors (també quan es refresca)
                ((TextView) convertView.findViewById(R.id.nom)).setText(getItem(pos).nom);
                ((TextView) convertView.findViewById(R.id.intents)).setText(Integer.toString(getItem(pos).intents));
                return convertView;
            }

        };

        // busquem la ListView i li endollem el ArrayAdapter
        ListView lv = (ListView) findViewById(R.id.recordsView);
        lv.setAdapter(adapter);

        // botó per afegir entrades a la ListView
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<25;i++) {
                    int randomNum = (int) Math.floor(Math.random()*(99-1+1)+1);

                    int randomNom = (int) Math.floor(Math.random()*(nousNoms.size()-1+1));
                    int randomCognom = (int) Math.floor(Math.random()*(nousCognoms.size()-1+1));

                    records.add(new Record(randomNum, nousNoms.get(randomNom)+nousCognoms.get(randomCognom)));
                }
                // notificar l'adapter dels canvis al model
                adapter.notifyDataSetChanged();
            }
        });
    }
}