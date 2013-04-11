package com.example.notications;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button createNotification = (Button) findViewById(R.id.create_notification_button);
		createNotification.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent in = new Intent(MainActivity.this, NotificationActivity.class);
				PendingIntent penin = PendingIntent.getActivity(MainActivity.this, 0, in, 0);
				
				Notification notification = new Notification.Builder(MainActivity.this).
						setContentTitle(getString(R.string.new_notification)).
						setContentText(getString(R.string.notification_content)).
						setSmallIcon(R.drawable.ic_launcher).setContentIntent(penin).getNotification();
				notification.flags = Notification.FLAG_AUTO_CANCEL;
				
				NotificationManager notificationManager = (NotificationManager)
						getSystemService(NOTIFICATION_SERVICE);
				notificationManager.notify(0, notification);
			}
		});
	}

}
