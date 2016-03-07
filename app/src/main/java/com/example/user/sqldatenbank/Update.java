package com.example.user.sqldatenbank;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by User on 07.03.2016.
 */
public class Update extends Activity
{
    //Variablen erzeugen
    Database helper;
    Button update;
    EditText id,vorname,nachname,gruppe;

    protected void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.update);
        //Objekt für Datenbankklasse deklarieren
        helper = new Database(this);
        //Button deklarieren
        update = (Button)findViewById(R.id.UpdateDurchführen);
        //Edit texte Deklariren
        id = (EditText)findViewById(R.id.GetUpdateId);
        vorname = (EditText)findViewById(R.id.GetUpdateName);
        nachname = (EditText)findViewById(R.id.GetUpdateNachname);
        gruppe = (EditText)findViewById(R.id.GetUpdateGruppe);
        //Methoden
        UpdateAndChange();
    }

    public void UpdateAndChange()
    {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                boolean wahr = helper.Update(id.getText().toString(),vorname.getText().toString(),nachname.getText().toString(),gruppe.getText().toString());

                if(wahr == true)
                {
                    Toast.makeText(Update.this, "Update erfolgreich", Toast.LENGTH_LONG).show();
                    Wechsel();
                }
                else
                {
                    Toast.makeText(Update.this,"FEHLER",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void Wechsel()
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
