package com.skv.alertsapp;

import java.util.Timer;
import java.util.TimerTask;

import android.support.v7.app.ActionBarActivity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	AlertsAdapter adapter;
	DataService dataService;

	Timer timer;
	
	class myTimerTask extends TimerTask
	{

		@Override
		public void run() {
			try
			{

				 runOnUiThread(new Runnable() {
					  public void run() {
						dataService = new DataService(adapter);
						dataService.execute("Alerts");	//1 - Alerts
						
					  }
					});
							
		
			}catch(Exception ex)
			{
				Log.d("rTrack","Run Exception " + ex.getMessage());
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//oDataTest();
		ListView lv =(ListView)findViewById(R.id.lvAlerts);

		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
			
			adapter = new AlertsAdapter(this,R.layout.alerts_listitem_min_row);
		else
			adapter = new AlertsAdapter(this,R.layout.alerts_listitem_row);
		 
		lv.setAdapter(adapter);

		timer = new Timer();
		/*
		dataService = new DataService(adapter);
		
		dataService.execute("Alerts");	//1 - Alerts
		 */
		//delay 100 msec, every 10sec
		timer.schedule (new myTimerTask(), 100, 10000);
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

@Override
	
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);

		ListView lv =(ListView)findViewById(R.id.lvAlerts);

		try
		{
		if(timer != null)
		 {
			 timer.cancel();
		     timer.purge();
				
		 }
		}catch(Exception ex)
		{
			Log.d("rTrack", "Exception onConfigChange " + ex.getMessage());
		}
		timer = new Timer();
	
		LayoutInflater inflater = this.getLayoutInflater();
		lv.setAdapter(null);

	    // Checks the orientation of the screen
	    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

 	
	    	adapter = new AlertsAdapter(this,R.layout.alerts_listitem_row);
			Log.d("rTrack", "LandScaped");
				

	    } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){


	    	
			 adapter = new AlertsAdapterMini(this,R.layout.alerts_listitem_min_row);
				Log.d("rTrack", "PortRaited");
		    		
	    }


		lv.setAdapter(adapter);

		timer.schedule (new myTimerTask(), 100, 10000);
		Log.d("rTrack", "Aftr schedule");

	  }

}
