package com.ajgames.endless_runner;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Particle
{

	public static final int STATE_ALIVE = 0; // particle is alive
	public static final int STATE_DEAD = 1; // particle is dead

	public static final int DEFAULT_LIFETIME = 200; // play with this
	public static final int MAX_DIMENSION = 5; // the maximum width or height
	public static final int MAX_SPEED = 10; // maximum speed (per update)

	private int state; // particle is alive or dead
	private float width; // width of the particle
	private float height; // height of the particle
	private float x, y; // horizontal and vertical position
	private double xv, yv; // vertical and horizontal velocity
	private int age; // current age of the particle
	private int lifetime; // particle dies when it reaches this value
	private int color; // the color of the particle
	private Paint paint; // internal use to avoid instantiation

	public boolean isAlive()
	{
		return state == STATE_ALIVE;
	}

	public boolean isDead()
	{
		return state == STATE_DEAD;
	}

	public Particle( int x, int y )
	{
		Random random = new Random();
		this.x = x;
		this.y = y;
		this.state = Particle.STATE_ALIVE;
		this.width = random.nextInt( MAX_DIMENSION ) + 1;
		this.height = this.width;
		this.lifetime = DEFAULT_LIFETIME;
		this.age = 0;
		this.xv = random.nextDouble() * MAX_SPEED * 2 - MAX_SPEED;
		this.yv = random.nextDouble() * MAX_SPEED * 2 - MAX_SPEED;
		// smoothing out the diagonal speed
		if( xv * xv + yv * yv > MAX_SPEED * MAX_SPEED )
		{
			xv *= 0.7;
			yv *= 0.7;
		}
		this.color = Color.argb( 255, random.nextInt( 255 ),
				random.nextInt( 255 ), random.nextInt( 255 ) );
		this.paint = new Paint( this.color );
	}

	public void update()
	{
		if( this.state == STATE_DEAD )
			return;

		this.x += this.xv;
		this.y += this.yv;

		int a = this.color >>> 24;
		a -= 2;
		if( a <= 0 )
		{
			this.state = STATE_DEAD;
		} else
		{
			this.color = ( this.color & 0x00FFFFFF ) + ( a << 24 );
			this.paint.setAlpha( a );
			this.age++;
		}
		if( this.age >= this.lifetime )
		{
			this.state = STATE_DEAD;
		}
	}

	public void draw( Canvas canvas )
	{
		paint.setColor( this.color );
		canvas.drawRect( this.x, this.y, this.x + this.width, this.y
				+ this.height, paint );
	}

}
