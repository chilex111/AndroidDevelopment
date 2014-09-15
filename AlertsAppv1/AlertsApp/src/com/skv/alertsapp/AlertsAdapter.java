package com.skv.alertsapp;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;


public class AlertsAdapter extends ArrayAdapter<AlertsData> {
	Context _context;
	int layoutResourceId;
	public AlertsAdapter(Context context, int resource) {
		super(context, resource);
		layoutResourceId = resource;
		_context = context;
	}

	//private static final long serialVersionUID = 1L;
	

	alertHolder holder = null;
	class alertHolder {
		  
		 public TextView alertId;	   
		 public TextView alertType;
		 public TextView alertSubType;
		 public TextView alertMsg;
		 public TextView payload;

	}

//	 ArrayList<AlertsData> data = null;
	 
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
	 View row = convertView;
	 
	 try
	   {
		   LayoutInflater inflater =null;
		 if(row == null)
	     {
			 
			 try
			 {
	             inflater = ((Activity)_context).getLayoutInflater();
	            
	            row = inflater.inflate(layoutResourceId, parent, false);
	            holder = FillHolder(row);
	         	row.setTag(holder);
			 }catch(Exception ex){
				 Log.d("rTrack", "Row==NULL , holder setting" );
			 }
	      }
	      else
	      {
	            holder = (alertHolder)row.getTag();

            
	      }
	        
	      AlertsData alert = getItem(position);
	   	  displayFromHolder(holder, alert);
	   	  
	   }catch(Exception ex)
	   {
		   Log.d("rTrack", "Exception:getView " + ex.getMessage());
			
	   }	 
	  return row;  

	}

	
	alertHolder FillHolder(View row)
	{
		alertHolder holder = new alertHolder();

		holder.alertId = (TextView)row.findViewById(R.id.txtId);

		holder.alertMsg = (TextView)row.findViewById(R.id.txtMessage);
        	 
        holder.payload = (TextView)row.findViewById(R.id.txtPayload);


        holder.alertType = (TextView)row.findViewById(R.id.txtType);


	    holder.alertSubType = (TextView)row.findViewById(R.id.txtSubType);
	    return holder;
	}
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
		
		 try
		 {
	   	        
			 holder.alertType.setText(alert.alertType);
		 }catch(Exception ex)
		 {
			   Log.d("rTrack", "Exception:setAlertType " + ex.getMessage());
				
		 }
		 try
		 {
	   	        
		        holder.alertSubType.setText(alert.alertSubType);
		 }catch(Exception ex)
		 {
			   Log.d("rTrack", "Exception:setAlertSubType " + ex.getMessage());
				
		 }

		 try
		 {
		
		        if(alert.payload != null)
		        	holder.payload.setText(alert.payload);
		        else
		        	holder.payload.setText("");
		        	
		        
		 }catch(Exception ex)
		 {
			   Log.d("rTrack", "Exception:setPayload " + ex.getMessage());
				
		 }
				

	}

}
