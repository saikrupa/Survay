package com.testing.survey;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class WelcomeScreen extends Activity{

	int SPLASH_TIME_OUT=3000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcomescreen);
		splash();
	}
	public void splash() {

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {

				// dialog.dismiss();
				Intent i = new Intent(getApplicationContext(),LoginScreen.class);
				startActivity(i);
				finish();
			}
		}, SPLASH_TIME_OUT);

	}

}
