package com.example.user.sqldatenbank;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    //Variablen
    Database helper;
    Button add,load,delete,update;
    EditText getName,getNachname,getGruppe;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Erstellt neues Database Objekt
        helper = new Database(this);
        //Deklariert alle Variablen der Buttons
        add = (Button)findViewById(R.id.Add);
        load = (Button)findViewById(R.id.Load);
        delete = (Button)findViewById(R.id.Löschen);
        update = (Button)findViewById(R.id.Update);
        //Deklariert alle Variablen der Edit Texts
        getName = (EditText)findViewById(R.id.VornameEinlesen);
        getNachname = (EditText)findViewById(R.id.NachnameEinlesen);
        getGruppe = (EditText)findViewById(R.id.GruppeEinlesen);
        //Methoden
        Add();
        Load();
        WechselZuUpdate();
        WechselZuDelete();

    }

    public void Add()
    {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                boolean wahr = helper.Insert(getName.getText().toString(),getNachname.getText().toString(),getGruppe.getText().toString());
                if(wahr == true)
                {
                    Toast.makeText(MainActivity.this,"Daten wurden erfolgreich gespeichert",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"FEHLER",Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    public void Load()
    {
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Cursor res = helper.Select();
                if(res.getCount() == 0)
                {
                    Nachricht("FEHLER", "Keine Daten");
                }
                StringBuffer stringBuffer = new StringBuffer();

                while (res.moveToNext())
                {
                    stringBuffer.append("ID :" + res.getString(0) + "\n");
                    stringBuffer.append("Vorname  " + res.getString(1) + "\n");
                    stringBuffer.append("Nachname  " + res.getString(2) + "\n");
                    stringBuffer.append("Gruppe  " + res.getString(3) + "\n");
                }

                Nachricht("Alle Daten",stringBuffer.toString());
            }
        });
    }

    public void Nachricht(String titel, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titel);
        builder.setMessage(message);
        builder.show();
    }

    public void WechselZuUpdate()
    {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Update.class);
                startActivity(intent);

            }
        });
    }

    public void WechselZuDelete()
    {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
             Intent i = new Intent(MainActivity.this,Delete.class);
                startActivity(i);

            }
        });
    }


}
