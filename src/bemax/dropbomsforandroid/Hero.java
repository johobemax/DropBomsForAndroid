package bemax.dropbomsforandroid;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class Hero {
	private int x;
	private int y;
	private int speed;
	private Bitmap bitmap;
	private int target;

	public Hero(){
		x = 190;
		y = 400;
		speed = 15;
		target = 190;
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

	public void chase(int tx){
		target = tx - bitmap.getWidth() / 2;
	}

	public void setBitmap(Resources r, int id){
		bitmap = BitmapFactory.decodeResource(r, id);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}


}
