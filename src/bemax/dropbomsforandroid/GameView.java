package bemax.dropbomsforandroid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.location.Address;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GameView implements SurfaceHolder.Callback, Runnable, OnTouchListener{
	private Hero hero;
	private Bom[] boms;
	private SurfaceHolder holder;
	private boolean isAttached;
	private Thread thread;

	public GameView(SurfaceView sView) {
		// TODO ペイントの初期化
		holder = sView.getHolder();
		holder.addCallback(this);

		hero = new Hero(sView.getResources(), R.drawable.icon);

		boms = new Bom[3];
		for(int i=0; i<boms.length; i++){
			boms[i] = new Bom(sView.getResources(), R.drawable.icon);
		}

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
		Log.d("EVENT", ""+event.getAction());
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
				b.drop();
			}
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
