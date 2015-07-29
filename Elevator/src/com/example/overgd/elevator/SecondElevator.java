package com.example.overgd.elevator;

public class SecondElevator implements Runnable {

	ElevatorView actvity;
	public int floor;

	public SecondElevator(ElevatorView activity, int floor) {
		super();
		this.actvity = activity;
		this.floor = floor;
	}

	@Override
	public void run() {
		for (int c = 0; c < 5; c++) { // 4회 반복

			for (int i = 0; i < 5; i++) { // 5회 반복(증가)
				actvity.floor_2 = i;
				// onDraw()메서드 호출 하지만 동기화해서 처리해줘야 한다.
				actvity.postInvalidate();
				try {
					Thread.sleep(floor);
				} catch (Exception e) {
				}
			}

			for (int i = 4; i >= 0; i--) { // 5회 반복(감소)
				actvity.floor_2 = i;
				actvity.postInvalidate();
				try {
					Thread.sleep(floor);

				} catch (Exception e) {

				}

			}

		}
		actvity.flag = true;
		actvity.postInvalidate();

	}

}
