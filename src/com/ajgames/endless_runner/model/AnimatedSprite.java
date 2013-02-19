package com.ajgames.endless_runner.model;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class AnimatedSprite extends Sprite
{
	protected Rect sourceRect;
	protected int numFrames;
	protected int currentFrame;
	protected int framePeriod;
	private long frameTicker;

	public AnimatedSprite( Bitmap bitmap, int x, int y, int width, int height,
			int fps, int frameCount )
	{
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		this.currentFrame = 0;
		this.numFrames = frameCount;
		this.width = bitmap.getWidth() / frameCount;
		this.height = bitmap.getHeight();
		this.sourceRect = new Rect( 0, 0, this.width, this.height );
		this.framePeriod = 1000 / fps;
		this.frameTicker = 0l;
	}

	public void update( long gameTime )
	{
		if( gameTime > frameTicker + framePeriod )
		{
			this.frameTicker = gameTime;
			this.currentFrame++;
			if( this.currentFrame >= this.numFrames )
			{
				this.currentFrame = 0;
			}
		}
		this.sourceRect.left = this.currentFrame * this.width;
		this.sourceRect.right = this.sourceRect.left + this.width;
	}

	public void draw( Canvas canvas )
	{
		Rect destRect = new Rect( (int) this.x, (int) this.y,
				(int) ( this.x + this.width ), (int) ( this.y + this.height ) );
		canvas.drawBitmap( this.bitmap, sourceRect, destRect, null );
	}

}
