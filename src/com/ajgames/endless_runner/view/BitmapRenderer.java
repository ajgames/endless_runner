package com.ajgames.endless_runner.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.ajgames.endless_runner.model.Sprite;

public class BitmapRenderer extends SpriteRenderer
{

	protected Bitmap bitmap;
	protected Rect sourceRect;

	public BitmapRenderer( Sprite sprite, Bitmap bitmap )
	{
		super( sprite );
		this.bitmap = bitmap;
		this.width = bitmap.getWidth();
		this.height = bitmap.getHeight();
		this.sourceRect = new Rect( 0, 0, this.width, this.height );
	}

	public void destroy()
	{
		this.bitmap.recycle();
	}

	@Override
	public void render( Canvas canvas )
	{
		Rect destRect = new Rect( (int) this.sprite.getX(),
				(int) this.sprite.getY(),
				(int) ( this.sprite.getX() + this.sprite.getWidth() ),
				(int) ( this.sprite.getY() + this.sprite.getHeight() ) );
		canvas.drawBitmap( this.bitmap, this.sourceRect, destRect, null );
	}

}
