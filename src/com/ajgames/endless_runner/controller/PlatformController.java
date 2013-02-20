package com.ajgames.endless_runner.controller;

import java.util.Random;
import java.util.Vector;

import org.jbox2d.dynamics.World;

import com.ajgames.endless_runner.model.Platform;
import com.ajgames.endless_runner.model.Runner;

public class PlatformController
{
	private static final float MAX_DIST_BETW_PLATFORMS = 100.0f;
	private static final float MAX_DIST_FROM_SCREEN_BOTTOM = 100.0f;
	private static final int MIN_PLATFORM_WIDTH = 100;
	private static final int MAX_PLATFORM_WIDTH = 300;
	private static final int MIN_PLATFORM_HEIGHT = 10;
	private static final int MAX_PLATFORM_HEIGHT = 2;
	private static final int START_PLATFORM_WIDTH = 500;
	private static final int START_PLATFORM_HEIGHT = 20;

	public Vector< Platform > platforms;

	private Random random;
	private Runner runner;
	private World world;
	private GameEngine gameEngine;

	public float getRandomX()
	{
		return gameEngine.getWidth() + random.nextFloat() * MAX_DIST_BETW_PLATFORMS;
	}

	public float getRandomY()
	{
		return gameEngine.getHeight() - random.nextFloat() * MAX_DIST_FROM_SCREEN_BOTTOM;
	}

	public int getRandomWidth()
	{
		return random.nextInt( MAX_PLATFORM_WIDTH ) + MIN_PLATFORM_WIDTH;
	}

	protected int getRandomHeight()
	{
		return random.nextInt( MAX_PLATFORM_HEIGHT ) + MIN_PLATFORM_HEIGHT;
	}

	private boolean isOffScreen( Platform platform )
	{
		if( platform.getLinearVelocity().x < 0
				&& platform.getX() + platform.getWidth() <= 0 )
			return true;
		else if( platform.getLinearVelocity().x > 0 && platform.getX() >= 350 )
			return true;
		else
			return false;
	}

	private boolean isOnScreen( Platform platform )
	{
		if( platform.getLinearVelocity().x < 0
				&& platform.getX() + platform.getWidth() < gameEngine
						.getWidth() )
			return true;
		else if( platform.getLinearVelocity().x > 0 && platform.getX() > 0.0f )
			return true;
		else
			return false;
	}

	public PlatformController( GameEngine gameEngine, Runner runner, World world )
	{
		this.gameEngine = gameEngine;
		this.runner = runner;
		this.world = world;
		this.platforms = new Vector< Platform >();
		this.random = new Random();
		this.createFirstPlatform( world );
	}

	public void update()
	{
		checkFirstPlatformRemoval();
		checkAddNextPlatform();
	}

	private void createFirstPlatform( World world )
	{
		this.platforms.add( new Platform( 0.0f, 200.0f, START_PLATFORM_WIDTH,
				START_PLATFORM_HEIGHT, this.runner.speed, this.world ) );
	}

	private Platform createRandomPlatform()
	{
		return new Platform( this.getRandomX(), this.getRandomY(),
				this.getRandomWidth(), this.getRandomHeight(),
				this.runner.speed, this.world );
	}

	private void checkFirstPlatformRemoval()
	{
		Platform platform = this.platforms.firstElement();
		if( isOffScreen( platform ) )
		{
			platform.destroy();
			this.platforms.remove( 0 );
		}
	}

	private void checkAddNextPlatform()
	{
		Platform platform = this.platforms.lastElement();
		if( isOnScreen( platform ) )
		{
			this.platforms.add( createRandomPlatform() );
		}
	}

}
