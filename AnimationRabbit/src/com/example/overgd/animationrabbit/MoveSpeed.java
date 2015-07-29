package com.example.overgd.animationrabbit;

public class MoveSpeed implements Runnable {

	MainActivity.AnimationRabbit animation;
	public int speed;

	public MoveSpeed(MainActivity.AnimationRabbit animation, int speed) {
		super();
		this.animation = animation;
		this.speed = speed;
	}

	@Override
	public void run() {
		
		for(;;) {
			for (int i = 0; i < 100; i++) {
				animation.MOVE = i;
				animation.postInvalidate();
				try {
					Thread.sleep(speed);
				}catch (Exception e) {
					
				}
			}
			for (int i = 100; i >= 0; i--) {
				animation.MOVE = i;
				animation.postInvalidate();
				try {
					Thread.sleep(speed);
				}catch (Exception e) {
					
				}
			}
		}
		
	}

}
