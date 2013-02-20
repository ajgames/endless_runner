package com.ajgames.endless_runner.view;

import java.util.Vector;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

import com.ajgames.endless_runner.controller.GameEngine;
import com.ajgames.endless_runner.model.Platform;
import com.ajgames.endless_runner.model.Runner;
import com.example.endless_runner.R;

public class RunningGameRenderer implements IRenderer
{
	private GameEngine gameEngine;
	private RunnerRenderer runnerRenderer;
	private PlatformRenderer platformRenderer;

	public RunningGameRenderer( GameEngine gameEngine,
			Vector< Platform > platforms, Runner runner )
	{
		this.gameEngine = gameEngine;
		this.runnerRenderer = new RunnerRenderer( runner,
				BitmapFactory.decodeResource( this.gameEngine.getResources(),
						R.drawable.droid ) );
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
