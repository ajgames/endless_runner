package com.ajgames.endless_runner;

import com.ajgames.endless_runner.controller.GameEngine;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

	    //displayAlert();

		game = new GameEngine( this ); 
		game.newGame();
		setContentView( game );
		//setContentView( new GameEngine( this ) );
	    
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
	private void displayAlert(){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				this);

			// set title
			alertDialogBuilder.setTitle("You Died!");

			// set dialog message
			alertDialogBuilder
				.setMessage("Back to Menu?")
				.setCancelable(false)
				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, close
						// current activity

						Intent intent = new Intent(PlayGameActivity.this, MainActivity.class);
					    startActivity(intent);
						dialog.cancel();
					}
				  })
				.setNegativeButton("No, play again",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
					}
				});

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();
	}
}