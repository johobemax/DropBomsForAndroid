package bemax.dropbomsforandroid;

import java.util.Random;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;

public class Bom extends Item{
	private Random rand;
	private int countDown;

	public Bom(View v){
		super(v);
		image = BitmapFactory.decodeResource(v.getResources(), R.drawable.bom);
		rand = new Random();
		imageWidth = image.getWidth();
		imageHeight = image.getHeight();

		speedBase = v.getWidth() / 400.0f;
	}

	public void init(int cd){
		x = rand.nextInt(view.getWidth()-imageWidth);
		y = -imageHeight;
		speed = (int)((rand.nextInt(4) + 3) * speedBase);
		if(cd != 0){
			countDown = cd;
		}else{
			countDown = rand.nextInt(5) * 5;
		}
	}

	public void move(){
		if(countDown <= 0){
			y += speed;
		}else{
			countDown--;
		}
	}

	public boolean isHit(Hero h) {
		// TODO 自動生成されたメソッド・スタブ
		return h.getRect().contains(getRect().centerX(), getRect().centerY());
	}

	public void hit(){

	}

	public boolean isHit(Movable m) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
}
