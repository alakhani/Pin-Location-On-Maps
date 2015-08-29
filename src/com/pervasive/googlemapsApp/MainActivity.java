package com.pervasive.googlemapsApp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity implements LocationListener {

	// Google Map
	private GoogleMap googleMap;

	//location manager object to extract location
	LocationManager lm;
	
	//Arraylist to store all the location coordinates to pin
	private ArrayList<LocationCords>locationPins=new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		try {
			// Loading map initially
			initilizeMap();

		} catch (Exception e) {
			e.printStackTrace();
		}

		//get the latitude and longitude data
		lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		// Creating an empty criteria object
		Criteria criteria = new Criteria();

		// Get name of the provider in this case which would be gps
		String provider = lm.getBestProvider(criteria, false);

		if(provider!=null && !provider.equals(""))
		{ 
			// Get the location
			Location location = lm.getLastKnownLocation(provider);
			//set frequency of location update parameters
			lm.requestLocationUpdates(provider, 20000, 1, this);

			if(location!=null)
				onLocationChanged(location);

		}
		else
		{
			//create a toast indicating error.
			Toast.makeText(getBaseContext(), "OOPS something went wrong", Toast.LENGTH_SHORT).show();

		}


	}


	private void initilizeMap() {
		//get map fragment
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();

			// check if theres an issue with the initialization of the object
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		//on resume just initialize and update the pins
		initilizeMap();
		updatePins();
	}

	@Override
	public void onLocationChanged(Location location) 
	{
		// latitude and longitude
		double latitude = location.getLatitude();//17.385044;
		double longitude =location.getLongitude(); //78.486671;
		//when location is changed just add the latitude and the longitude values to the list
		locationPins.add(new LocationCords(latitude, longitude));
		updatePins();

	}

	public void updatePins()
	{
		int count=1;
		for(LocationCords loc:locationPins)
		{
			// create marker
			MarkerOptions marker = new MarkerOptions().position(new LatLng(loc.getLatitude(), loc.getLongitude())).title("Marker"+count++);
			// adding marker to the map
			googleMap.addMarker(marker);

		}
	}

	@Override
	public void onProviderDisabled(String provider) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void onProviderEnabled(String provider) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) 
	{
		// TODO Auto-generated method stub
	}
}