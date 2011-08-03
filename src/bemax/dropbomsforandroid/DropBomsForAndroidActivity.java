package bemax.dropbomsforandroid;

import java.util.HashMap;
import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class DropBomsForAndroidActivity extends Activity implements OnTouchListener,OnLoadCompleteListener{
	private MediaPlayer mp;
	private SoundPool sp;
	private HashMap<Integer,Integer> seMap;
	private Handler handler;
	private DropBomsForAndroidActivity activity;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = this;
        handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO 自動生成されたメソッド・スタブ
				super.handleMessage(msg);
				switch(msg.what){
				case 0:
					new TitleScreen(activity,this);
					break;
				case 1:
					new GameView(activity,this);
					break;
				case 2:
					int[] data = (int[])msg.obj;
					new GameOverScreen(data, activity,this);
				}
			}
        };

        seMap = new HashMap<Integer,Integer>();
        sp = new SoundPool(10,AudioManager.STREAM_MUSIC,1);
        sp.setOnLoadCompleteListener(this);
        seMap.put(R.raw.item_get, sp.load(this,R.raw.item_get,1));
        seMap.put(R.raw.bom, sp.load(this,R.raw.bom,1));
        seMap.put(R.raw.menu_kettei, sp.load(this,R.raw.menu_kettei,1));

        handler.sendEmptyMessage(0);
    }

	protected void onStart() {
    	// TODO アクティビティが開始された
    	super.onStart();
    }

    @Override
    protected void onStop() {
    	// TODO 自動生成されたメソッド・スタブ
    	super.onStop();
    }

    @Override
    protected void onDestroy() {
    	// TODO 自動生成されたメソッド・スタブ
    	super.onDestroy();
    }



	protected MediaPlayer getMp() {
		return mp;
	}

	protected SoundPool getSp() {
		return sp;
	}

	protected HashMap<Integer, Integer> getSeMap() {
		return seMap;
	}

	protected void setMp(MediaPlayer mp) {
		this.mp = mp;
	}

	public boolean onTouch(View v, MotionEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			sp.play(seMap.get(R.raw.menu_kettei), 1.0f, 1.0f, 0, 0, 1.0f);
			handler.sendEmptyMessage(1);
		}
		return false;
	}

	public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {

	}
}