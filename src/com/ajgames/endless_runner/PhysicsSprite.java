package com.ajgames.endless_runner;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class PhysicsSprite extends Sprite
{
	protected Body body;
	protected BodyDef bodyDef;
	protected World world;

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
				Physics.DEFAULT_RESTITUTION );
	}

	public final void createBox( BodyType bodyType, float density,
			float friction, float restitution )
	{
		this.bodyDef = new BodyDef();
		this.bodyDef.type = bodyType;
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

	public void draw( Canvas canvas )
	{
		Paint paint = new Paint();
		Vec2 position = this.getPixelPosition();
		this.x = position.x;
		this.y = position.y;
		canvas.drawRect( new Rect( (int) this.x, (int) this.y, (int) this.x
				+ this.width, (int) this.y + this.height ), paint );
	}
}
