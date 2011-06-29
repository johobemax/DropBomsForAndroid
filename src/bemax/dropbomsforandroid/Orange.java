package bemax.dropbomsforandroid;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

public class Orange extends Item{
	private Bitmap image;
	private Random rand;
	private boolean moveOK;

	public Orange(View v){
		super(v);
		image = BitmapFactory.decodeResource(v.getResources(), R.drawable.orange);
		imageWidth = image.getWidth();
		imageHeight = image.getHeight();
		rand = new Random();
		moveOK = false;
	}

	public Bitmap getImage(){
		return image;
	}

	@Override
	public void init() {
		// TODO 自動生成されたメソッド・スタブ
		x = rand.nextInt(view.getWidth()-imageWidth);
		y = -imageHeight;
		speed = (rand.nextInt(4) + 3) * view.getHeight() / 400;
	}

	@Override
	public void move() {
		// TODO 自動生成されたメソッド・スタブ
		if(moveOK){
			y += speed;
			if(y > view.getHeight()){
				init();
			}
		}
	}

	public void setMoveOK(boolean b){
		moveOK = b;
	}
}
