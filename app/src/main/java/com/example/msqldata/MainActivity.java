package com.example.msqldata;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.cert.TrustAnchor;

public class MainActivity extends AppCompatActivity {


    EditText nameET, contactET, dobET;

    Button insertBtn, updateBtn, deleteBtn,viewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );


        nameET = (EditText)findViewById ( R.id.name_ET );
        contactET = (EditText) findViewById ( R.id.contact_ET );
        dobET = (EditText) findViewById ( R.id.dob_ET );

        insertBtn = (Button) findViewById ( R.id.btnInsert );
        updateBtn = (Button) findViewById ( R.id.btnUpdate );
        deleteBtn = (Button) findViewById ( R.id.btnDelete );
        viewBtn = (Button) findViewById ( R.id.btnView );

        BDHelper bdHelper = new BDHelper ( this );

        insertBtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                String name = nameET.getText ().toString ();
                String contact = contactET.getText ().toString ();
                String dob = dobET.getText ().toString ();

                Boolean checkingdata = bdHelper.insertData ( name, contact, dob );

                if (checkingdata == true){

                    Toast.makeText ( MainActivity.this, "New Entry Inserted", Toast.LENGTH_LONG ).show ();
                }else {
                    Toast.makeText ( MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_LONG ).show ();
                }
            }
        } );


        updateBtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                String name = nameET.getText ().toString ();
                String contact = contactET.getText ().toString ();
                String dob = dobET.getText ().toString ();

                Boolean checkUpdatedata = bdHelper.UpdateDate ( name, contact, dob );

                if (checkUpdatedata == true){

                    Toast.makeText ( MainActivity.this, "Data Update", Toast.LENGTH_LONG ).show ();

                }else {
                    Toast.makeText ( MainActivity.this, "Data Not UPdate", Toast.LENGTH_LONG ).show ();
                }
            }
        } );


        deleteBtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                String name  = nameET.getText ().toString ();

                Boolean checkDelete = bdHelper.deleteData ( name );

                if (checkDelete == true){

                    Toast.makeText ( MainActivity.this, "Data Delete", Toast.LENGTH_LONG ).show ();
                }else {
                    Toast.makeText ( MainActivity.this, "Date Not Delete", Toast.LENGTH_LONG ).show ();
                }
            }
        } );

        viewBtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                Cursor cursor = bdHelper.ShowData ();

                if (cursor.getCount () == 0) {

                    Toast.makeText ( MainActivity.this, "Data Not Show", Toast.LENGTH_LONG ).show ();
                    return;
                }
                StringBuffer buffer = new StringBuffer ();

                while (cursor.moveToNext ()){

                    buffer.append ( "Name : "+cursor.getString ( 0 ) + "\n" );
                    buffer.append ( "Contact : "+cursor.getString ( 1 ) + "\n"  );
                    buffer.append ( "Date of Birth : "+cursor.getString ( 2 )+ "\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder ( MainActivity.this );

                builder.setCancelable ( true );
                builder.setTitle ( "User Entries" );
                builder.setMessage ( buffer.toString () );
                builder.show ();
            }
        } );

    }
}