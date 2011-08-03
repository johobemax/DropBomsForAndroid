package bemax.dropbomsforandroid;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

public class Orange extends Item{
	private Random rand;
	private int countDown;

	public Orange(View v){
		super(v);
		setImage(R.drawable.orange);
		zoc = (int)(getImageWidth() * 0.5);
		rand = new Random();

		speedBase = v.getWidth() / 400.0f;
	}

	@Override
	public void init(int cd) {
		// TODO 自動生成されたメソッド・スタブ
		x = rand.nextInt(view.getWidth()-getImageWidth());
		y = -getImageHeight();
		speed = (int)((rand.nextInt(4) + 3) * speedBase);
		if(cd != 0){
			countDown = cd;
		}else{
			countDown = rand.nextInt(5) * 10;
		}
		//initAnimation();
	}

	@Override
	public void move() {
		if(countDown == 0){
			y += speed;
		}else{
			countDown--;
		}
		//nextAnimation();
	}
}
