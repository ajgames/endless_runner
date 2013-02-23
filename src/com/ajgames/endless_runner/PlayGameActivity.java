package com.ajgames.endless_runner;

import com.ajgames.endless_runner.controller.GameEngine;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class PlayGameActivity extends Activity{  
	
	private GameEngine game;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN );

		game = new GameEngine( PlayGameActivity.this ); 
		setContentView( game );
	}
}
