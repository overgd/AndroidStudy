package com.example.overgd.cardmatchgame;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.util.*;
import android.view.*;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new CardMatchView(this));
	}
}

class Card {

	static final int CARD_SHOW = 0;// 임시로 앞면 상태
	static final int CARD_CLOSE = 1;// 게임이 시작된 후의 뒷면 상태
	static final int CARD_PLAYER_OPEN = 2;// 플레이어가 선택한 후의 앞면 상태
	static final int CARD_MATCHED = 3; // 쌍이 맞아서 열린 앞면 상태
	static final int IMG_RED = 1;
	static final int IMG_GREEN = 2;
	static final int IMG_BLUE = 3;
	static final int IMG_YELLOW = 4;

	int m_state; // 현재 카드의 상태를 저장
	int m_Color; // 현재 카드의 색을 저장

	public Card(int color) {

		m_state = CARD_SHOW;
		m_Color = color;

	}
}

class CardMatchView extends View {

	Bitmap m_BackGroundImage; // 배경 이미지용
	Bitmap m_Card_BackImage; // 뒷면 이미지용
	Bitmap m_Card_Red; // 빨간 카드 이미지용
	Bitmap m_Card_Blue; // 파랑 카드 이미지용
	Bitmap m_Card_Green; // 초록 카드 이미지용
	Bitmap m_Card_Yellow; // 노랑 카드 이미지용

	Card m_Shuffle[][]; // 여섯개의 카드객체 저장용 배열

	static final int STATE_READY = 0; // 게임 시작 전 상태
	static final int STATE_GAME = 1; // 게임 중인 상태
	static final int STATE_END = 2; // 게임 종료 상태

	private int m_state = STATE_READY; // 초기값으로 게임 시작 전 상태로 둠
	private Card m_SelectCard_1 = null; // 첫번째 선택한 카드 저장용
	private Card m_SelectCard_2 = null; // 두번째 선택한 카드 저장용

	public CardMatchView(Context context) {

		super(context);

		m_BackGroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.background, null);
		m_Card_BackImage = BitmapFactory.decodeResource(getResources(), R.drawable.backside, null);

		m_Card_Red = BitmapFactory.decodeResource(getResources(), R.drawable.front_red, null);
		m_Card_Blue = BitmapFactory.decodeResource(getResources(), R.drawable.front_blue, null);
		m_Card_Green = BitmapFactory.decodeResource(getResources(), R.drawable.front_green, null);
		m_Card_Yellow = BitmapFactory.decodeResource(getResources(), R.drawable.front_yellow, null);

