package bemax.dropbomsforandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
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
	private int get;
	private Activity context;
	private View view;

	public GameView(SurfaceView sView, Activity con) {
		// TODO ペイントの初期化

		view = sView;

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
			if(b.getY() > -b.getImageHeight())
				canvas.drawBitmap(b.getImage(), null, b.getRect(), paint);
		}

		if(apple.getY() > -apple.getImageHeight())
			canvas.drawBitmap(apple.getImage(), null, apple.getRect(), paint);

		for(Orange orange: oranges){
			if(orange.getY() > -orange.getImageHeight())
				canvas.drawBitmap(orange.getImage(), null, orange.getRect(), paint);
		}

		canvas.drawText("SCORE:" + score, 30, 30, paint);
		canvas.drawText("Get:" + get +"%", 230, 30, paint);
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

		hero.init(0);

		for(Bom b: boms){
			b.setSpeedBase(view.getHeight()/300.0f);
			b.init(50);
		}

		apple.setSpeedBase(view.getHeight()/300.0f);
		apple.init(200);

		for(Orange orange: oranges){
			orange.setSpeedBase(view.getHeight()/300.0f);
			orange.init(50);
		}

		score = 0;
		get = 0;
		int getnum = 0;
		int dropnum = 0;

		boolean nohit = true;

		while(isAttached & nohit){
			long st = System.currentTimeMillis();

			hero.move();

			for(Bom b: boms){
				b.move();
				if(b.isHit(hero)){
					nohit = false;
					break;
				}
				if(b.getRect().top > view.getHeight()){
					b.init(0);
				}
			}

			apple.move();
			if(apple.isHit(hero)){
				apple.init(0);
				score += 100;
				getnum ++;
				dropnum ++;
			}
			if(apple.getRect().top > view.getHeight()){
				apple.init(0);
				score -= 200;
				dropnum ++;
			}

			for(Orange orange: oranges){
				orange.move();
				if(orange.isHit(hero)){
					orange.init(0);
					score += 10;
					getnum ++;
					dropnum ++;
				}
				if(orange.getRect().top > view.getHeight()){
					orange.init(0);
					score -= 20;
					dropnum ++;
				}
			}

			if(dropnum > 0){
				get = 100 * getnum / dropnum;
			}

			if(score<0) score = 0;

			draw(holder);

			long ed = System.currentTimeMillis();

			if(ed-st < 30){
				try {
					Thread.sleep(30-ed+st);
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
			intent.putExtra("get", get);
			context.startActivity(intent);
		}
		context.finish();
	}
}