package com.ajgames.endless_runner;

import com.ajgames.endless_runner.controller.GameEngine;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class PlayGameActivity extends Activity{  
	
	private static final String TAG = PlayGameActivity.class.getSimpleName();
	
	private GameEngine game;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		//requestWindowFeature( Window.FEATURE_NO_TITLE );
		//getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
			//	WindowManager.LayoutParams.FLAG_FULLSCREEN );

		game = new GameEngine( this ); 
		setContentView( game );
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		Log.d( TAG, "Stopping..." );
		
        Log.d(TAG, "onStop screen state : "
                 +String.valueOf(this.hasWindowFocus()));
		//game.stopGame();
	}	
	@Override
	public void onStart() {
	    super.onStart(); 
		Log.d( TAG, "Starting.." );
	}
}