package br.com.k19.android.cap08;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;



public class MainActivity extends Activity {

	private Handler handler = new Handler() {
		public void handlerMessage(Message message) {
			Object path = message.obj;
			if (message.arg1 == RESULT_OK && path != null) {
				Toast.makeText(MainActivity.this,getString(R.string.download_error), Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(MainActivity.this,
						getString(R.string.download_error), Toast.LENGTH_LONG)
						.show();
			}
		};
	};
	
	@Override
	public void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button startButton = (Button) findViewById(R.id.start_button);
		startButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View V) {
				Intent intent = new Intent(MainActivity.this,
						DownloadService.class);
		Messenger messenger = new Messenger(handler);
		intent.putExtra("messenger", messenger);
		intent.setData(Uri.parse("cursos.html"));
		intent.putExtra("urlPath", "http://animetree.files.wordpress.com/2011/05/yu_yu_hakusho-cartoon-anime-manga.jpg");
		startService(intent);
			}
		});
	}
}