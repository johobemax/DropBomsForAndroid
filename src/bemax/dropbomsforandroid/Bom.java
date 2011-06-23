package bemax.dropbomsforandroid;

import java.util.Random;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Bom implements Movable{
	private int x;
	private int y;
	private int speed;
	private Bitmap bitmap;
	private Random rand;
	private int width;
	private int height;

	public Bom(Resources res, int id){
		bitmap = BitmapFactory.decodeResource(res, id);
		rand = new Random();
		init();
		width = bitmap.getWidth();
		height = bitmap.getHeight();
	}

	public void init(){
		x = rand.nextInt(400);
		y = 0;
		speed = rand.nextInt(5) + 5;
	}

	public void move(){
		y += speed;
		if(y>500){
			init();
		}
	}

	public void drawBom(Canvas canvas){
		canvas.drawBitmap(bitmap, x, y, null);
	}

	public Rect getRect() {
		// TODO 自動生成されたメソッド・スタブ
		return new Rect(x, y, x+width, y+height);
	}

	public boolean isHit(Movable m) {
		// TODO 自動生成されたメソッド・スタブ
		return m.getRect().contains(getRect().centerX(), getRect().centerY());
	}
}
