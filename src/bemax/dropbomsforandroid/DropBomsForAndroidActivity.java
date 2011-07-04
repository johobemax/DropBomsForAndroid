package bemax.dropbomsforandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class DropBomsForAndroidActivity extends Activity implements OnTouchListener{
	private GameView gameView;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gametitle);

        ImageView bom = (ImageView)findViewById(R.id.bom_image_view);
        bom.setOnTouchListener(this);
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

	public boolean onTouch(View v, MotionEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			Intent intent = new Intent(this, GamePlayActivity.class);
			startActivity(intent);
		}
		return false;
	}

}