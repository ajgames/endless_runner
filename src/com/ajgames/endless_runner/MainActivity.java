package com.ajgames.endless_runner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;


import com.ajgames.endless_runner.controller.GameEngine;
import com.example.endless_runner.R;

public class MainActivity extends Activity
{

	private static final String TAG = MainActivity.class.getSimpleName();
	
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		

		setContentView( R.layout.activity_main );

		Intent intent = new Intent(MainActivity.this, PlayGameActivity.class);
	    startActivity(intent);
		//displayAlert();
		
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
	private void displayAlert(){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				this);

			// set title
			alertDialogBuilder.setTitle("You Died!");

			// set dialog message
			alertDialogBuilder
				.setMessage("Retry?!")
				.setCancelable(false)
				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, close
						// current activity
					}
				  })
				.setNegativeButton("No",new DialogInterface.OnClickListener() {
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
