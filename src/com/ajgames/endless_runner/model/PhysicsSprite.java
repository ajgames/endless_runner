package com.ajgames.endless_runner.model;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import android.graphics.Rect;

public class PhysicsSprite extends Sprite
{
	protected Body body;
	protected BodyDef bodyDef;
	protected World world;

	@Override
	public float getX()
	{
		return getPixelPosition().x;
	}

	@Override
	public float getY()
	{
		return getPixelPosition().y;
	}

	@Override
	public float getRotation()
	{
		return this.body.getAngle();
	}
	
	@Override
	public Rect getBounds()
	{
		return new Rect( (int) this.getX(), (int) this.getY(),
				(int) this.getX() + this.getWidth(), (int) this.getY()
						+ this.getHeight() );
	}

	public Vec2 getLinearVelocity()
	{
		return this.body.getLinearVelocity();
	}
	
	protected Vec2 getPhysicsPosition()
	{
		Vec2 position = new Vec2( ( this.x + this.width * 0.5f )
				/ Physics.DRAW_SCALE, ( this.y + this.height * 0.5f )
				/ Physics.DRAW_SCALE );
		return position;
	}

	protected Vec2 getPixelPosition()
	{
		Vec2 position = this.body.getPosition();
		return new Vec2( ( position.x * Physics.DRAW_SCALE ) - this.width
				* 0.5f, ( position.y * Physics.DRAW_SCALE ) - this.height
				* 0.5f );
	}

	public PhysicsSprite( float x, float y, int width, int height, World world )
	{
		super( x, y, width, height );
		this.world = world;
	}

	public void destroy()
	{
		this.world.destroyBody( this.body );
	}

	public final void createBox( BodyType bodyType )
	{
		createBox( bodyType, Physics.DEFAULT_DENSITY, Physics.DEFAULT_FRICTION,
				Physics.DEFAULT_RESTITUTION, true );
	}

	public final void createBox( BodyType bodyType, float density,
			float friction, float restitution, boolean fixedRotation )
	{
		this.bodyDef = new BodyDef();
		this.bodyDef.type = bodyType;
		this.bodyDef.fixedRotation = fixedRotation;
		Vec2 physicsPosition = getPhysicsPosition();
		this.bodyDef.position.set( physicsPosition.x, physicsPosition.y );

		this.body = this.world.createBody( bodyDef );

		PolygonShape shape = new PolygonShape();
		shape.setAsBox( ( width / Physics.DRAW_SCALE ) * 0.5f,
				( height / Physics.DRAW_SCALE ) * 0.5f );

		FixtureDef fd = new FixtureDef();
		fd.shape = shape;
		fd.density = density;
		fd.friction = friction;
		fd.restitution = restitution;

		body.createFixture( fd );
	}
}
