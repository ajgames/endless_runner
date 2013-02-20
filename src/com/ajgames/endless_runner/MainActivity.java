package com.ajgames.endless_runner;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.ajgames.endless_runner.controller.GameEngine;

public class MainActivity extends Activity
{

	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		//this is just a test!
		super.onCreate( savedInstanceState );
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN );
		setContentView( new GameEngine( this ) );
		Log.d( TAG, "View added" );
	}

	@Override
	protected void onDestroy()
	{
		Log.d( TAG, "Destroying.." );
		super.onDestroy();
	}

	@Override
	protected void onStop()
	{
		Log.d( TAG, "Stopping..." );
		super.onStop();
	}

}
