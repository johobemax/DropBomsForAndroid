package bemax.dropbomsforandroid;

import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class GameView implements SurfaceHolder.Callback, Runnable, OnTouchListener{
	private Hero hero;
	private Bom[] boms;
	private Apple apple;
	private Orange orange;
	private SurfaceHolder holder;
	private boolean isAttached;
	private Thread thread;
	private int score;

	public GameView(SurfaceView sView) {
		// TODO ペイントの初期化

		holder = sView.getHolder();
		holder.addCallback(this);

		hero = new Hero(sView);

		boms = new Bom[3];
		for(int i=0; i<boms.length; i++){
			boms[i] = new Bom(sView);
		}

		apple = new Apple(sView);
		orange = new Orange(sView);

		sView.setOnTouchListener(this);
	}

	public void draw(SurfaceHolder h) {
		// TODO 画像を書く
		Paint paint = new Paint();
		paint.setTextSize(30);
		paint.setColor(Color.WHITE);
		Canvas canvas = h.lockCanvas();
		canvas.drawColor(Color.BLACK);
		canvas.drawBitmap(hero.getImage(), null, hero.getRect(), paint);
		for(Bom b: boms){
			canvas.drawBitmap(b.getImage(), null, b.getRect(), paint);
		}
		canvas.drawBitmap(apple.getImage(), null, apple.getRect(), paint);
		canvas.drawBitmap(orange.getImage(), null, orange.getRect(), paint);

		canvas.drawText("SCORE:" + score, 30, 30, paint);
		h.unlockCanvasAndPost(canvas);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO サーフェイスが更新された時の処理
	}

	public void surfaceCreated(SurfaceHolder holder) {
		// TODO サーフェイスが作成された時の処理
		isAttached = true;
		thread = new Thread(this);
		thread.start();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO サーフェイスが削除された時の処理
		isAttached = false;
		while(thread.isAlive());
	}

	public boolean onTouch(View v, MotionEvent event) {
		// TODO サーフェイスがタッチされて時の処理
		int x = -1;
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:		// タッチされた
			x = (int) event.getX();
			hero.chase(x);
			break;
		case MotionEvent.ACTION_MOVE:		// タッチしたまま動いた
			x = (int)event.getX();
			hero.chase(x);
			break;
		case MotionEvent.ACTION_UP:			// タッチした指が離れた
			x = -1;
			break;
		case MotionEvent.ACTION_OUTSIDE:	// 検出範囲外に出た

			break;
		}

		return true;	// その後のタッチイベントを追いかける。
	}

	public void onTouchModeChanged(boolean isInTouchMode) {
		// TODO タッチモードが変更された時の処理
	}

	public void run() {
		// TODO ゲームの処理ルーチン

		hero.init();

		for(Bom b: boms){
			b.init();
		}

		apple.init();

		orange.init();

		Date date = new Date();

		score = 0;

		while(isAttached){
			long st = date.getTime();

			hero.move();

			for(Bom b: boms){
				b.move();
				if(b.isHit(hero)){
					isAttached = false;
					break;
				}
			}

			apple.move();
			if(apple.isHit(hero)){
				apple.init();
				score += 100;
			}

			orange.move();
			if(orange.isHit(hero)){
				orange.init();
				score += 10;
			}

			draw(holder);

			long ed = date.getTime();
			if(ed-st < 10){
				try {
					Thread.sleep(10-ed+st);
				} catch (InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
		}
	}
}