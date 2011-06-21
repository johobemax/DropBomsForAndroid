package bemax.dropbomsforandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback, Runnable{
	private Paint paint;
	private Hero hero;
	private Context context;
	private SurfaceHolder holder;
	private boolean isAttached;
	private Thread thread;

	public GameView(Context context) {
		super(context);
		// TODO ペイントの初期化
		this.context = context;
		paint = new Paint();
		holder = this.getHolder();
		holder.addCallback(this);
		hero = new Hero();
		hero.setBitmap(getResources(), R.drawable.icon);

	}

	public void draw(SurfaceHolder h) {
		// TODO 画像を書く
		Canvas canvas = h.lockCanvas();
		canvas.drawColor(Color.BLACK);
		canvas.drawBitmap(hero.getBitmap(), hero.getX(), hero.getY(), paint);
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

	@Override
	public boolean onTouchEvent(MotionEvent event) {
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
			Log.d("Antion","Move");
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
		while(true){
			hero.move();
			draw(holder);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				break;
			}
		}
	}
}
