package com.ajgames.endless_runner.view;

import java.util.Vector;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.ajgames.endless_runner.model.Platform;

public class PlatformRenderer implements IRenderer
{
	private Vector< Platform > platforms;

	public PlatformRenderer( Vector< Platform > platforms )
	{
		this.platforms = platforms;
	}

	public void render( Canvas canvas )
	{
		for( int i = 0; i < platforms.size(); i++ )
		{
			Paint paint = new Paint();
			canvas.drawRect( this.platforms.get( i ).getBounds(), paint );
		}
	}
}
