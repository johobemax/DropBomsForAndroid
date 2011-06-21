package bemax.dropbomsforandroid;

import android.app.Activity;
import android.os.Bundle;

public class DropBomsForAndroidActivity extends Activity{
	private GameView gameView;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new GameView(this);
        setContentView(gameView);
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