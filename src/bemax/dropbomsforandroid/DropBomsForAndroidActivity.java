package bemax.dropbomsforandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class DropBomsForAndroidActivity extends Activity implements OnTouchListener{
	private SoundEffect se;
	private ImageView bom;

	public SoundEffect getSe(){
		return se;
	}

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gametitle);

        bom = (ImageView)findViewById(R.id.bom_image_view);
        bom.setOnTouchListener(this);

        se = new SoundEffect(this, 10);
		se.addSound(R.raw.menu_kettei);
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
    	se.release();
    }

	public boolean onTouch(View v, MotionEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			se.play(R.raw.menu_kettei);
			Intent intent = new Intent(this, GamePlayActivity.class);
			startActivity(intent);
		}
		return false;
	}
}