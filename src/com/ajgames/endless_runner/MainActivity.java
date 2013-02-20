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
	private GameEngine game;
	
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		//this is just a test!
		super.onCreate( savedInstanceState );
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN );
		game = new GameEngine( this ); 
		setContentView( game );
		
		
		Log.d( TAG, "View added" );
	}
	
	@Override
	public void onResume() {
	    super.onResume(); 
		Log.d( TAG, "Resuming.." );
	}   
	@Override
	public void onStart() {
	    super.onStart(); 
		Log.d( TAG, "Starting.." );
	}
	@Override
	public void onRestart() {
	    super.onRestart(); 
		Log.d( TAG, "Restarting.." );
	}
	@Override
	public void onPause() {
	    super.onPause(); 
	    game.stopGame();
		Log.d( TAG, "Pausing.." );
	}   	
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		Log.d( TAG, "Destroying.." );
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		Log.d( TAG, "Stopping..." );
	}

}
