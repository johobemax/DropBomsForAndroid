package bemax.dropbomsforandroid;

import java.util.Random;
import android.view.View;

public class Apple extends Item
{
	private Random rand;
	private int countDown;

	public Apple(View v){
		super(v);
		setImage(R.drawable.apple);
		zoc = (int)(imageWidth * 0.5);
		rand = new Random();

		speedBase = v.getWidth() / 400.0f;
	}

	public void move(){
		if(countDown == 0){
			y += speed;
		}else{
			countDown--;
		}
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
			countDown = rand.nextInt(5)*50 + 100;
		}
	}
}
