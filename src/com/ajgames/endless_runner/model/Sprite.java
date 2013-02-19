package com.ajgames.endless_runner.model;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class Sprite
{
	protected Bitmap bitmap;
	protected float x;
	protected float y;
	protected int width;
	protected int height;
	protected float rotation;

	public float getRotation()
	{
		return this.rotation;
	}
	
	public void setRotation( float r )
	{
		this.rotation = r;
	}
	
	public int getHeight()
	{
		return height;
	}

	public void setHeight( int h )
	{
		this.height = h;
	}

	public int getWidth()
	{
		return this.width;
	}

	public void setWidth( int w )
	{
		this.width = w;
	}

	public float getX()
	{
		return this.x;
	}

	public void setX( float x )
	{
		this.x = x;
	}

	public float getY()
	{
		return this.y;
	}

	public void setY( float y )
	{
		this.y = y;
	}

	public Bitmap getBitmap()
	{
		return this.bitmap;
	}

	public void setBitmap( Bitmap bitmap )
	{
		this.bitmap = bitmap;
	}

	public Rect getBounds()
	{
		return new Rect( (int) this.getX(), (int) this.getY(), (int) this.getX() + this.getWidth(),
				(int) this.getY() + this.getHeight() );
	}

	public Sprite()
	{
		this.rotation = 0;
		this.x = 0.0f;
		this.y = 0.0f;
		this.width = 0;
		this.height = 0;
	}

	public Sprite( float x, float y, int width, int height )
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rotation = 0;
	}

}
