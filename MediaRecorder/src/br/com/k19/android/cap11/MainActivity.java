package br.com.k19.android.cap11;

import java.io.File;
import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private final String LOG_TAG = "MainActivity";
	
	private MediaRecorder mRecorder;
	private boolean playing;
	Button reproduzirButton;
	
	private MediaPlayer mPlayer;
	private boolean recording;
	Button gravarButton;
	
	private String mFileName;
	
	private void onPlay() {
		if (playing){
			reproduzirButton.setText("Iniciar execucao");
			mPlayer.stop();
				mPlayer.release();
				mPlayer = null;
		}
		else{
			try{
				reproduzirButton.setText("parar execucao");
				mPlayer = new MediaPlayer();
				
				mPlayer.setOnCompletionListener( new OnCompletionListener() {
					public void onCompletion(MediaPlayer mp) {
						playing = true;
						onPlay();
					}
				});
				mPlayer.setDataSource(mFileName);
				mPlayer.prepare();
				mPlayer.start();
			}
			catch (Exception e){
				Log.e(LOG_TAG, e.getMessage());
			}
		}
		playing = ! playing;
	}
	private void onRecord() {
		if (recording) {
			gravarButton.setText("Iniciar gravacao");
			mRecorder.stop();
				mRecorder.release();
				mRecorder = null;
		}
		else {
			try{
				mRecorder = new MediaRecorder();
				mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
				mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
				mRecorder.setOutputFile(mFileName);
				mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
					mRecorder.prepare();
					mRecorder.start();
					gravarButton.setText("Parar gravacao");
			} catch(IOException e){
				Log.e(LOG_TAG, e.getMessage());
			}
		}
		recording = !recording;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		String filepath = Environment.getExternalStorageDirectory().getPath();
		File file = new File(filepath, "RecordFolder");
		if (!file.exists()){
			file.mkdirs();
		}
		mFileName = file.getAbsolutePath() + "/lastRecordedFile.3gp";
		
		recording = false;
		setContentView(R.layout.activity_main);
		gravarButton = (Button) findViewById(R.id.button1);
		reproduzirButton = (Button) findViewById(R.id.button2);
		
		gravarButton.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				onRecord();
			}
		});
		reproduzirButton.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				onPlay();
			}
		});
	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		return super.onMenuItemSelected(featureId,  item);
	}
}