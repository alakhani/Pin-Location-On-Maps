package com.pervasive.googlemapsApp;
/* class that is just used to store the latitudes and the longitude*/
public class LocationCords 
{
	public LocationCords(double lat,double longit) 
	{
		this.latitude=lat;
		this.longitude=longit;
	}
	private double latitude;
	public double getLatitude()
	{
		return latitude;
	}
	public void setLatitude(double latitude) 
	{
		this.latitude = latitude;
	}
	private double longitude;
	public double getLongitude() 
	{
		return longitude;
	}
	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

}
