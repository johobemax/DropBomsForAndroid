package bemax.dropbomsforandroid;

import java.util.Random;
import android.graphics.BitmapFactory;
import android.view.View;

public class Bom extends Item{
	private Random rand;
	private boolean moveOK;

	public Bom(View v){
		super(v);
		image = BitmapFactory.decodeResource(v.getResources(), R.drawable.bom);
		rand = new Random();
		imageWidth = image.getWidth();
		imageHeight = image.getHeight();
		moveOK = false;
	}

	public void init(){
		x = rand.nextInt(view.getWidth()-imageWidth);
		y = -imageHeight;
		speed = (rand.nextInt(4) + 3) * view.getHeight() / 400;
	}

	public void move(){
		if(moveOK){
			y += speed;
			if(y>view.getHeight()){
				init();
			}
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

	public void setMoveOK(boolean ok){
		moveOK = ok;
	}
}
