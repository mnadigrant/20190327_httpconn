package com.example.a20190327_httpconn;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.util.Log;


public class fetchData extends AsyncTask<Void,Void,Void> {
    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    private RecyclerView muserRecyclerView;
    private List<User> muserData = new ArrayList<>();

    String id;
    String description;
    String title;
    String done;

    Activity mainActivity;

    public fetchData(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("http://192.168.31.212:5000/todo/api/v1.0/tasks");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
            JSONArray JA = new JSONArray(data);
            for(int i =0 ; i <JA.length();  i++){
                JSONObject JO = (JSONObject) JA.get(i);

                id = JO.getString("id");
                description = JO.getString("description");
                title = JO.getString("title");
                done = JO.getString("done");


                Tasks g_task = new Tasks(description,done,title,id);
                Tasks.addToTaskList(g_task);

                Log.i("list", title);
            }
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



        Intent intent = new Intent(mainActivity, MainActivity.class);
        mainActivity.startActivity(intent);

        Log.i("list", title + description + done);
    }
}
