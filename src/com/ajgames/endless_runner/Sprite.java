package com.ajgames.endless_runner;

import android.graphics.Bitmap;

public class Sprite
{
	protected Bitmap bitmap;
	protected float x;
	protected float y;
	protected int width;
	protected int height;

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

	public Sprite()
	{

	}

	public Sprite( float x, float y, int width, int height )
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

}
