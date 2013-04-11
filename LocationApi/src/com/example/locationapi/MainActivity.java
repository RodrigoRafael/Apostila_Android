package com.example.locationapi;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements LocationListener{
	private static final String TAG = "MainActivity";
	
	private TextView latitudeText;
	private TextView longitudeText;
	private LocationManager locationManager;
	private String provider;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		latitudeText = (TextView) findViewById(R.id.latitude_text);
		longitudeText = (TextView) findViewById(R.id.longitude_text);
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		
		Location location = locationManager.getLastKnownLocation(provider);
		
		if(location != null){
			Log.d(TAG, "Provider" + provider + "foi selecionado");
			onLocationChanged(location);
		} else{
			latitudeText.setText(R.string.location_not_avaible);
			longitudeText.setText(R.string.location_not_avaible);
		}
		
	}
	
	protected void onPause(){
		super.onPause();
		locationManager.requestLocationUpdates(provider, 400, 1, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onLocationChanged(Location location) {
		double lat = location.getLatitude();
		double lng = location.getLongitude();
		latitudeText.setText(getString(R.string.point_label, lat));
		longitudeText.setText(getString(R.string.point_label, lng));
	}

	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(this, "Provider desabilitado" + provider, Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(this, "Novo provider" + provider, Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		
		
	}

}