		m_Shuffle = new Card[4][2]; // 여섯개의 카드객체 저장
		setCardShuffle();
		CardGameThread cgt = new CardGameThread(this);
		cgt.start();

	}

	public void setCardShuffle() {
		m_Shuffle[0][0] = new Card(Card.IMG_BLUE);
		m_Shuffle[0][1] = new Card(Card.IMG_GREEN);
		m_Shuffle[1][0] = new Card(Card.IMG_RED);
		m_Shuffle[1][1] = new Card(Card.IMG_YELLOW);
		m_Shuffle[2][0] = new Card(Card.IMG_GREEN);
		m_Shuffle[2][1] = new Card(Card.IMG_RED);
		m_Shuffle[3][0] = new Card(Card.IMG_BLUE);
		m_Shuffle[3][1] = new Card(Card.IMG_YELLOW);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (m_state == STATE_READY) {

			startGame();
			m_state = STATE_GAME; // 게임상태를 시작상태로 변경

		} else if (m_state == STATE_GAME) {

			if (m_SelectCard_1 != null && m_SelectCard_2 != null) {
				// 첫번째 카드와 두번째 카드가 모두 앞면인 경우
				return true; // 메서드 종료
			}

			int px = (int) event.getX(); // 터치한 위치의 X좌표
			int py = (int) event.getY();// 터치한 위치의 Y좌표

			for (int y = 0; y < 2; y++) {
				for (int x = 0; x < 4; x++) {
					Rect box = new Rect(35 + x * 90, 180 + y * 130, 35 + x * 90 + 80, 180 + y * 130 + 115);
					// 가상의 사각형 생성
					if (box.contains(px, py)) { // 터치한 위치가 사각형 안인 경우

						if (m_Shuffle[x][y].m_state != Card.CARD_MATCHED) {
							// 카드를 맞추지 못한 경우
							if (m_SelectCard_1 == null) {
								// 선택이 첫번째 카드인 경우
								m_SelectCard_1 = m_Shuffle[x][y];
								m_SelectCard_1.m_state = Card.CARD_PLAYER_OPEN;
							} else {
								// 첫번째 카드가 선택되어 있는 경우
								if (m_SelectCard_1 != m_Shuffle[x][y]) {
									// 첫번째 선택한 카드와 동일한 카드가 아닌 경우
									m_SelectCard_2 = m_Shuffle[x][y];
									m_SelectCard_2.m_state = Card.CARD_PLAYER_OPEN;
								}
							}
						}
					}
				}
			}

		} else if (m_state == STATE_END) {
			m_state = STATE_READY;
		}
		invalidate();
		return true;
	}

	public void checkMatch() {

		if (m_SelectCard_1 == null || m_SelectCard_2 == null) {
			// 두 개의 카드를 선택하지 않은 경우
			return;// 메서드 종료
		}
		
		if (m_SelectCard_1.m_Color == m_SelectCard_2.m_Color) {
			// 첫번째 카드와 두번째 카드가 일치하는 경우
			m_SelectCard_1.m_state = Card.CARD_MATCHED;
			m_SelectCard_2.m_state = Card.CARD_MATCHED;
			m_SelectCard_1 = null;
			m_SelectCard_2 = null;
			// 다시 선택할 수 있도록 변수를 비움
		} else { // 두 카드의 색이 일치하지 않는 경우

			try {
				Thread.sleep(500); // 0.5초 대기
			} catch (Exception e) {
			}
			m_SelectCard_1.m_state = Card.CARD_CLOSE;
			m_SelectCard_2.m_state = Card.CARD_CLOSE;
			m_SelectCard_1 = null;
			m_SelectCard_2 = null;
		}
		
		postInvalidate(); // onDraw메서드 호출

	}

	public void startGame() {
		m_Shuffle[0][0].m_state = Card.CARD_CLOSE;
		m_Shuffle[0][1].m_state = Card.CARD_CLOSE;
		m_Shuffle[1][0].m_state = Card.CARD_CLOSE;
		m_Shuffle[1][1].m_state = Card.CARD_CLOSE;
		m_Shuffle[2][0].m_state = Card.CARD_CLOSE;
		m_Shuffle[2][1].m_state = Card.CARD_CLOSE;
		m_Shuffle[3][0].m_state = Card.CARD_CLOSE;
		m_Shuffle[3][1].m_state = Card.CARD_CLOSE;
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(m_BackGroundImage, 0, 0, null); // 배경이미지 출력
		for (int y = 0; y < 2; y++) { // 2번 반복
			for (int x = 0; x < 4; x++) { // 3번 반복

				if (m_Shuffle[x][y].m_state == Card.CARD_SHOW || m_Shuffle[x][y].m_state == Card.CARD_PLAYER_OPEN
						|| m_Shuffle[x][y].m_state == Card.CARD_MATCHED) {
					// 카드 앞면
					if (m_Shuffle[x][y].m_Color == Card.IMG_RED) {
						canvas.drawBitmap(m_Card_Red, 35 + x * 90, 180 + y * 130, null);
					} else if (m_Shuffle[x][y].m_Color == Card.IMG_BLUE) {
						canvas.drawBitmap(m_Card_Blue, 35 + x * 90, 180 + y * 130, null);
					} else if (m_Shuffle[x][y].m_Color == Card.IMG_GREEN) {
						canvas.drawBitmap(m_Card_Green, 35 + x * 90, 180 + y * 130, null);
					} else if (m_Shuffle[x][y].m_Color == Card.IMG_YELLOW) {
						canvas.drawBitmap(m_Card_Yellow, 35 + x * 90, 180 + y * 130, null);
					}
				} else {
					canvas.drawBitmap(m_Card_BackImage, 35 + x * 90, 180 + y * 130, null);
				}

			}
		}
	}

}

class CardGameThread extends Thread {

	CardMatchView cmv;

	public CardGameThread(CardMatchView cmv) {
		this.cmv = cmv;
	}

	@Override
	public void run() {
		super.run();
		while (true) {
			cmv.checkMatch();
		}
	}

}