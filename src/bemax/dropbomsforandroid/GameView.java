package bemax.dropbomsforandroid;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GameView implements SurfaceHolder.Callback, Runnable, OnTouchListener{
	private Hero hero;
	private Bom[] boms;
	private Apple apple;
	private Orange[] oranges;
	private SurfaceHolder holder;
	private boolean isAttached;
	private Thread thread;
	private int score;
	private Activity context;

	public GameView(SurfaceView sView, Activity con) {
		// TODO ペイントの初期化

		holder = sView.getHolder();
		holder.addCallback(this);

		context = con;

		hero = new Hero(sView);

		boms = new Bom[4];
		for(int i=0; i<boms.length; i++){
			boms[i] = new Bom(sView);
		}

		apple = new Apple(sView);

		oranges = new Orange[2];
		for(int i=0; i<oranges.length; i++){
			oranges[i] = new Orange(sView);
		}

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

		for(Orange orange: oranges){
			canvas.drawBitmap(orange.getImage(), null, orange.getRect(), paint);
		}

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

		for(Orange orange: oranges){
			orange.init();
		}

		Date date = new Date();

		score = 0;

		boolean nohit = true;

		Timer timer = new Timer();
		TimerTask task = new TimerTask(){
			@Override
			public void run() {
				// TODO 設定時間後に実行されるタスク
				for(Bom b: boms){
					b.setMoveOK(true);
				}

				for(Orange orange: oranges){
					orange.setMoveOK(true);
				}
			}
		};

		//1.5秒後から爆弾などが落ちてくる
		timer.schedule(task, 1500);

		while(isAttached & nohit){
			long st = date.getTime();

			hero.move();

			for(Bom b: boms){
				b.move();
				if(b.isHit(hero)){
					nohit = false;
					break;
				}
			}

			apple.move();
			if(apple.isHit(hero)){
				apple.init();
				score += 100;
			}

			for(Orange orange: oranges){
				orange.move();
				if(orange.isHit(hero)){
					orange.init();
					score += 10;
				}
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

		//爆弾にぶつかってループが終了していれば、GameOver画面を出す。
		if(!nohit){
			Intent intent = new Intent(context, GameOverActivity.class);
			intent.putExtra("score", score);
			context.startActivity(intent);
		}
		context.finish();
	}
}