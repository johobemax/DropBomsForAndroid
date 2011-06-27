package bemax.dropbomsforandroid;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GameView implements SurfaceHolder.Callback, Runnable, OnTouchListener{
	private Hero hero;
	private Bom[] boms;
	private Apple apple;
	private Orange orange;
	private SurfaceHolder holder;
	private boolean isAttached;
	private Thread thread;
	private DisplayMetrics displayMetrics;

	public GameView(SurfaceView sView, DisplayMetrics dm) {
		// TODO ペイントの初期化
		displayMetrics = dm;

		holder = sView.getHolder();
		holder.addCallback(this);

		Rect viewSize = new Rect();
		sView.getDrawingRect(viewSize);

		hero = new Hero(	sView,					// 描画対象のView
							viewSize.centerX(),		// 初期位置のX座標
							viewSize.height()-40,	// 初期位置のY座標
							15						// スピード
						);

		boms = new Bom[3];
		for(int i=0; i<boms.length; i++){
			boms[i] = new Bom(	sView.getResources(),
								R.drawable.bom
							);
		}

		apple = new Apple(sView.getResources(), R.drawable.apple);
		orange = new Orange(sView.getResources(), R.drawable.orange);

		sView.setOnTouchListener(this);
	}

	public void draw(SurfaceHolder h) {
		// TODO 画像を書く
		Canvas canvas = h.lockCanvas();
		canvas.drawColor(Color.BLACK);
		hero.drawHero(canvas);
		for(Bom b: boms){
			b.drawBom(canvas);
		}
		apple.drawBom(canvas);
		orange.drawBom(canvas);
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
		while(isAttached){
			hero.move();
			for(Bom b: boms){
				b.move();
				if(b.isHit(hero)){
					isAttached = false;
					break;
				}
			}
			if(apple.getFlg()){
				apple.move();
			}else{
				Random r = new Random();
				if(r.nextInt(500)==1){
					apple.setFlg(true);
				}
			}
			orange.move();
			draw(holder);
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				break;
			}
		}
	}
}
