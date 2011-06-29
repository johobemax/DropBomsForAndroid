package bemax.dropbomsforandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class GamePlayActivity extends Activity {
	private GameView gameView;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gameplay);
        SurfaceView sView = (SurfaceView)findViewById(R.id.gameView);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        gameView = new GameView(sView, this);
    }

    @Override
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
}
