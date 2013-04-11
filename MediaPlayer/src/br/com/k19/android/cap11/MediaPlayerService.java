package br.com.k19.android.cap11;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.IBinder;
import android.widget.Toast;

public class MediaPlayerService extends Service implements OnPreparedListener, 
	OnErrorListener {
	
	private MediaPlayer mMediaPlayer;
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "Iniciando o servico", Toast.LENGTH_SHORT).show();
		
		try {
			mMediaPlayer = new MediaPlayer();
			Uri path = Uri.parse("android.resource://br.com.k19.android.cap11/" + R.raw.sample);
			mMediaPlayer.setDataSource(getApplicationContext(), path);
			mMediaPlayer.setOnPreparedListener(this);
			mMediaPlayer.prepareAsync();
		}
		catch (IOException e){
			// recurso não encontrado
		}
		
		return super.onStartCommand(intent,  flags, startId);
	}
	@Override
	public void onDestroy(){
		super.onDestroy();
		Toast.makeText(this, "Terminando o servico", Toast.LENGTH_SHORT).show();
		if (mMediaPlayer != null){
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
	}
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	public void onPrepared(MediaPlayer mp) {
		mMediaPlayer.start();
	}
	public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
		// tratamento de erros
		return false;
	}
}