package br.com.k19.android.animation;

import android.os.Bundle;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void startAnimation(View view) {
		float dest=0;
		ImageView aniView = (ImageView) findViewById(R.id.imageView1);
		if(view.getId() == R.id.Button01){
			dest = 360;
			if (aniView.getRotation() == 360) {
				System.out.println(aniView.getAlpha());
				dest = 0;
			}
			ObjectAnimator animation1 = ObjectAnimator.ofFloat(aniView,
					"rotation", dest);
			animation1.setDuration(2000);
			animation1.start();
		}
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

}
