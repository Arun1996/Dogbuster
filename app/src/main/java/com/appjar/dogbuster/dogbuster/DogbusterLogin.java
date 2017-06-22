package com.appjar.dogbuster.dogbuster;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DogbusterLogin extends AppCompatActivity{

    Button btnLogin; TextView textEmail,textPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogbuster_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), DogbusterRegister.class);
                startActivity(intent);
            }
        });

        textEmail=(TextView)findViewById(R.id.txtEmail);
        textPass=(TextView)findViewById(R.id.txtPassword);

        btnLogin=(Button)findViewById(R.id.cmdLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String res="";
                ServerInterface SI=new ServerInterface(getBaseContext(), new AsyncResponse() {
                    @Override
                    public void processFinish(String output) {

                        if(output.contains("Success"))
                        {
                            Toast.makeText(getBaseContext(), "Login Successful!",
                                    Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(getBaseContext(), DogbusterCentral.class);
                            startActivity(intent);

                        }
                        else
                        {
                            Toast.makeText(getBaseContext(), "Invalid Credentials! Please try again...",
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                },1);

                String email=textEmail.getText().toString(); String pass=(String)textPass.getText().toString();
                SI.execute(email,pass);
            }
        });
    }
    @Override
    public void onBackPressed()
    {
            //super.onBackPressed();
    }
}
