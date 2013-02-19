package com.ajgames.endless_runner.view;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.ajgames.endless_runner.model.Sprite;

public class SpriteRenderer
{
	private Sprite sprite;

	public SpriteRenderer( Sprite sprite )
	{
		this.sprite = sprite;
	}

	public void render( Canvas canvas )
	{
		Paint paint = new Paint();
		canvas.drawRect( this.sprite.getBounds(), paint );
	}
}
