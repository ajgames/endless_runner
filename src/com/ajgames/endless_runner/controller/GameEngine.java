package com.ajgames.endless_runner.controller;

import org.jbox2d.dynamics.World;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.ajgames.endless_runner.MainActivity;
import com.ajgames.endless_runner.MainThread;
import com.ajgames.endless_runner.callbacks.MainContactListener;
import com.ajgames.endless_runner.model.Physics;
import com.ajgames.endless_runner.model.Runner;
import com.ajgames.endless_runner.view.IRenderer;
import com.ajgames.endless_runner.view.RunningGameRenderer;
import com.example.endless_runner.R;


public class GameEngine extends SurfaceView implements
		SurfaceHolder.Callback
{
	private static final String TAG = GameEngine.class.getSimpleName();
	
	private String avgFps;
	
	private World world;
	private Runner runner;
	
	private IRenderer renderer;

	private GravityController gravityController;
	private PlatformController platformController;

	private MainThread mainThread;
	

	public GameEngine( Context context )
	{
		super( context );
		newGame();
		
	}

	public void update() 
	{
		this.world.step( 1.0f / 60.0f, 6, 2 );
		this.gravityController.update();
		this.platformController.update();
		this.runner.update();
		
		if( this.runner.getY() > getHeight() )
		{
			newGame();
		}
	} 

	public void render( Canvas canvas )
	{
		this.renderer.render( canvas );
		this.displayFps( canvas, avgFps );
	}
	
	@Override
	public void surfaceChanged( SurfaceHolder holder, int format, int width,
			int height )
	{

	}

	@Override
	public void surfaceCreated( SurfaceHolder arg0 )
	{
        if(!mainThread.isAlive()){
        	this.mainThread.setRunning( true );
        	this.mainThread.start();
        }
	}

	@Override
	public void surfaceDestroyed( SurfaceHolder arg0 )
	{
		// tell the thread to shut down and wait for it to finish
		// this is a clean shutdown
		boolean retry = true;
		while( retry )
		{
			try
			{
				if (mainThread != null)
					mainThread.join();
				retry = false;
			} catch( InterruptedException e )
			{
				// try again shutting down the thread
			}
		}
		Log.d( TAG, "Thread was shut down cleanly" );
	}

	@Override
	public boolean onTouchEvent( MotionEvent event )
	{
		int x = (int) event.getX();

		switch( event.getAction() )
		{
			case MotionEvent.ACTION_DOWN:
			{
				if( this.runner.allowJump() )
				{
					this.gravityController.startDecreasingGravity();
					this.runner.jump();
				}
				//move the guy left, right, or stop based on where on the screen was clicked
				//TODO this is not currently functional
				this.runner.setDirection(x);
				break;
			}
			case MotionEvent.ACTION_UP:
			{
				this.gravityController.stopDecreasingGravity();
			}
		}
		return true;
	}
	
	public void stopGame(){

		mainThread.setRunning( false );
		( (Activity) getContext() ).finish();
		
	}
	
	public void newGame(){
		// adding the callback (this) to the surface holder to intercept events
		this.getHolder().addCallback( this );

		// make the GamePanel focusable so it can handle events
		this.setFocusable( true );

		this.world = new World( Physics.DEFAULT_GRAVITY_VEC, Physics.DO_SLEEP );
		this.world.setContactListener( new MainContactListener() );
		
		this.runner = new Runner( 30.0f, 0.0f, this.world );
		
		this.gravityController = new GravityController( this.world );
		this.platformController = new PlatformController( this, this.runner, this.world );
		
		this.renderer = new RunningGameRenderer( this, this.platformController.platforms, this.runner );

		this.mainThread = new MainThread( this.getHolder(), this );
	}
	public void pauseGame(){
	}
	public void resumeGame(){
		//mainThread.setRunning( true );

	}
	
	public void setAvgFps( String avgFps )
	{
		this.avgFps = avgFps;
	}

	private void displayFps( Canvas canvas, String fps )
	{
		if( canvas != null && fps != null )
		{
			Paint paint = new Paint();
			paint.setARGB( 255, 255, 255, 255 );
			canvas.drawText( fps, this.getWidth() - 50, 20, paint );
		}
	}
	
}
