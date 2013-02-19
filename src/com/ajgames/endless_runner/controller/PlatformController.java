package com.ajgames.endless_runner.controller;

import java.util.Random;

import org.jbox2d.dynamics.World;

import com.ajgames.endless_runner.model.Platform;

public class PlatformController
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
	
	public Platform platforms[];
	
	private Random random;
	private World world;
	
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
		if( platform.getLinearVelocity().x < 0
				&& platform.getX() + platform.getWidth() <= 0 )
			return true;
		else if( platform.getLinearVelocity().x > 0 && platform.getX() >= 350 )
			return true;
		else
			return false;
	}
	
	public PlatformController( World world )
	{
		this.world = world;
		this.platforms = new Platform[ NUM_PLATFORMS ];
		this.random = new Random();
		this.createPlatforms( world );
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

}
