/*
 * Copyright (C) 2009 Jason Tomlinson.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cloak.game;

/**
 * Based on the Sprite class and provides animation
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class AnimatedSprite extends Sprite {

	/** Current frame in the animation sequence **/
	protected int mCurrentFrame;
	/**�Counter used for the animation sequence  **/
	protected int mAnimationDelta;
	/** Speed at which we animate the sprite, 1000 equals 1 second in real-time **/
	protected int mAnimationSpeed;
	/** Number of frames in the animation sequence **/
	protected int mAnimationFrames;
	
	/** Are we currently in an animated sequence **/
	protected boolean mAnimating;
	
	/** Does the sprite continually loop through the animation sequence **/
	protected boolean mLoopAnimation;
	
	/** Animation bitmaps **/
	protected Bitmap mAnimatedSprite[];
	
	public AnimatedSprite(Bitmap img, int width, int height, int frames) {
		super();
		
		mWidth = width;
		mHeight = height;
		
		mAnimationFrames = frames;
		
		mAnimatedSprite = new Bitmap[mAnimationFrames];
		for (int i = 0; i < mAnimationFrames; i++)
			mAnimatedSprite[i] = img.createBitmap(img, mWidth * i, 0, mWidth, mHeight);
		
		mCurrentFrame = 0;
		
		mAnimating = false;
		mVisible = false;
	}
	
	public void heartbeat(long delta) {
		//jmt: don't do anything if we aren't animating
		if (mAnimating) {
			mAnimationDelta += delta;
			
			if (mAnimationDelta >= mAnimationSpeed) {
				mCurrentFrame++;
				mAnimationDelta = 0;
				
				if (mCurrentFrame >= mAnimationFrames) {
					mCurrentFrame = 0;
					if (!mLoopAnimation)
						mAnimating = false;
				}
				
			}
			//jmt: no need if we have a draw method in AnimatedSprite
			//mSprite = mAnimatedSprite[mCurrentFrame];
		}
	}
	
	/** AnimatedSprite draw routine **/
	public void draw(Canvas canvas) {
		if (mVisible) {
			canvas.save();
			canvas.rotate(mRotateDegrees, mX + mWidth/2, mY + mHeight/2);
			canvas.drawBitmap(mAnimatedSprite[mCurrentFrame], mX, mY, mPaint);
			canvas.restore();
		}
	}
	
	/** Is the sprite animating? **/
	public boolean isAnimating() {
		return mAnimating;
	}
	
	/** Get the current image from the animation sequence **/
	public Bitmap getFrame(int numFrame) {
		if (numFrame < mAnimationFrames)
			return mAnimatedSprite[numFrame];
		else
			return null;
	}
	
	/** Start the animation sequence **/
	public void startAnimation() {
		mCurrentFrame = 0;
		mAnimationDelta = 0;
		mAnimating = true;
	}
	
	/** Stop the animation sequence **/
	public void stopAnimation() {
		mAnimating = false;
	}
	
	/** Infinantly animate the sprite **/
	public void startLoopAnimation() {
		mLoopAnimation = true;
		startAnimation();
	}
	
	/** Stop the infinite animation sequence **/
	public void stopLoopAnimation() {
		mLoopAnimation = false;
		stopAnimation();
	}
	
	/** Set the speed at which the animation sequence runs **/
	public void setAnimationSpeed(int speed) {
		mAnimationSpeed = speed;
	}
	
	/** Set the current frame in the animation sequence **/
	public void setFrame(int frame) {
		if (frame < mAnimationFrames) {
			mCurrentFrame = frame;
		}
	}
	
	/** Return current anmiation sequence frame **/
	public int getFrame() {
		return mCurrentFrame;
	}
	
}
