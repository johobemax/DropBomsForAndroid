package bemax.dropbomsforandroid;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

public class Orange extends Item{
	private Bitmap image;
	private Random rand;

	public Orange(View v){
		super(v);
		image = BitmapFactory.decodeResource(v.getResources(), R.drawable.orange);
		imageWidth = image.getWidth();
		imageHeight = image.getHeight();
		rand = new Random();
	}

	public Bitmap getImage(){
		return image;
	}

	@Override
	public void init() {
		// TODO 自動生成されたメソッド・スタブ
		x = rand.nextInt(view.getWidth()-imageWidth);
		y = -imageHeight;
		speed = (rand.nextInt(4) + 1) * view.getHeight() / 200;
	}

	@Override
	public void move() {
		// TODO 自動生成されたメソッド・スタブ
		y += speed;
		if(y > view.getHeight()){
			init();
		}
	}
}
