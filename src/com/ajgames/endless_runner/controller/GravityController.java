package com.ajgames.endless_runner.controller;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import com.ajgames.endless_runner.model.Physics;

/**
 * Controller for the worlds gravity vector. This controller allows for
 * differing jump heights for the player by reducing the y component of gravity.
 * 
 * @author Adam
 * 
 */
public class GravityController
{
	private static final String TAG = GravityController.class.getSimpleName();

	private static final float MIN_GRAVITY_MAG = 8.0f;
	private static final Vec2 GRAV_CHANGE_VEC = new Vec2( 0.0f, 1.5f );

	private boolean decreasing = false;
	private World world;
	private Vec2 gravityVec;

	public GravityController( World world )
	{
		this.world = world;
		gravityVec = new Vec2( Physics.DEFAULT_GRAVITY_VEC );
		setWorldGravity();
	}

	public void update()
	{
		if( this.allowDecrease() )
		{
			this.reduceGravity();
		}
		setWorldGravity();
	}

	public void startDecreasingGravity()
	{
		if( !this.decreasing )
		{
			this.decreasing = true;
			this.gravityVec.set( Physics.DEFAULT_GRAVITY_VEC );
		}
		setWorldGravity();
	}

	public void stopDecreasingGravity()
	{
		this.decreasing = false;
	}

	private boolean allowDecrease()
	{
		return this.decreasing && this.gravityVec.length() > MIN_GRAVITY_MAG;
	}

	private void reduceGravity()
	{
		this.gravityVec.subLocal( GRAV_CHANGE_VEC );
	}

	private void setWorldGravity()
	{
		this.world.setGravity( this.gravityVec );
	}

}
