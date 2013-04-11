package br.com.k19.android.animation;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {
	
	private SensorManager mSensorManager;
	private Sensor mSensor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mSensorManager.registerListener((SensorEventListener) this, mSensor, SensorManager
				.SENSOR_DELAY_NORMAL);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void onAccuracyChanged(Sensor arg0, int arg1) {

}
	public void onSensorChanged(SensorEvent ev){
		
		TextView x = (TextView) findViewById(R.id.textView1);
		TextView y = (TextView) findViewById(R.id.textView2);
		TextView z = (TextView) findViewById(R.id.textView3);
		
		x.setText(String.valueOf(ev.values[0]));
		y.setText(String.valueOf(ev.values[1]));
		z.setText(String.valueOf(ev.values[2]));
	}
}