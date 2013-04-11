package br.com.k19.android.cap07;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;




public class MainActivity extends Activity {
	private ProgressBar progress;
	private Button startButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		progress = (ProgressBar) findViewById(R.id.progress_bar);
		startButton = (Button) findViewById(R.id.start_button);
				
		startButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Runnable runnable = new Runnable() {
					@Override
					public void run() {
						for (int i = 1; i<= 10; i++) {
							final int value = i;
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									progress.setProgress(value);
								}
							});
						}
					}
				};
				new Thread(runnable).start();
			}
		});
	}
}