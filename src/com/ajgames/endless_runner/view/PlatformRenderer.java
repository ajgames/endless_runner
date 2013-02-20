package com.ajgames.endless_runner.view;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.ajgames.endless_runner.model.Platform;

public class PlatformRenderer implements IRenderer
{
	private Platform[] platforms;

	public PlatformRenderer( Platform platforms[] )
	{
		this.platforms = platforms;
	}

	public void render( Canvas canvas )
	{
		for( int i = 0; i < platforms.length; i++ )
		{			
			Paint paint = new Paint();
			canvas.drawRect( this.platforms[ i ].getBounds(), paint );
		}
	}
}
