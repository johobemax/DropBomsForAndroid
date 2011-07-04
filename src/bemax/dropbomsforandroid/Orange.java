package bemax.dropbomsforandroid;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

public class Orange extends Item{
	private Bitmap image;
	private Random rand;
	private boolean moveOK;
	private int countDown;

	public Orange(View v){
		super(v);
		image = BitmapFactory.decodeResource(v.getResources(), R.drawable.orange);
		imageWidth = image.getWidth();
		imageHeight = image.getHeight();
		rand = new Random();

		speedBase = v.getWidth() / 400.0f;
	}


	public Bitmap getImage(){
		return image;
	}

	@Override
	public void init(int cd) {
		// TODO 自動生成されたメソッド・スタブ
		x = rand.nextInt(view.getWidth()-imageWidth);
		y = -imageHeight;
		speed = (int)((rand.nextInt(4) + 3) * speedBase);
		if(cd != 0){
			countDown = cd;
		}else{
			countDown = rand.nextInt(5) * 10;
		}
	}

	@Override
	public void move() {
		if(countDown == 0){
			y += speed;
		}else{
			countDown--;
		}
	}
}
