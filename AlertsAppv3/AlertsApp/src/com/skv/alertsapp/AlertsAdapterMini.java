package com.skv.alertsapp;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AlertsAdapterMini extends AlertsAdapter {

	public AlertsAdapterMini(Context context, int resource) {
		super(context, resource);
		
	}
	@Override
	alertHolder FillHolder(View row)
	{
		alertHolder holder = new alertHolder();

		holder.alertId = (TextView)row.findViewById(R.id.txtId);

		holder.alertMsg = (TextView)row.findViewById(R.id.txtMessage);
        	 
        return holder;
	}
	@Override
	void displayFromHolder(alertHolder holder,AlertsData alert)
	{
		 try
		 {
			 String strId ="";

			 strId = String.format("%05d",alert.id);
			 holder.alertId.setText(strId);
		 }catch(Exception ex)
		 {
		   Log.d("rTrack", "Exception:setAlertId " + ex.getMessage());
			
		 }
		
		try
		 {

			holder.alertMsg.setText(alert.alertMessage);
		 }catch(Exception ex)
		 {
			   Log.d("rTrack", "Exception:setAlertMsg " + ex.getMessage());
				
		 }
	}

}
