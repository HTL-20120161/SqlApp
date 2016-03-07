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
public class Delete extends Activity
{
    Database helper;
    EditText id;
    Button delete;

    protected void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.delete);

        helper = new Database(this);

        id = (EditText)findViewById(R.id.GetDeleteID);

        delete = (Button)findViewById(R.id.DeleteAusführen);

        DeleteAndChange();
    }

    public void DeleteAndChange()
    {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Integer erg = helper.Delete(id.getText().toString());
               if(erg>0)
               {
                   Toast.makeText(Delete.this, "Daten wurden Gelöscht", Toast.LENGTH_LONG).show();
                   Wechseln();
               }
               else
               {
                   Toast.makeText(Delete.this,"FEHLER",Toast.LENGTH_LONG).show();
               }
            }
        });
    }

    public void Wechseln()
    {
        Intent i = new Intent(Delete.this,MainActivity.class);
        startActivity(i);
    }
}
