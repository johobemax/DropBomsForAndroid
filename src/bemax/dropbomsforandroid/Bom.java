package bemax.dropbomsforandroid;

import java.util.Random;
import android.view.View;

public class Bom extends Item{
	private Random rand;
	private int countDown;
	private float xSpeed;
	private float ySpeed;

	public Bom(View v){
		super(v);
		setImage(R.drawable.bom);
		rand = new Random();
		speedBase = v.getHeight() / 400.0f;
		zoc = (int)(getImageWidth() * 0.4);
		ySpeed = 2;
	}

	public void init(int cd){
		x = rand.nextInt(view.getWidth()-getImageWidth());
		y = -getImageHeight();
		speed = (rand.nextInt(4) + ySpeed) * speedBase;
		xSpeed = (float)Math.sin((rand.nextInt(9) - 5)*Math.PI*5/180)*speed;
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
