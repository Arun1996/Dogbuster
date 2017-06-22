package com.dogbuster.appjar.dogbuster;

/**
 * Created by HP on 18-03-2017.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Ascalonic on 2/11/2017.
 */


public class ServerInterface extends AsyncTask<String, Void, String> {


    private TextView statusField,roleField;
    String resp;
    private Context cont;
    private int myflag;
    private int byGetOrPost = 0;

    public AsyncResponse del;

    public ServerInterface(Context context,AsyncResponse mydel,int flag) {
        this.cont = context;
        this.del=mydel;
        resp=new String("");
        this.myflag=flag;
    }


    protected void onPreExecute(){
    }

    @Override
    protected String doInBackground(String... arg0) {

        String msg="";

        if(myflag==0)
        {
            try {
                String mail_id = (String) arg0[0];
                String password = (String) arg0[1];

                String link = "http://dogbuster.in/login.php";

                String data = URLEncoder.encode("mail_id", "UTF-8") + "=" +
                        URLEncoder.encode(mail_id, "UTF-8");

                data+="&"+URLEncoder.encode("pwd", "UTF-8") + "=" +
                        URLEncoder.encode(password, "UTF-8");

                Log.d(data,"CONN_TEST");

                URL url = new URL(link);

                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write(data);
                wr.flush();

                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                msg=sb.toString();
            } catch (Exception e) {

                msg= new String("Exception: " + e.getMessage());
            }
        }
        else if(myflag==1)
        {

        }

        return msg;

    }

    @Override
    protected void onPostExecute(String result){
        del.processFinish(result);
    }
}