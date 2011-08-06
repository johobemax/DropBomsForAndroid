package bemax.dropbomsforandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.View;

public abstract class Acter {
	protected float x;
	protected float y;
	protected float speed;
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

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Rect getRect(){
		return new Rect((int)x, (int)y, (int)x + getImageWidth(), (int)y + getImageHeight());
	}

	public void setSpeedBase(float f){
		speedBase = f;
	}
}