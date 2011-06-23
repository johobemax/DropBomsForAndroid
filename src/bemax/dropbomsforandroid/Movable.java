package bemax.dropbomsforandroid;

import android.graphics.Rect;

public interface Movable {
	public Rect getRect();
	public boolean isHit(Movable m);
	public void move();
}
