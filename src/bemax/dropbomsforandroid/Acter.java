package bemax.dropbomsforandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

public abstract class Acter {
	protected int x;
	protected int y;
	protected int speed;
	protected Bitmap image;
	protected View view;
	protected float speedBase;
	protected int zoc;
	protected Animation animation;

	public Acter(View v) {
		view = v;
	}

	public abstract void init(int cd);

	public abstract void move();

	public void setImage(int id){
		image = BitmapFactory.decodeResource(view.getResources(), id);
	}

	public void setImage(Bitmap bitmap){
		image = bitmap;
	}

	public Bitmap getImage() {
		Bitmap btm;
		if(animation==null){
			btm = image;
		}else{
			btm = animation.nextImage();
		}
		return btm;
	}

	public int getImageWidth() {
		return getImage().getWidth();
	}

	public int getImageHeight() {
		return getImage().getHeight();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Rect getRect(){
		return new Rect(x, y, x + getImageWidth(), y + getImageHeight());
	}

	public void setSpeedBase(float f){
		speedBase = f;
	}
}