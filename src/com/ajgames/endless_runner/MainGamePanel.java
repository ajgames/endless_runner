package com.ajgames.endless_runner;

import org.jbox2d.dynamics.World;

import android.app.Activity;
import android.content.Context;
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
	private static final String TAG = MainGamePanel.class.getSimpleName();

	private World world;
	private Runner runner;
	private PlatformRenderer platformRenderer;

	private String avgFps;
	private MainThread mainThread;

	public MainGamePanel( Context context )
	{
		super( context );
		// adding the callback (this) to the surface holder to intercept events
		getHolder().addCallback( this );

		// make the GamePanel focusable so it can handle events
		setFocusable( true );

		world = new World( Physics.GRAVITY_VEC, Physics.DO_SLEEP );

		this.runner = new Runner( world );
		this.platformRenderer = new PlatformRenderer( world );

		mainThread = new MainThread( getHolder(), this );
	}

	public void update()
	{
		world.step( 1.0f / 60.0f, 6, 2 );
		this.runner.update();
		this.platformRenderer.update();
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
		while( retry )
		{
			try
			{
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
		int y = (int) event.getY();

		if( event.getAction() == MotionEvent.ACTION_DOWN )
		{
			if( y > getHeight() - 50 )
			{
				mainThread.setRunning( false );
				( (Activity) getContext() ).finish();
			}
			this.runner.jump();
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
		platformRenderer.draw( canvas );
		runner.draw( canvas );
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
