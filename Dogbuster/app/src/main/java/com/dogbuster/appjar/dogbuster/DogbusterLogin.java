package com.dogbuster.appjar.dogbuster;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class DogbusterLogin extends AppCompatActivity {

    EditText inputMail,inputPwd; private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogbuster_login);

        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), DogbusterRegister.class);
                startActivity(intent);
            }
        });

        ImageView btnSearch= (ImageView) findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinner.setVisibility(View.VISIBLE);

                inputMail=(EditText)findViewById(R.id.txtMail);
                inputPwd=(EditText)findViewById(R.id.txtPassword);

                String res="";
                ServerInterface SI=new ServerInterface(getBaseContext(), new AsyncResponse() {
                    @Override
                    public void processFinish(String output) {

                        spinner.setVisibility(View.GONE);

                        if(output.contains("Success"))
                        {
                            //Login success
                            Toast.makeText(getBaseContext(), "Login Success",
                                    Toast.LENGTH_LONG).show();


                            Intent intent = new Intent(getBaseContext(), MainPanel.class);
                            startActivity(intent);

                        }
                        else
                        {
                            //Login failed
                            Toast.makeText(getBaseContext(), output,
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                },0);

                SI.execute(inputMail.getText().toString(),inputPwd.getText().toString());

            }
        });
    }

}
