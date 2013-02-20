package com.ajgames.endless_runner.callbacks;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.contacts.Contact;

import com.ajgames.endless_runner.model.PhysicsSprite;

public class MainContactListener implements ContactListener
{

	public MainContactListener()
	{
	}

	@Override
	public void beginContact( Contact contact )
	{
		Body body1 = contact.getFixtureA().getBody();
		Body body2 = contact.getFixtureB().getBody();

		( (PhysicsSprite) body1.getUserData() )
				.onCollisionBegan( (PhysicsSprite) body2.getUserData() );
		( (PhysicsSprite) body2.getUserData() )
			.onCollisionBegan( (PhysicsSprite) body1.getUserData() );
	}

	@Override
	public void endContact( Contact contact )
	{
		Body body1 = contact.getFixtureA().getBody();
		Body body2 = contact.getFixtureB().getBody();

		( (PhysicsSprite) body1.getUserData() )
				.onCollisionEnded( (PhysicsSprite) body2.getUserData() );
	}

	@Override
	public void preSolve( Contact contact, Manifold oldManifold )
	{

	}

	@Override
	public void postSolve( Contact contact, ContactImpulse impulse )
	{

	}

}
