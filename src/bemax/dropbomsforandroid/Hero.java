package bemax.dropbomsforandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

public class Hero extends Acter{
	private int target;

	public Hero(View v){
		super(v);
		animation = new Animation(v.getResources(), new int[]{
				R.drawable.ruby_p1,
				R.drawable.ruby_p2,
				R.drawable.ruby_p3,
				R.drawable.ruby_p2}, 5);
		zoc = (int)(getImageWidth() * 0.4);
	}

	public void init(int cd){
		x = (view.getWidth() - getImageWidth() ) / 2;
		y = (int)(view.getHeight()*0.7 - getImageHeight() * 0.8);
		speed = view.getWidth() / 40;
		target = x;
	}

	@Override
	public void move(){
		int dx = target - x;
		if(dx>=0){
			if(dx < speed){
				x += dx;
			}else{
				x += speed;
			}
		}else{
			if(dx > -speed){
				x += dx;
			}else{
				x += -speed;
			}
		}
		setImage(animation.nextImage());
	}

	public void chase(int tx){
		target = tx - getImageWidth() / 2;
	}
}
