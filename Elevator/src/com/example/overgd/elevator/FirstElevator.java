package com.example.overgd.elevator;

public class FirstElevator extends Thread {

	ElevatorView actvity;
	public int floor;

	public FirstElevator(ElevatorView actvity, int floor) {
		this.actvity = actvity;
		this.floor = floor;
	}

	@Override
	public void run() {
		super.run();

		for (int c = 0; c < 5; c++) { // 4회 반복

			for (int i = 0; i < 5; i++) { // 5회 반복(증가)
				actvity.floor_1 = i;
				// onDraw()메서드 호출 하지만 동기화해서 처리해줘야 한다.
				actvity.postInvalidate();
				try {
					Thread.sleep(floor);
				} catch (Exception e) {
				}
			}

			for (int i = 4; i >= 0; i--) { // 5회 반복(감소)
				actvity.floor_1 = i;
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
