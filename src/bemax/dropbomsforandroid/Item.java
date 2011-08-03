package bemax.dropbomsforandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.View;

public abstract class Item extends Acter{
	protected int point;

	public Item(View v) {
		super(v);
	}

	public boolean isHit(Hero h){
		int dx = h.getRect().centerX() - getRect().centerX();
		int dy = h.getRect().centerY() - getRect().centerY();
		double len = Math.sqrt(dx * dx + dy * dy);

		if(len <= h.zoc + zoc){
			return true;
		}else{
			return false;
		}
	}
}
