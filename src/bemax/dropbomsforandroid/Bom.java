package bemax.dropbomsforandroid;

import java.util.Random;
import android.view.View;

public class Bom extends Item{
	private Random rand;
	private int countDown;
	private int xSpeed;

	public Bom(View v){
		super(v);
		setImage(R.drawable.bom);
		rand = new Random();
		speedBase = v.getWidth() / 400.0f;
		zoc = (int)(getImageWidth() * 0.4);
	}

	public void init(int cd){
		x = rand.nextInt(view.getWidth()-getImageWidth());
		y = -getImageHeight();
		xSpeed = rand.nextInt(7) - 3;
		speed = (int)((rand.nextInt(4) + 3) * speedBase);
		if(cd != 0){
			countDown = cd;
		}else{
			countDown = rand.nextInt(5) * 5;
		}
		//initAnimation();
	}

	public void move(){
		if(countDown <= 0){
			x = x + xSpeed;
			y += speed;
		}else{
			countDown--;
		}
		//nextAnimation();
	}
}
