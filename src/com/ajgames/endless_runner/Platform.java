package com.ajgames.endless_runner;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

public class Platform extends PhysicsSprite
{
	public Platform( float x, float y, int width, int height, float xSpeed,
			World world )
	{
		super( x, y, width, height, world );
		this.createBox( BodyType.KINEMATIC );
		this.body.setLinearVelocity( new Vec2( xSpeed, 0 ) );
	}

}
