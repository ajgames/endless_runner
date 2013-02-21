package com.ajgames.endless_runner.view;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.ajgames.endless_runner.model.Sprite;

public class SpriteRenderer
{
	protected Sprite sprite;
	protected int width;
	protected int height;

	public SpriteRenderer( Sprite sprite )
	{
		this.sprite = sprite;
		this.height = sprite.getHeight();
		this.width = sprite.getWidth();
	}

	public void render( Canvas canvas )
	{
		Paint paint = new Paint();
		canvas.drawRect( this.sprite.getBounds(), paint );
	}
}
