package com.ajgames.endless_runner.view;

import java.util.Vector;

/**
 * 
 * Animator for all AnimatedSprites
 * @author Adam
 *
 */
public class Animator
{
	protected Vector< AnimatedSpriteRenderer > animatedSprites;
	protected long frameTicker;

	public Animator()
	{
		this.animatedSprites = new Vector< AnimatedSpriteRenderer >();
		this.frameTicker = 0l;
	}
	
	/**
	 * 
	 * @param animatedSprite - AnimatedSpriteRenderer to continue to update
	 */
	public void add( AnimatedSpriteRenderer animatedSprite )
	{
		this.animatedSprites.add( animatedSprite );
	}

	/**
	 * 
	 * @param animatedSprite - AnimatedSpriteRenderer to stop updating
	 */
	public void remove( AnimatedSpriteRenderer animatedSprite )
	{
		this.animatedSprites.remove( animatedSprite );
	}
	
	/**
	 * Update all of the held AnimatedSpriteRenderers
	 */
	public void update()
	{
		long gameTime = System.currentTimeMillis();
		for( int i = 0; i < this.animatedSprites.size(); i++ )
		{
			this.animatedSprites.get( i ).update( gameTime );
		}
	}
}
