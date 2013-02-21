package com.ajgames.endless_runner.view;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.ajgames.endless_runner.model.Sprite;

/**
 * 
 * Takes a sprite sheet and chops it into an animated sprite
 * @author Adam
 *
 */
public class AnimatedSpriteRenderer extends BitmapRenderer
{
	protected Animator animator;
	protected int numFrames;
	protected int numRows;
	protected int numCols;
	protected int currentFrame;
	protected int framePeriod;
	private long frameTicker;

	/**
	 * @param sprite - Model sprite for this AnimatedSprite
	 * @param bitmap - SpriteSheet Bitmap to draw
	 * @param animator - Animator to step all of the animations
	 * @param cols - Number of columns in the sprite sheet
	 * @param rows - number of rows in the sprite sheet
	 * @param fps - Frames per second to display the animation
	 * @param frameCount - Total number of frames in animation
	 */
	public AnimatedSpriteRenderer( Sprite sprite, Bitmap bitmap,
			Animator animator, int cols, int rows, int fps, int frameCount )
	{
		super( sprite, bitmap );
		this.animator = animator;
		this.currentFrame = 0;
		this.numFrames = frameCount;
		this.numRows = rows;
		this.numCols = cols;
		this.width = bitmap.getWidth() / this.numCols;
		this.height = bitmap.getHeight() / this.numRows;
		this.sourceRect = new Rect( 0, 0, this.width, this.height );
		this.framePeriod = 1000 / fps;
		this.frameTicker = 0l;

		this.animator.add( this );
	}

	@Override
	public void destroy()
	{
		super.destroy();
		this.animator.remove( this );
	}

	/**
	 * Checks to see if the currentFrame has changed and updates the sourceRect
	 * @param gameTime - current game time in milliseconds
	 */
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
		this.sourceRect.left = ( this.currentFrame % this.numCols ) * this.width;
		this.sourceRect.right = this.sourceRect.left + this.width;
		this.sourceRect.top = ( this.currentFrame / this.numCols ) * this.height;
		this.sourceRect.bottom = this.sourceRect.top + this.height;
	}

}
