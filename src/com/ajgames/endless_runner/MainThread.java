package com.ajgames.endless_runner;

import java.text.DecimalFormat;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import com.ajgames.endless_runner.controller.GameEngine;

public class MainThread extends Thread
{
	private static final String TAG = MainThread.class.getSimpleName();

	public static final int MAX_FPS = 60;
	private static final int MAX_FRAME_SKIPS = 5;
	private static final int FRAME_PERIOD = 1000 / MAX_FPS;

	private DecimalFormat df = new DecimalFormat( "0.##" );
	private final static int STAT_INTERVAL = 1000;
	private static final int FPS_HISTORY_NR = 10;
	private long lastStatusStore = 0;
	private long statusIntervalTimer = 0l;
	private long totalFramesSkipped = 0l;
	private long framesSkippedPerStatCycle = 0l;

	private int frameCountPerStatCycle = 0;
	private long totalFrameCount = 0l;
	private double fpsStore[];
	private long statsCount = 0;
	private double averageFps = 0.0;

	private SurfaceHolder surfaceHolder;
	private GameEngine gameEngine;

	// flag to hold game state
	private boolean running;

	public void setRunning( boolean running )
	{
		this.running = running;
	}

	public MainThread( SurfaceHolder surfaceHolder, GameEngine gameEngine )
	{
		super();
		this.surfaceHolder = surfaceHolder;
		this.gameEngine = gameEngine;
	}

	@Override
	public void run()
	{
		initTimingElements();

		Canvas canvas;
		long tickCount = 0L;
		long beginTime;
		long timeDiff;
		int sleepTime = 0;
		int framesSkipped;

		Log.d( TAG, "Starting game loop" );

		while( running )
		{
			canvas = null;
			try
			{
				canvas = this.surfaceHolder.lockCanvas();
				synchronized( surfaceHolder )
				{
					beginTime = System.currentTimeMillis();
					framesSkipped = 0;

					this.gameEngine.update();
					this.gameEngine.render( canvas );

					timeDiff = System.currentTimeMillis() - beginTime;
					sleepTime = (int) ( FRAME_PERIOD - timeDiff );

					if( sleepTime > 0 )
					{
						try
						{
							Thread.sleep( sleepTime );
						} catch( InterruptedException e )
						{
						}
					}

					while( sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS )
					{
						this.gameEngine.update();
						sleepTime += FRAME_PERIOD;
						framesSkipped++;
					}

					if( framesSkipped > 0 )
					{
						Log.d( TAG, "Skipped: " + framesSkipped );
					}

					framesSkippedPerStatCycle += framesSkipped;
					storeStats();
				}
			} finally
			{
				if( canvas != null )
				{
					surfaceHolder.unlockCanvasAndPost( canvas );
				}

			}
			tickCount++;
			// update game state
			// render state to the screen
		}
		Log.d( TAG, "Game loop executed " + tickCount + " times" );
	}

	private void storeStats()
	{
		frameCountPerStatCycle++;
		totalFrameCount++;

		statusIntervalTimer += ( System.currentTimeMillis() - statusIntervalTimer );

		if( statusIntervalTimer >= lastStatusStore + STAT_INTERVAL )
		{
			double actualFps = (double) ( frameCountPerStatCycle / ( STAT_INTERVAL / 1000 ) );

			fpsStore[ (int) statsCount % FPS_HISTORY_NR ] = actualFps;

			statsCount++;

			double totalFps = 0.0;
			for( int i = 0; i < FPS_HISTORY_NR; i++ )
			{
				totalFps += fpsStore[ i ];
			}

			if( statsCount < FPS_HISTORY_NR )
			{
				averageFps = totalFps / statsCount;
			} else
			{
				averageFps = totalFps / FPS_HISTORY_NR;
			}

			totalFramesSkipped += framesSkippedPerStatCycle;
			framesSkippedPerStatCycle = 0;
			statusIntervalTimer = 0;
			frameCountPerStatCycle = 0;

			statusIntervalTimer = System.currentTimeMillis();
			lastStatusStore = statusIntervalTimer;
			
			gameEngine.setAvgFps("FPS: " + df.format(averageFps));
		}
	}

	private void initTimingElements()
	{
		fpsStore = new double[FPS_HISTORY_NR];
		for( int i = 0; i < FPS_HISTORY_NR; i++ )
		{
			fpsStore[ i ] = 0.0;
		}
	}
}
