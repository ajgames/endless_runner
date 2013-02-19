package com.ajgames.endless_runner;

public class Speed
{

	public static final int DIRECTION_RIGHT = 1;
	public static final int DIRECTION_LEFT = -1;
	public static final int DIRECTION_UP = -1;
	public static final int DIRECTION_DOWN = 1;

	private float xv = 1;
	private float yv = 1;

	private int xDirection = DIRECTION_RIGHT;
	private int yDirection = DIRECTION_DOWN;

	public Speed()
	{
		this.xv = 1;
		this.yv = 1;
	}

	public float getXv()
	{
		return xv;
	}

	public void setXv( float xv )
	{
		this.xv = xv;
	}

	public float getYv()
	{
		return this.yv;
	}

	public void setYv( float yv )
	{
		this.yv = yv;
	}

	public int getxDirection()
	{
		return xDirection;
	}

	public void setxDirection( int xDirection )
	{
		this.xDirection = xDirection;
	}

	public int getyDirection()
	{
		return this.yDirection;
	}

	public void setyDirection( int yDirection )
	{
		this.yDirection = yDirection;
	}

	public void toggleXDirection()
	{
		this.xDirection *= -1;
	}

	public void toggleYDirection()
	{
		this.yDirection *= -1;
	}

}
