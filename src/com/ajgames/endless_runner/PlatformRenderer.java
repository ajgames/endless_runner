package com.ajgames.endless_runner;

import java.util.Random;

import org.jbox2d.dynamics.World;

import android.graphics.Canvas;

public class PlatformRenderer
{
	private static final float MIN_SPEED = 4.0f;
	private static final float MAX_SPEED = 5.0f;
	private static final float MIN_START_Y = 0.0f;
	private static final float MAX_START_Y = 400.0f;
	private static final float MAX_START_X = 400.0f;
	private static final float MIN_START_X = 0.0f;
	private static final int MIN_PLATFORM_WIDTH = 10;
	private static final int MAX_PLATFORM_WIDTH = 100;
	private static final int MIN_PLATFORM_HEIGHT = 10;
	private static final int MAX_PLATFORM_HEIGHT = 2;
	private static final int NUM_PLATFORMS = 10;

	private World world;
	private Random random;
	private Platform[] platforms;

	public float getRandomX()
	{
		return random.nextFloat() * ( MAX_START_X - MIN_START_X ) + MIN_START_X;
	}

	public float getRandomY()
	{
		return random.nextFloat() * ( MAX_START_Y - MIN_START_Y ) + MIN_START_Y;
	}

	public int getRandomWidth()
	{
		return random.nextInt( MAX_PLATFORM_WIDTH ) + MIN_PLATFORM_WIDTH;
	}

	protected int getRandomHeight()
	{
		return random.nextInt( MAX_PLATFORM_HEIGHT ) + MIN_PLATFORM_HEIGHT;
	}

	protected float getRandomSpeed()
	{
		float speed = ( random.nextFloat() * MAX_SPEED - MIN_SPEED )
				+ MIN_SPEED;
		if( random.nextFloat() < 0.5 )
			return speed;
		else
			return -speed;
	}

	protected boolean isOffScreen( Platform platform )
	{
		if( platform.body.getLinearVelocity().x < 0
				&& platform.x + platform.width <= 0 )
			return true;
		else if( platform.body.getLinearVelocity().x > 0 && platform.x >= 350 )
			return true;
		else
			return false;
	}

	public PlatformRenderer( World world )
	{
		this.world = world;
		this.random = new Random();
		this.platforms = new Platform[NUM_PLATFORMS];
		this.createPlatforms( world );
	}

	private void createPlatforms( World world )
	{
		for( int i = 0; i < NUM_PLATFORMS; i++ )
		{
			this.platforms[ i ] = createPlatform( world );
		}
	}

	private Platform createPlatform( World world )
	{
		return new Platform( this.getRandomX(), this.getRandomY(),
				this.getRandomWidth(), this.getRandomHeight(),
				this.getRandomSpeed(), world );
	}

	public void update()
	{
		Platform platform;
		for( int i = 0; i < NUM_PLATFORMS; i++ )
		{
			platform = this.platforms[ i ];
			if( isOffScreen( platform ) )
			{
				platform.destroy();
				platform = createPlatform( this.world );
				platforms[ i ] = platform;
			}
		}
	}

	public void draw( Canvas canvas )
	{
		for( int i = 0; i < NUM_PLATFORMS; i++ )
		{
			this.platforms[ i ].draw( canvas );
		}
	}
}
