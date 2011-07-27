package bemax.dropbomsforandroid;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class TitleScreen implements OnTouchListener, OnLoadCompleteListener{
	private Handler handler;
	private SoundPool sp;
	private ImageView bom;
	private int menu_kettei;

	public TitleScreen(Activity act, Handler h){
		handler = h;
		act.setContentView(R.layout.gametitle);

		bom = (ImageView)act.findViewById(R.id.bom_image_view);

        sp = new SoundPool(1,AudioManager.STREAM_MUSIC,1);
        menu_kettei = sp.load(act, R.raw.menu_kettei, 1);
        sp.setOnLoadCompleteListener(this);
	}

	public boolean onTouch(View v, MotionEvent event) {
		sp.play(menu_kettei, 1.0f, 1.0f, 0, 0, 1.0f);
		handler.sendEmptyMessage(1);
		return false;
	}

	public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
		// TODO 自動生成されたメソッド・スタブ
		bom.setOnTouchListener(this);
	}
}
