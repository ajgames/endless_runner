package com.ajgames.endless_runner.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import com.ajgames.endless_runner.controller.GameEngine;
import com.ajgames.endless_runner.model.Platform;
import com.ajgames.endless_runner.model.Runner;

public class RunningGameRenderer implements IRenderer
{	
	private GameEngine gameEngine;
	private RunnerRenderer runnerRenderer;
	private PlatformRenderer platformRenderer;
	
	public RunningGameRenderer( GameEngine gameEngine, Platform platforms[], Runner runner )
	{
		this.gameEngine = gameEngine;
		this.runnerRenderer = new RunnerRenderer( runner );
		this.platformRenderer = new PlatformRenderer( platforms );
	}

	@Override
	public void render( Canvas canvas )
	{
		canvas.drawColor( Color.GREEN );
		this.platformRenderer.render( canvas );
		this.runnerRenderer.render( canvas );
	}
	
}
