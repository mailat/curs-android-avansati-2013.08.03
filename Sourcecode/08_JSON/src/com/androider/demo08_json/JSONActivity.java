package com.androider.demo08_json;

import java.util.ArrayList;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class JSONActivity extends ListActivity {

	ArrayList<String> valuesAvailable = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//seteaza titlul activitatii
		setTitle("Mesajele din Twitter via JSON.");		
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, valuesAvailable);
		setListAdapter(adapter);			
		
		new TwitterTimelineReader().execute("https://api.twitter.com/1/statuses/user_timeline/ed.json");
	}

    private class TwitterTimelineReader extends AsyncTask<String, Void, Void> {
    	
        private final HttpClient client = new DefaultHttpClient();
        private String response;
        private ProgressDialog progressDialog = new ProgressDialog(JSONActivity.this);
        
        protected void onPreExecute() {
        	progressDialog.setMessage("Descarcam mesajele Twitter ...");
        	progressDialog.show();
        }

        protected Void doInBackground(String... urls) {
            try {
                HttpGet httpget = new HttpGet(urls[0]);
                response = client.execute(httpget, new BasicResponseHandler());
            } catch (Throwable e) {
                cancel(true);
            }
            
            return null;
        }
        
        protected void onPostExecute(Void unused) {
        	//adaugam tweeturile in lista de pe ecran
    		try {
    			JSONArray jsonArray = new JSONArray(response);
    			for (int i = 0; i < jsonArray.length(); i++) {
    				JSONObject jsonObject = jsonArray.getJSONObject(i);
    				adapter.add(jsonObject.getString("text"));
    			}
    		} catch (Throwable e) {
    			//reactioneaza
    		}

        	//inchidem dialogul 
        	progressDialog.dismiss();
        }
    }
}