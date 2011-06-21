package bemax.dropbomsforandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
	private Paint paint;
	private SurfaceHolder holder;

	public GameView(Context context) {
		super(context);
		// TODO ペイントの初期化
		paint = new Paint();
		holder = this.getHolder();
		holder.addCallback(this);
	}

	public void draw() {
		// TODO 画像を書く
		Canvas canvas = holder.lockCanvas();
		paint.setColor(Color.RED);
		canvas.drawRect(new Rect(10,10,100,100), paint);
		holder.unlockCanvasAndPost(canvas);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO サーフェイスが更新された時の処理

	}

	public void surfaceCreated(SurfaceHolder holder) {
		// TODO サーフェイスが作成された時の処理
		draw();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO サーフェイスが削除された時の処理

	}

}
