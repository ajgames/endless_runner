package com.ajgames.endless_runner;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Droid extends Sprite
{
	private Speed speed;
	private boolean touched;

	public Droid( Bitmap bitmap, int x, int y )
	{
		this.speed = new Speed();
		this.speed.setXv( 2 );
		this.speed.setYv( 2 );
		this.setBitmap( bitmap );
		this.setX( x );
		this.setY( y );
	}

	public Speed getSpeed()
	{
		return speed;
	}

	public boolean isTouched()
	{
		return touched;
	}

	public void setTouched( boolean touched )
	{
		this.touched = touched;
	}

	public void draw( Canvas canvas )
	{
		canvas.drawBitmap( getBitmap(),
				getX() - ( getBitmap().getWidth() / 2 ), getY()
						- ( getBitmap().getHeight() / 2 ), null );
	}

	public void update()
	{
		if ( !this.touched )
		{
			this.setX( this.getX() + speed.getXv() * speed.getxDirection() );
			this.setY( this.getY() + speed.getYv() * speed.getyDirection() );
		}
	}

	public void handleActionDown( int eventX, int eventY )
	{
		if ( eventX >= ( this.getX() - this.getBitmap().getWidth() / 2 )
				&& ( eventX <= ( this.getX() + this.getBitmap().getWidth() / 2 ) ) )
		{
			if ( eventY >= ( this.getY() - this.getBitmap().getHeight() / 2 )
					&& ( this.getY() <= ( this.getY() + this.getBitmap().getHeight() / 2 ) ) )
			{
				setTouched( true );
			} else
			{
				setTouched( false );
			}
		} else
		{
			setTouched( false );
		}
	}
}
