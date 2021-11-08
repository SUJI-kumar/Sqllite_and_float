package com.example.ca2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
    EditText name,regno;
    Button button_add,button_view,btn_delete,btn_update;
    database g;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("P1SQLiteStudentMgmtSystem");
        g=new database(this);
//        SQLiteDatabase db=g.getReadableDatabase();
        name = findViewById(R.id.edittext_name);
        regno = findViewById(R.id.edittext_regno);
        button_add = findViewById(R.id.button_add);
        button_view = findViewById(R.id.button_view);
        btn_delete=findViewById(R.id.button_delete);
        btn_update=findViewById(R.id.button_update);


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = name.getText().toString();
                String string_reg =regno.getText().toString();

                if (stringName.isEmpty() || string_reg.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Enter All the Fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean i=g.insert_data(stringName,string_reg);
                    if(i == true)
                    {
                        Toast.makeText(getApplicationContext(), "Data is added Successfully", Toast.LENGTH_LONG).show();
                    }else
                    {
                        Toast.makeText(getApplicationContext(), "Data is not added", Toast.LENGTH_LONG).show();
                    }
                    name.setText("");
                    regno.setText("");
                }
            }
        });
        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor t=g.getinfo();
                if(t.getCount()==0)
                {
                    Toast.makeText(MainActivity.this,"No data Found",Toast.LENGTH_LONG).show();
                }
                StringBuffer buffer=new StringBuffer();
                while (t.moveToNext())
                {
                    buffer.append("ID:"+t.getString(0)+"\n");
                    buffer.append("NAME:"+t.getString(1)+"\n");
                    buffer.append("Reg_No:"+t.getString(2)+"\n\n");
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Student Data");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reg_no=regno.getText().toString();
                if(reg_no.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter username First",Toast.LENGTH_LONG).show();
                }else {
                    Boolean i = g.deletedata((reg_no));
                    if (i == true) {
                        Toast.makeText(MainActivity.this, "Delection Succesful", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Delection UnSuccesful", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reg_no=regno.getText().toString();
                String new_name=name.getText().toString();
                if(new_name.isEmpty() || reg_no.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter all Fields",Toast.LENGTH_LONG).show();
                }else
                {
                    Boolean i=g.update_data(new_name,reg_no);
                    if(i == true)
                    {
                        Toast.makeText(MainActivity.this,"Updation Succesful",Toast.LENGTH_LONG).show();
                    }else
                    {
                        Toast.makeText(MainActivity.this,"Updation UnSuccesful",Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

    }
}