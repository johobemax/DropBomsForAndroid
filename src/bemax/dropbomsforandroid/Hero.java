package bemax.dropbomsforandroid;

import android.graphics.BitmapFactory;
import android.view.View;

public class Hero extends Item{
	private int target;

	public Hero(View v){
		super(v);
		image = BitmapFactory.decodeResource(view.getResources(), R.drawable.rubykun);
		imageWidth = image.getWidth();
		imageHeight = image.getHeight();
	}

	public void init(){
		x = (view.getWidth() - imageWidth ) / 2;
		y = view.getHeight() - imageHeight * 4;
		speed = view.getWidth() / 80;
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
	}

	public void chase(int tx){
		target = tx - image.getWidth() / 2;
	}

}
