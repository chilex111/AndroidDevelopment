package com.skv.alertsapp;

import java.util.List;

import org.apache.olingo.client.api.communication.request.retrieve.ODataEntitySetRequest;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.v4.ODataClient;
import org.apache.olingo.client.core.ODataClientFactory;
import org.apache.olingo.commons.api.domain.v4.ODataEntity;
import org.apache.olingo.commons.api.domain.v4.ODataEntitySet;
import org.apache.olingo.commons.api.format.ODataFormat;





import android.os.AsyncTask;
import android.util.Log;

public class DataService extends  AsyncTask<String, Void, Void> {

	AlertsData [] alerts;
	AlertsAdapter _adapter;

	String serviceUri;
	ODataClient odata;
		
	public DataService(AlertsAdapter a)
	{
		
		_adapter = a;
	}

	@Override
	protected Void doInBackground(String... arg0) {
	
		getData(arg0[0]);
		return null;		// TODO Auto-generated method stub

	}
	@Override
	 protected void onPostExecute(Void a)
	 {
		try
		{
				if(alerts != null)
				{
					_adapter.clear();
					 for(int i=0; i <alerts.length; i++)
					 {
			
						 _adapter.add(alerts[i]);

					 }
					 _adapter.notifyDataSetChanged();
					 

				}
				else
					Log.d("rTrack", "no alerts");
				
		}catch(Exception ex)
		{
			Log.d("rTrack", "Exception:"+ex.getMessage());
		}
				
	 }
	public void getData(String type)
	{
		int sz= 20; 	// maximum number of top N items we want to display
		
		serviceUri =	"http://192.168.1.101/Alerts";
		try
		{
			Log.d("getData","Before getV4");
			
			odata = ODataClientFactory.getV4();
			Log.d("getData","After getV4");
			
			odata.getConfiguration().setDefaultPubFormat(ODataFormat.JSON);
			
			Log.d("getData","Before readEntities");
			
			final List<ODataEntity> entities = readEntities(odata,serviceUri,type);
			Log.d("getData",String.format("entites.size=%d",  entities.size()));
			
			if(entities.size() < sz)
				sz =entities.size();
				
			alerts = new AlertsData[ sz];
				
			for( int i=0 ; i < sz; i++ )
			{
				try
				{
					ODataEntity alert = entities.get(i);


						alerts[i] = new AlertsData();
						alerts[i].id = Integer.parseInt(alert.getProperty("id").getValue().toString());
						alerts[i].alertMessage = alert.getProperty("alertMessage").getValue().toString();
						alerts[i].locationName = alert.getProperty("LocationName").getValue().toString();
						
						try
						{
							alerts[i].alertType =alert.getProperty("AlertType").getValue().toString();
						}catch(Exception e)
						{
							alerts[i].alertType ="";
						}
						try
						{
						
							alerts[i].alertSubType =alert.getProperty("AlertSubType").getValue().toString();
						}catch(Exception e)
						{
							alerts[i].alertSubType = "";
						}
						try
						{
						
							alerts[i].payload = alert.getProperty("Payload").getValue().toString();
						}catch(Exception e)
						{
							alerts[i].payload = "";
						}
						debugPrint(alerts[i]);
						
					}catch(Exception ex)
					{
						Log.d("rTrack", "Exception Row:"+ex.getMessage());
					}
				}

		}
		catch(Exception ex)
		{
			Log.d("rTrack","Connect:Exception:" + ex.getMessage());
		}

	}
	List<ODataEntity> readEntities(ODataClient odata, String uri, String esetname) {
		 
	    final ODataEntitySetRequest<ODataEntitySet> request = odata.getRetrieveRequestFactory()
	        .getEntitySetRequest(odata.newURIBuilder(uri)
	            .appendEntitySetSegment(esetname).build());

	    final ODataRetrieveResponse<ODataEntitySet> response = request.execute();

	    final ODataEntitySet entitySet = response.getBody();

	    List<ODataEntity> entities = entitySet.getEntities();

	    return entities;

	  }
	void debugPrint(AlertsData a)
	{
		Log.d("rTrack", String.format("%d,%s,%s,%s",a.id,a.alertType,a.alertSubType,a.alertMessage));
		
	}
}
