package com.appjar.dogbuster.dogbuster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class DogbusterRegister extends AppCompatActivity {

    private ProgressBar spinner;

    Button btnReg,btnCancelReg;
    EditText textFName,textLName,textAddress,textPhone,textPass,textCPass,textEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogbuster_register);

        spinner=(ProgressBar)findViewById(R.id.progressRegister);
        spinner.setVisibility(View.GONE);

        textFName=(EditText)findViewById(R.id.editFname);
        textLName=(EditText)findViewById(R.id.editLname);
        textAddress=(EditText)findViewById(R.id.editAddress);
        textPhone=(EditText)findViewById(R.id.editPhone);
        textPass=(EditText)findViewById(R.id.editPassword);
        textCPass=(EditText)findViewById(R.id.editCPassword);
        textEmail=(EditText)findViewById(R.id.editEmail);

        btnReg=(Button)findViewById(R.id.btnSignup);
        btnCancelReg=(Button)findViewById(R.id.btnCancelSignup);

        btnReg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                String res="";
                ServerInterface SI=new ServerInterface(getBaseContext(), new AsyncResponse() {
                    @Override
                    public void processFinish(String output) {

                        spinner.setVisibility(View.GONE);

                        Toast.makeText(getBaseContext(), output,
                                    Toast.LENGTH_LONG).show();

                        Log.d(output,"TEST REG");

                        finish();

                    }
                },5);

                String firstname=textFName.getText().toString();
                String lastname=textLName.getText().toString();
                String address=textAddress.getText().toString();
                String phone=textPhone.getText().toString();
                String mail_id=textEmail.getText().toString();
                String password=textPass.getText().toString();
                String cpassword=textCPass.getText().toString();

                if(firstname.isEmpty()||lastname.isEmpty()||address.isEmpty()||phone.isEmpty()||mail_id.isEmpty()||password.isEmpty()||cpassword.isEmpty())
                    Toast.makeText(getBaseContext(), "Please fill out all fields!",
                            Toast.LENGTH_LONG).show();
                else if(!password.equals(cpassword))
                    Toast.makeText(getBaseContext(), "Passwords do not match!",
                            Toast.LENGTH_LONG).show();
                else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(mail_id).matches())
                    Toast.makeText(getBaseContext(), "Enter valid mail id!",
                            Toast.LENGTH_LONG).show();
                else {
                    spinner.setVisibility(View.VISIBLE);
                    SI.execute(firstname, lastname, address, phone, mail_id, password, cpassword);
                }

            }
        });
    }
}
