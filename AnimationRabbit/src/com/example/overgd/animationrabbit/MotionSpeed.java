package com.example.overgd.animationrabbit;

public class MotionSpeed implements Runnable {

	MainActivity.AnimationRabbit animation;
	public int speed;

	public MotionSpeed(MainActivity.AnimationRabbit animation, int speed) {
		super();
		this.animation = animation;
		this.speed = speed;
	}

	@Override
	public void run() {

		for (;;) {
			for (int i = 0; i <= 1; i++) {
				animation.MOTION = i;
				animation.postInvalidate();
				try {
					Thread.sleep(speed);
				} catch (Exception e) {
				}
			}
		}

	}

}
