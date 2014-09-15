package com.skv.alertsapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	AlertsAdapter adapter;
	DataService dataService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//oDataTest();
		ListView lv =(ListView)findViewById(R.id.lvAlerts);
		
		adapter = new AlertsAdapter(this,R.layout.alerts_listitem_row);
		 
		lv.setAdapter(adapter);
		adapter.setNotifyOnChange(true);
		dataService = new DataService(adapter);
			
			//dataService.doInBackground("Alert");
		dataService.execute("Alerts");	//1 - Alerts

	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
