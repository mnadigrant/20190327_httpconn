package com.example.a20190327_httpconn;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class ServerLogin extends AsyncTask<Void,Void,Void> {

    String username, password;
    Activity loginActivity;

    String newUsername, newPassword, email, userId;

    String message;

    String strurl = "http://192.168.31.212:5000/todo/api/v1.0/users/";
    String data = "";

    public ServerLogin(String username, String password, Activity activity){
        this.username = username;
        this.password = password;
        loginActivity = activity;

    }

    @Override
    protected Void doInBackground(Void... voids) {

       String strnewurl = strurl + username + " && " + password;
        try {
            URL url = new URL(strnewurl);




            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = "";
            while((line = bufferedReader.readLine()) != null){

                sb.append(line + "\n");

            }

            data = sb.toString();

            JSONObject JO = new JSONObject(data);

            message = JO.getString("message");

                Log.i("list", message);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);


        if (message.equals("User Login Successful")){
            Intent intent = new Intent(loginActivity, MainActivity.class);
            loginActivity.startActivity(intent);

        } else {
            Toast.makeText(loginActivity,"Login Failed",Toast.LENGTH_SHORT).show();
        }

    }
}


