package bemax.dropbomsforandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

public class Hero extends Item{
	private int target;
	private Bitmap[] images;
	private int anim_cnt;

	public Hero(View v){
		super(v);
		anim_cnt = 0;
		images = new Bitmap[4];
		images[0] = BitmapFactory.decodeResource(view.getResources(), R.drawable.ruby_p1);
		images[1] = BitmapFactory.decodeResource(view.getResources(), R.drawable.ruby_p2);
		images[2] = BitmapFactory.decodeResource(view.getResources(), R.drawable.ruby_p3);
		images[3] = BitmapFactory.decodeResource(view.getResources(), R.drawable.ruby_p2);
		image = images[0];
		imageWidth = image.getWidth();
		imageHeight = image.getHeight();
	}

	public void init(int cd){
		x = (view.getWidth() - imageWidth ) / 2;
		y = (int)(view.getHeight()*0.7 - imageHeight * 0.8);
		speed = view.getWidth() / 40;
		target = x;
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
			if(dx > -speed){
				x += dx;
			}else{
				x += -speed;
			}
		}
		anim_cnt++;
		if(anim_cnt % 5 == 0){
			if(anim_cnt==20) anim_cnt = 0;
			image = images[anim_cnt / 5];
		}
	}

	public void chase(int tx){
		target = tx - image.getWidth() / 2;
	}

}
