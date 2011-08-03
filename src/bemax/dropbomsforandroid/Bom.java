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
		setImage(R.drawable.bom);
		rand = new Random();
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

//	public boolean isHit(Hero h) {
//		int zoc = (int)((h.getRect().width() + getRect().width()) / 2);
//		int xlen = h.getRect().centerX() - getRect().centerX();
//		int ylen = h.getRect().centerY() - getRect().centerY();
//		int dist = (int) Math.sqrt(xlen*xlen+ylen*ylen);
//		return dist < zoc ? true : false;
//	}
}
