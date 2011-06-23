package bemax.dropbomsforandroid;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Hero implements Movable{
	private int x;
	private int y;
	private int speed;
	private Bitmap bitmap;
	private int target;
	private int width;
	private int height;

	public Hero(Resources r, int id){
		x = 190;
		y = 400;
		speed = 15;
		target = 190;
		bitmap = BitmapFactory.decodeResource(r, id);
		width = bitmap.getWidth();
		height = bitmap.getHeight();
	}

	public void move(){
		int dx = target - x;
		if(dx>=0){
			if(dx < speed){
				x += dx;
			}else{
				x += speed;
			}
		}else{
			if(dx > speed){
				x += dx;
			}else{
				x += -speed;
			}
		}
	}

	public void drawHero(Canvas canvas){
		canvas.drawBitmap(bitmap, x, y, null);
	}

	public void chase(int tx){
		target = tx - bitmap.getWidth() / 2;
	}

	public Rect getRect() {
		// TODO 自動生成されたメソッド・スタブ
		return new Rect(x, y, x+width, y+height);
	}

	public boolean isHit(Movable m) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
}
