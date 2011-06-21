package bemax.dropbomsforandroid;

import java.util.Random;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Bom {
	private int x;
	private int y;
	private int speed;
	private Bitmap bitmap;
	private Random rand;

	public Bom(Resources res, int id){
		bitmap = BitmapFactory.decodeResource(res, id);
		rand = new Random();
		init();
	}

	public void init(){
		x = rand.nextInt(400);
		y = 0;
		speed = rand.nextInt(5) + 5;
	}

	public void drop(){
		y += speed;
		if(y>500){
			init();
		}
	}

	public void drawBom(Canvas canvas){
		canvas.drawBitmap(bitmap, x, y, null);
	}
}
