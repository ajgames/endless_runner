package com.ajgames.endless_runner.view;

import android.graphics.Bitmap;

import com.ajgames.endless_runner.model.PhysicsSprite;

public class RunnerRenderer extends AnimatedSpriteRenderer
{

	public RunnerRenderer( PhysicsSprite sprite, Bitmap bitmap, Animator animator )
	{
		super( sprite, bitmap, animator, 6, 5, 30, 30 );
	}

}
