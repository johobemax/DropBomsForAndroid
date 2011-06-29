package bemax.dropbomsforandroid;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.graphics.BitmapFactory;
import android.view.View;

public class Apple extends Item
{
	private boolean moveOK;
	private Random rand;

	public Apple(View v){
		super(v);
		image = BitmapFactory.decodeResource(v.getResources(), R.drawable.apple);
		rand = new Random();
		imageWidth = image.getWidth();
		imageHeight = image.getHeight();
	}

	public void move(){
		if(moveOK){
			y += speed;
			if(y > view.getHeight()){
				init();
			}
		}
	}

	@Override
	public void init() {
		// TODO 自動生成されたメソッド・スタブ
		x = rand.nextInt(view.getWidth()-imageWidth);
		y = -imageHeight;
		speed = (rand.nextInt(4) + 3) * view.getHeight() / 400;
		moveOK = false;
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				// TODO 自動生成されたメソッド・スタブ
				moveOK = true;
			}
		};
		timer.schedule(task, 4000 + rand.nextInt(5) * 1000);
	}
}
