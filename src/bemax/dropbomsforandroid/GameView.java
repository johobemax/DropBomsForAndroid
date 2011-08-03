package bemax.dropbomsforandroid;

import java.io.IOException;
import java.text.Format;
import java.util.Formatter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
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
	private DropBomsForAndroidActivity activity;
	private SurfaceView view;
	private Handler handler;
	private long dist;
	private Bitmap backImage;
	private float horizon;

	public GameView(DropBomsForAndroidActivity act, Handler h) {
		activity = act;
		handler  = h;

		activity.setContentView(R.layout.gameplay);
		view = (SurfaceView)activity.findViewById(R.id.gameView);

		holder = view.getHolder();
		holder.addCallback(this);

		hero = new Hero(view);

		boms = new Bom[4];
		for(int i=0; i<boms.length; i++){
			boms[i] = new Bom(view);
		}

		apple = new Apple(view);

		oranges = new Orange[2];
		for(int i=0; i<oranges.length; i++){
			oranges[i] = new Orange(view);
		}

		view.setOnTouchListener(this);

		activity.setMp(MediaPlayer.create(activity, R.raw.music));
		try {
			activity.getMp().setLooping(true);
			activity.getMp().prepare();
		} catch (IllegalStateException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public void init(){
		backImage = BitmapFactory.decodeResource(view.getResources(), R.drawable.universe);
		horizon = view.getHeight() * 0.7f;
		float scale = horizon / backImage.getHeight() * 0.8f;
		backImage = Bitmap.createScaledBitmap(backImage, (int)(backImage.getWidth()*scale)+1, (int)(backImage.getHeight()*scale)+1, true);
	}

	public void draw(SurfaceHolder h) {
		// TODO 画像を書く
		//float horizon = view.getHeight() * 0.7f;

		Paint paint = new Paint();
		paint.setTextSize(30);
		Canvas canvas = h.lockCanvas();
		paint.setColor(view.getResources().getColor(R.color.sky));
		canvas.drawRect(0.0f,0.0f,(float)view.getWidth(), horizon, paint);
		canvas.drawBitmap(backImage, 0.0f, 0.0f,paint);
		paint.setColor(view.getResources().getColor(R.color.grass));
		canvas.drawRect(0.0f, horizon, (float)view.getWidth(), (float)view.getHeight(), paint);

		paint.setColor(Color.WHITE);
		canvas.drawText("SCORE:" + score, 30, 30, paint);
		canvas.drawText("Get:" + get +"%", 230, 30, paint);
		canvas.drawText("dist:" + new Formatter().format("%03d",dist).toString(), 30, view.getHeight() - 250, paint);

		canvas.drawBitmap(hero.getImage(), null, hero.getRect(), paint);

		if(apple.getY() > -apple.getImageHeight())
			canvas.drawBitmap(apple.getImage(), null, apple.getRect(), paint);

		for(Orange orange: oranges){
			if(orange.getY() > -orange.getImageHeight())
				canvas.drawBitmap(orange.getImage(), null, orange.getRect(), paint);
		}

		for(Bom b: boms){
			if(b.getY() > -b.getImageHeight())
				canvas.drawBitmap(b.getImage(), null, b.getRect(), paint);
		}

		h.unlockCanvasAndPost(canvas);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO サーフェイスが更新された時の処理
	}

	public void surfaceCreated(SurfaceHolder holder) {
		// TODO サーフェイスが作成された時の処理
		init();
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

		activity.getMp().start();

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
				if(b.getRect().top > view.getHeight() - 200){
					b.init(0);
					activity.getSp().play(activity.getSeMap().get(R.raw.bom), 0.5f, 0.5f, 0,0,1.0f);
				}
			}

			apple.move();
			if(apple.isHit(hero)){
				activity.getSp().play(activity.getSeMap().get(R.raw.item_get), 0.5f, 0.5f, 0,0,1.0f);
				apple.init(0);
				score += 100;
				getnum ++;
				dropnum ++;
			}
			if(apple.getRect().top > view.getHeight() - 200){
				apple.init(0);
				score -= 200;
				dropnum ++;
			}

			for(Orange orange: oranges){
				orange.move();
				if(orange.isHit(hero)){
					activity.getSp().play(activity.getSeMap().get(R.raw.item_get), 0.5f, 0.5f, 0,0,1.0f);
					orange.init(0);
					score += 10;
					getnum ++;
					dropnum ++;
				}
				if(orange.getRect().top > view.getHeight() - 200){
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
			dist = ed - st;

			if(dist < 30){
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
			Message mes = Message.obtain(handler);
			mes.obj = new int[]{score, get};
			mes.what = 2;
			mes.sendToTarget();
		}
		activity.getMp().release();
	}
}
