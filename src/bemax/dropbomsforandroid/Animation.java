package bemax.dropbomsforandroid;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Animation {
	private Bitmap[] images;
	private int imageCnt;
	private int step;
	private int cnt;

	public Animation(Resources res, int[] ids, int step){
		images = new Bitmap[ids.length];
		for(int i=0; i<ids.length; i++){
			images[i] = BitmapFactory.decodeResource(res, ids[i]);
		}
		imageCnt = 0;
		this.step = step;
		cnt = 0;
	}

	public  Bitmap nextImage(){
		cnt++;
		if(cnt==step){
			imageCnt = (imageCnt + 1)==images.length ? 0 : imageCnt + 1;
			cnt = 0;
		}
		return images[imageCnt];
	}
}
