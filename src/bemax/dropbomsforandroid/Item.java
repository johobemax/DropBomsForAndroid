package bemax.dropbomsforandroid;

import java.util.TimerTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.View;

public abstract class Item implements Movable{
	protected int x;
	protected int y;
	protected int speed;
	protected Bitmap image;
	protected int imageWidth;
	protected int imageHeight;
	protected View view;

	public Item(View v) {
		view = v;
	}

	public abstract void init();

	public void setImage(int id){
		image = BitmapFactory.decodeResource(view.getResources(), id);
	}

	public abstract void move();

	public boolean isHit(Movable m){
		int dx = m.getRect().centerX() - getRect().centerX();
		int dy = m.getRect().centerY() - getRect().centerY();
		double len = Math.sqrt(dx * dx + dy * dy);

		if(len <= imageWidth){
			return true;
		}else{
			return false;
		}
	}

	public Bitmap getImage() {
		return image;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Rect getRect(){
		return new Rect(x, y, x + imageWidth, y + imageHeight);
	}
}
