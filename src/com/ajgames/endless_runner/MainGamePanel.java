package com.ajgames.endless_runner;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainGamePanel extends SurfaceView implements
		SurfaceHolder.Callback
{

	private static final int EXPLOSION_SIZE = 100;
	private static final String TAG = MainGamePanel.class.getSimpleName();

	private Explosion[] explosions;
	private String avgFps;
	private MainThread mainThread;

	public MainGamePanel( Context context )
	{
		super( context );
		// adding the callback (this) to the surface holder to intercept events
		getHolder().addCallback( this );

		explosions = new Explosion[10];
		for( int i = 0; i < explosions.length; i++ )
			explosions[ i ] = null;

		mainThread = new MainThread( getHolder(), this );

		// make the GamePanel focusable so it can handle events
		setFocusable( true );

	}

	public void update()
	{
		for( int i = 0; i < explosions.length; i++ )
		{
			if( explosions[ i ] != null )
				explosions[ i ].update();
		}
	}

	@Override
	public void surfaceChanged( SurfaceHolder holder, int format, int width,
			int height )
	{

	}

	@Override
	public void surfaceCreated( SurfaceHolder arg0 )
	{
		mainThread.setRunning( true );
		mainThread.start();
	}

	@Override
	public void surfaceDestroyed( SurfaceHolder arg0 )
	{
		// tell the thread to shut down and wait for it to finish
		// this is a clean shutdown
		boolean retry = true;
		while ( retry )
		{
			try
			{
				mainThread.join();
				retry = false;
			} catch ( InterruptedException e )
			{
				// try again shutting down the thread
			}
		}
		Log.d( TAG, "Thread was shut down cleanly" );
	}

	@Override
	public boolean onTouchEvent( MotionEvent event )
	{
		int x = (int)event.getX();
		int y = (int)event.getY();
		
		if ( event.getAction() == MotionEvent.ACTION_DOWN )
		{
			if ( y > getHeight() - 50 )
			{
				mainThread.setRunning( false );
				( (Activity) getContext() ).finish();
			}
			else
			{
				int currentExplosion = 0;
				Explosion explosion = explosions[ currentExplosion ];
				while( currentExplosion < explosions.length - 1 && explosion != null && explosion.isAlive() )
				{
					currentExplosion++;
					explosion = explosions[ currentExplosion ];
				}
				if( ( explosion == null || explosion.isDead() ) )
				{
					explosion = new Explosion( EXPLOSION_SIZE, x, y );
					explosions[ currentExplosion ] = explosion;
				}
			}
		}
		return true;
	}

	public void setAvgFps( String avgFps )
	{
		this.avgFps = avgFps;
	}
	
	protected void render( Canvas canvas )
	{
		canvas.drawColor( Color.GREEN );
		displayFps( canvas, avgFps );
		for( int i = 0; i < explosions.length; i++ )
		{
			if( explosions[ i ] != null )
				explosions[ i ].draw( canvas );
		}
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
